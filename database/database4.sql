CREATE USER my_user IDENTIFIED BY my_user;

GRANT CONNECT, RESOURCE, DBA TO my_user;
GRANT UNLIMITED TABLESPACE TO my_user;

alter table Food add active varchar2(5) check (active = 'ON' or active = 'OFF');
alter table Service add active varchar2(5) check (active = 'ON' or active = 'OFF');

alter table Customer drop column IDENTITYCARD;
alter table Customer drop column BIRTHDAY;
alter table Customer drop column ADDRESS;

create or replace procedure sp_insertBill(
    V_idStaff in Bill.idStaff%type,
    V_idCustomer in bill.idcustomer%type,
    V_idWedding in bill.idwedding%type,
    V_return out varchar2
)
is
    BillBeforeInsert number(4);
    BillAfterInsert number(4);
begin
    select count(*) into BillBeforeInsert from Bill;
    insert into Bill(idStaff,idcustomer,idwedding) values(
        V_idStaff,V_idCustomer,V_idWedding
    );
    select count(*) into BillAfterInsert from Bill;
    if BillAfterInsert > BillBeforeInsert then
        v_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_insertReport(
    V_day in report.closingdate%type,
    V_return out varchar2
)
is  
    reportBeforeInsert number(4);
    reportAfterInsert number(4);
begin
    select count(*) into reportBeforeInsert from Report;
    insert into report(closingdate) values(
        V_day
    );
    select count(*) into reportAfterInsert from Report;
    if reportAfterinsert > reportBeforeInsert then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_deleteReport(
    V_idReport in report.idReport%type,
    V_return out varchar2
)
is  ReportTmp number(1);
begin
    delete Report where idReport = V_idReport;
    select count(*) into ReportTmp from report where idReport = V_idReport;
    if ReportTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_deleteFoodOrder(
    V_idFood in Food.idFood%type,
    V_idWedding in inforWedding.idWedding%type,
    V_return out varchar2
)
is
    FoodOrderTmp number(1);
begin
    delete FoodOrder where idFood = V_idFood and idWedding = V_idWedding;
    select count(*) into FoodOrderTmp from FoodOrder where idFood = V_idFood and idWedding = V_idWedding;
    if FoodOrderTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_updateBill(
    V_idCus in Customer.idCustomer%type,
    V_idBill in Bill.idBill%type,
    V_return out varchar2
)
is  BillTmp number(1);
begin
    update Bill set
        idCustomer = V_idCus
    where idBill = V_idBill;
    select count(*) into BillTmp from Bill where idBill = V_idBill and idCustomer = V_idCus;
    if BillTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_deleteBill(
    V_idBill in Bill.idBill%type,
    V_return out varchar2
)
is BillTmp number(1);
begin
    delete Bill where idBill = V_idBill;
    select count(*) into BillTmp from Bill where idBill = V_idBill;
    if BillTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace trigger trg_updateStaff
before update on Staff
for each row
begin
    if :new.numberPhone != :old.numberPhone then
        update account set USERNAME = :new.numberphone;
    end if;
end;

SET SERVEROUTPUT ON
create or replace trigger trg_updateOrderWedding
before update on OrderWedding
for each row
declare 
    idLobbyTmp varchar2(9);
    maxTableTmp number(4);
    orderLobby number(1);
    BillTmp number(1);
begin 
    select count(*) into BillTmp from Bill where idWedding = :old.idWedding;
    if BillTmp = 0 then
        if :new.idLobby != :old.idLobby then
            idLobbyTmp := :new.idLobby;
            if :new.DateStart != :old.DateStart then
                select count(*) into orderLobby from OrderWedding where idLobby = idLobbyTmp and :new.DATESTART = DATESTART and active = 'ON';
                if orderLobby > 0 then
                    return;
                end if;
            end if;
        else
            idLobbyTmp := :old.idLobby;
            if :new.DateStart != :old.DateStart then
                select count(*) into orderLobby from OrderWedding where idLobby = idLobbyTmp and :new.DATESTART = DATESTART;
                if orderLobby > 0 then
                    return;
                end if;
            end if;
        end if;
        
        select maxTable into maxTableTmp from Lobby where idLobby = idLobbyTmp;
        
        if :new.numberOfTable != :old.numberOfTable then
            if :new.numberOfTable > maxTableTmp then
                return;
            end if;
        else
            if :old.numberOfTable > maxTableTmp then
                return;
            end if;
        end if;
        DBMS_OUTPUT.PUT_LINE('Bill da lap khong the sua');
    end if;
end;
alter table OrderWedding add active varchar2(5) check(active = 'ON' or active = 'OFF');

create or replace trigger trg_updateLobby
before update on Lobby
for each row
declare 
    WeddingActive number(5);
begin
    select count(*) into WeddingActive from OrderWedding where idLobby = :old.idLobby and active = 'ON';
    if WeddingActive > 0 then
        RAISE_APPLICATION_ERROR(-20000,'sanh nay hien tai da co nguoi dat khong duoc update');
    end if;
end;

create or replace trigger trg_updateFood
before update on Food
for each row
declare
    FoodActive number(5);
begin
    select count(*) into FoodActive from OrderWedding Ord, FoodOrder Fo where ORd.idWedding = Fo.idWedding and Fo.idFood = :old.idFood and ORd.active = 'ON';
    if FoodActive > 0 then
       RAISE_APPLICATION_ERROR(-20000,'mon an nay hien tai da co nguoi dat khong duoc update'); 
    end if;
end;

create or replace trigger trg_serviceFood
before update on Service
for each row
declare
    ServiceActive number(5);
begin
    select count(*) into ServiceActive from OrderWedding Ord, ServiceORder Se where ORd.idWedding = Se.idWedding and Se.idService = :old.idService and ORd.active = 'ON';
    if ServiceActive > 0 then
       RAISE_APPLICATION_ERROR(-20000,'dich vu nay hien tai da co nguoi dat khong duoc update'); 
    end if;
end;