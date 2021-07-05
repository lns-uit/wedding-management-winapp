CREATE SEQUENCE Lobby_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Service_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Food_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE List_Wedding_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Bill_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Report_Monthly_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Order_Wedding_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Staff_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Account_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Service_Order_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Food_Order_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE Customer_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
CREATE SEQUENCE InforWedding_Stt
   MINVALUE 1 
   MAXVALUE 9999999 
   INCREMENT BY 1 
   START WITH 1
   NOCACHE 
   NOORDER 
   NOCYCLE;
/*create table*/
create table Lobby(
    stt number not null,
    idLobby varchar(8),
    nameLobby varchar(30),
    lobbyType varchar(20)check(lobbyType='Vip' or lobbyType='Th??ng'),
    maxTable number,
    priceTable number,
    priceLobby number,
    constraint Lobby_pk primary key (idLobby)
);
create table Service(
    stt number not null,
    idService varchar2(8),
    nameService varchar2(30),
    price number,
    constraint Service_pk primary key (idService)
);
create table Food(
    stt number not null,
    idFood varchar2(8),
    nameFood varchar2(30),
    priceFood number,
    typeFood varchar(20) check(typeFood='n??cc u?ng' or typeFood='món chính' or typeFood = 'khai v?' or typeFood='tráng mi?ng'),
    constraint Food_pk primary key (idFood)
);
create table InforWedding(
    stt number not null,
    idWedding varchar(8),
    nameBride varchar(30),
    nameGroom varchar(30),
    constraint InforWedding_pk primary key (idWedding)
);
create table OrderWedding(
    stt number not null,
    idWedding varchar2(8) unique,
    idLobby varchar2(8),
    idStaff varchar2(9),
    idCustomer varchar2(8),
    numberOfFood number,
    numberOfService number,
    deposit number,
    money number,
    numberOfTable number,
    dateOrderDate date,
    dateStart  date,
    active varchar2(10),
    constraint OrderWedding_pk primary key (idWedding,idLobby,idStaff,idCustomer)
);
create table Staff(
    stt number not null,
    idStaff varchar2(9),
    nameStaff varchar2(30),
    numberPhone varchar2(10) unique,
    address varchar2(50),
    identityCard varchar2(9) unique,
    startWork date,
    typeStaff varchar2(30) check(typeStaff = 'admin' or typeStaff = 'qu?n lý' or typeStaff = 'nhân viên lao công' or typeStaff = 'nhân viên ph?c v?' or typeStaff = 'nhân viên l? tân'),
    constraint Staff_pk primary key (idStaff)
);
create table Bill(
    stt number not null,
    idBill varchar2(8),
    idStaff varchar2(9),
    idCustomer varchar2(8),
    idWedding varchar(8),
    money number,
    dateOfPayment date,
    constraint Bill_pk primary key (idBill)
);
create table Customer(
    stt number not null,
    idCustomer varchar2(8),
    nameCustomer varchar2(30),
    numberPhone varchar2(10) unique,
    dayRegister date,
    money number,
    constraint Customer_pk primary key (idCustomer)
);
create table Account(
    stt number not null,
    idAccount varchar2(10),
    idStaff varchar2(9),
    userName varchar2(20),
    password varchar2(20),
    status varchar(20),
    constraint Account_pk primary key (idAccount)
);
create table Report(
    stt number not null,
    idReport varchar2(9),
    totalBillOfMonth number,
    TOTALWEDDINGOFMONTH number,
    closingDate date,
    constraint Reports_pk primary key (idReport)
);  
create table ServiceOrder(
    stt number not null,
    idService varchar2(8),
    idWedding varchar2(8),
    constraint ServiceOrder_pk primary key (idService,idWedding)
);

create table FoodOrder(
    stt number not null,
    idFood varchar2(8),
    idWedding varchar2(8),
    constraint FoodOrder_pk primary key (idFood,idWedding)
);
/*create foreign*/
alter table OrderWedding add constraint OrderWedding_Lobby_fk foreign key(idLobby) references Lobby(idLobby);
alter table OrderWedding add constraint OrderWedding_Customer_fk foreign key(idCustomer) references Customer(idCustomer);
alter table OrderWedding add constraint OrderWedding_Staff_fk foreign key(idStaff) references Staff(idStaff);
alter table OrderWedding add constraint OrderWedding_InforWedding_fk foreign key(idWedding) references InforWedding(idWedding);
alter table Bill add constraint Bill_Staff_fk foreign key (idStaff) references Staff(idStaff);
alter table Bill add constraint Bill_Customer_fk foreign key (idCustomer) references Customer(idCustomer);
alter table ServiceOrder add constraint ServiceOrder_InforWedding_fk foreign key (idWedding) references InforWedding(idWedding);
alter table ServiceOrder add constraint ServiceOrder_Service_fk foreign key (idService) references Service(idService);
alter table FoodOrder add constraint FoodOrder_InforWedding_fk foreign key (idWedding) references InforWedding(idWedding);
alter table FoodOrder add constraint FoodOrder_Food_fk foreign key (idFood) references Food(idFood);
alter table Account add constraint Account_Staff_fk foreign key (idStaff) references Staff(idStaff);
--trigger
create or replace trigger trg_deleteCustomer
before delete ON Customer
for each row
begin
    update OrderWedding set idCustomer = null where idCustomer = :old.idCustomer;
    update Bill set idCustomer = null where idCustomer = :old.idCustomer;
end;
------
create or replace trigger trg_deleteFood
before delete on Food
for each row
declare foodActive number(5);
begin
    select count(*) into foodActive from FoodOrder where idFood = :old.idFood;
    if foodActive > 0 then
        RAISE_APPLICATION_ERROR(-20000,'mon an da co nguoi dat khong the xoa');
    end if;
end;
----------
create or replace trigger trg_deleteInforeWedding 
before delete on InforWedding
for each row
declare BillTmp number;
idCustmp varchar2(10);
moneys number;
numbers number;
begin
    select count(*) into BillTmp from Bill where idWedding = :old.idWedding;
    if BillTmp = 1 then
        DBMS_OUTPUT.PUT_LINE('da co hoa don khong the xoa');
    else
        select idCustomer into idCustmp from orderWedding where idWedding = :old.idWedding;
        select count(*) into numbers from orderWedding where idCustomer = idCustmp;
        select money into moneys from customer where idCustomer = idCustmp;
        if numbers = 1 and moneys = 0 then
            delete from customer where idCustomer = idCustmp;
        end if;
        delete from FoodOrder where idWedding = :old.idWedding;
        delete from ServiceOrder where idWedding = :old.idWedding;
        delete from OrderWedding where idWedding = :old.idWedding;
    end if;
end;
-----------
create or replace trigger trg_deleteLobby
before delete on Lobby
for each row
declare lobbyActive number;
begin
    select count(*) into LobbyActive from OrderWedding where idLobby = :old.idLobby and active = 'ON';
    if LobbyActive > 0 then
         RAISE_APPLICATION_ERROR(-20000,'sanh da duoc dat khong the xoa');
    else
        update OrderWedding set idLobby = null where idLobby = :old.idLobby;
    end if;
end;
----------------------
create or replace trigger trg_deleteService
before delete on Service
for each row
declare serviceActive number;
begin
    select count(*) into serviceActive from serviceorder where idService = :old.idService;
    if serviceActive > 0 then
        RAISE_APPLICATION_ERROR(-20000,'dich vu nay da co nguoi dat khong the xoa');
    end if;
end;
-------------------------
create or replace trigger trg_deleteStaff
before delete on Staff
for each row
declare idStaffNone varchar2(8);
begin
    update OrderWedding set idStaff = null where idstaff = :old.idStaff;
    update Bill set idstaff = null where idStaff = :old.idStaff;
    delete account where idStaff = :old.idStaff;
end;
------------------------
create or replace trigger  trg_inserService
before insert on Service
for each row
declare tmp number;
begin
    tmp := Service_Stt.nextval;
    :new.stt := tmp;
    :new.idService := concat('SER',to_char(tmp));
end;
--------------------------
create or replace trigger trg_insertAccountAuto
After insert on Staff
for each row
declare tmp number;
begin
    tmp := Account_stt.nextval;
    insert into Account values(
        tmp, concat('Acc',tmp) , :new.idStaff, :new.numberPhone, '123456', 'OFF'
    );
end;
-------------------------
create or replace trigger trg_insertBill
before insert on Bill
for each row
declare 
    tmp number;
    moneyTmp number;
    reportTmp number;
    numberWedding number;
begin
    select count(*) into numberWedding from bill where idWedding = :new.idWedding;
    if numberWedding > 0 then
        RAISE_APPLICATION_ERROR(-20000,'cái ?ám c??i này ?ã thanh toán r?i');
    else
        tmp := Bill_stt.nextval;
        select money into moneyTmp from OrderWedding where idWedding = :new.idWedding;
        :new.stt := tmp;
        :new.idBill := concat('B',tmp);
        :new.money := moneyTmp;
        :new.DATEOFPAYMENT := current_date;
        update Customer set money = money + moneyTmp where idCustomer = :new.idCustomer;
        update orderWedding set deposit = moneyTmp, active = 'OFF' where idWedding = :new.idWedding;
        delete from foodorder where idWedding = :new.idWedding;
        delete from serviceorder where idWedding = :new.idWedding;
        select count(*) into reportTmp from report where EXTRACT(YEAR FROM :new.dateOfPayment) = EXTRACT(YEAR FROM closingDate) and EXTRACT(MONTH FROM :new.dateOfPayment) = EXTRACT(MONTH FROM closingDate);
        if reportTmp > 0 then
            update report set TOTALBILLOFMONTH = TOTALBILLOFMONTH + :new.money, TOTALWEDDINGOFMONTH = TOTALWEDDINGOFMONTH +1
            where EXTRACT(YEAR FROM :new.dateOfPayment) = EXTRACT(YEAR FROM closingDate) and EXTRACT(MONTH FROM :new.dateOfPayment) = EXTRACT(MONTH FROM closingDate);
        else
            insert into report (closingDate) values(:new.dateOfPayment);
            update report set TOTALBILLOFMONTH = :new.money, TOTALWEDDINGOFMONTH = 1
            where EXTRACT(YEAR FROM :new.dateOfPayment) = EXTRACT(YEAR FROM closingDate) and EXTRACT(MONTH FROM :new.dateOfPayment) = EXTRACT(MONTH FROM closingDate);
        end if;
    end if;
end;
-----------------------------
create or replace trigger trg_insertCustomer
before insert on Customer
for each row
declare 
    tmp number;
    birthDay number;
    dayRegisters number;
begin
    tmp := Customer_stt.nextval;
    :new.stt := tmp;
    :new.idCustomer := concat('CUS',to_char(tmp));
    :new.money := 0;
    :new.discount := 0;
    :new.DAYREGISTER := current_date;
end;
----------------------------
create or replace trigger trg_insertFood
before insert on Food
for each row
declare tmp number;
begin
    tmp := Food_stt.nextval;
    :new.stt := tmp;
    :new.idFood := concat('F',to_char(tmp));
    :new.active := 'ON';
end;
--------------------------
create or replace trigger trg_insertFoodOrder
before insert on FoodOrder
for each row
declare 
    tmp number;
    FoodTmp number;
begin
    select count(*) into FoodTmp from Food where idFood = :new.idFood and active = 'OFF';
    if FoodTmp = 0 then
        tmp := Food_Order_stt.nextval;
        :new.stt := tmp;
    end if;
end;
--------------------------
create or replace trigger trg_insertInforWedding
before insert on InforWedding
for each row
declare tmp number;
begin
    tmp := InforWedding_stt.nextval;
    :new.stt := tmp;
    :new.idWedding := concat('WED',to_char(tmp));
end;
-------------------------
create or replace trigger trg_insertLobby
before insert on Lobby
for each row
declare tmp number;
begin
    tmp := Lobby_stt.nextval;
    :new.stt := tmp;
    :new.idLobby := concat('LOB',to_char(tmp));
    :new.active := 'ON';
end;
------------------------
create or replace trigger trg_insertReports
before insert on Report
for each row
declare
    tmp number;
    sumReport number;
    monthlyTmp number;
    yearTmp number;
    BillOfMonth number;
    reportTmp number;
begin
    tmp := report_monthly_stt.nextval;
    :new.stt := tmp;
    :new.idReport := concat('RE',tmp);
    select Sum(money) into sumReport from Bill 
    where EXTRACT(YEAR FROM dateOfPayment) = EXTRACT(YEAR FROM :new.closingDate) and EXTRACT(MONTH FROM dateOfPayment) = EXTRACT(MONTH FROM :new.closingDate);
    :new.TOTALBILLOFMONTH := sumReport;
    select count(*) into BillOfMonth from Bill 
    where EXTRACT(YEAR FROM dateOfPayment) = EXTRACT(YEAR FROM :new.closingDate) and EXTRACT(MONTH FROM dateOfPayment) = EXTRACT(MONTH FROM :new.closingDate);
    :new.TOTALWEDDINGOFMONTH := BillOfMonth;
end;
----------------------------
create or replace trigger trg_insertServiceOrder
before insert on ServiceOrder
for each row
declare 
    tmp number;
    serviceTmp number;
begin
    select count(*) into serviceTmp from Service where idService = :new.idService and active = 'OFF';
    if serviceTmp = 0 then 
        tmp := Service_Order_stt.nextval;
        :new.stt := tmp;
    end if;
end;
---------------------------
create or replace trigger trg_insertStaff
before insert on Staff
for each row
declare tmp number;
begin
    tmp := Staff_stt.nextval;
    :new.stt := tmp;
    :new.idStaff := concat('ST',to_char(tmp));
    :new.STARTWORK := current_date;
    :new.ACTIVE := 'ON';
end;
----------------------------
create or replace trigger trg_insetOrderWedding
before insert on OrderWedding
for each row
declare 
    maxTableTmp number;
    priceLobbyTmp number;
    priceTableTmp number;
    sumService number;
    sumFood number;
    tmp number;
    numberOfFoodTmp number;
    numberOfServiceTmp number;
    orderLobby number;
    activeLobby varchar2(5);
begin
    if TO_CHAR(current_date, 'YYYYMMDD') > TO_CHAR(:new.DATESTART, 'YYYYMMDD') then
        RAISE_APPLICATION_ERROR (-20000,'ngày ??t không th? l?n h?n ngày di?n ra');
    end if;
    select count(*) into numberOfFoodTmp from FoodOrder where idWedding = :new.idWedding;
    select count(*) into numberOfServiceTmp from ServiceOrder where idWedding = :new.idWedding;
    select maxTable into maxTableTmp from Lobby where idLobby = :new.idLobby;
    select priceLobby into priceLobbyTmp from Lobby where idLobby = :new.idLobby;
    select priceTable into priceTableTmp from Lobby where idLobby = :new.idLobby;
    select Sum(price) into sumService from Service, ServiceOrder where Service.idService = ServiceOrder.idService and ServiceOrder.idWedding = :new.idWedding;
    select Sum(priceFood) into sumFood from Food, FoodOrder where Food.idFood = FoodOrder.idFood and FoodOrder.idWedding = :new.idWedding;
    select count(*) into orderLobby from OrderWedding where :new.idLobby = idLobby and :new.DATESTART = DATESTART and active = 'ON';
    if orderLobby > 0 then
        RAISE_APPLICATION_ERROR(-20000,'sanh nay da co nguoi dat');
        return;
    else
        if maxTableTmp >= :new.numberOfTable then
            if numberOfFoodTmp = 0 then
                sumFood := 0;
            end if;
            if numberOfServiceTmp = 0 then
                sumService := 0;
            end if;
            tmp := Order_Wedding_stt.nextval;
            :new.stt := tmp;
            :new.numberOfFood := numberOfFoodTmp;
            :new.NUMBEROFSERVICE := numberOfServiceTmp;
            :new.money := priceLobbyTmp+(priceTableTmp+sumFood)*:new.numberOfTable + sumService;
            :new.DEPOSIT := :new.money*0.3;
            :new.DATEORDERDATE := current_date;
            :new.active := 'ON';
            DBMS_OUTPUT.PUT_LINE(:new.money);
        else
            RAISE_APPLICATION_ERROR(-20000,'so ban vuot qua quy dinh');
            return;
        end if;
    end if;
end;
--------------------------
create or replace trigger trg_updateBill
before update on Bill
for each row
begin
    if :new.idCustomer != :old.idCustomer then
        update Customer set money = money-:old.money where idCustomer = :old.idCustomer;
        update Customer set money = money+:old.money where idCustomer = :new.idCustomer;
    end if;
end;
-----------------------
create or replace trigger trg_updateCustomer
before update on Customer
for each row
begin
    if :new.money > 100000000 and :new.money < 300000000 then
        :new.discount := 20;
    elsif :new.money > 300000000 then
        :new.discount := 35;
    else
        :new.discount := 0;
    end if;
end;
----------------------
create or replace trigger trg_updateFood
before update on Food
for each row
declare
    FoodActive number(5);
begin
    select count(*) into FoodActive from foodorder where idFood = :old.idFood;
    if FoodActive > 0 then
       RAISE_APPLICATION_ERROR(-20000,'mon an nay hien tai da co nguoi dat khong duoc update'); 
    end if;
end;
-----------------------
create or replace trigger trg_updateLobby
before update on Lobby
for each row
declare 
    WeddingActive number;
begin
    select count(*) into WeddingActive from OrderWedding where idLobby = :old.idLobby and active = 'ON';
    if WeddingActive > 0 then
        RAISE_APPLICATION_ERROR(-20000,'sanh nay hien tai da co nguoi dat khong duoc update');
    end if;
end;
--------------------------
create or replace trigger trg_updateService
before update on Service
for each row
declare
    ServiceActive number;
begin
    select count(*) into ServiceActive from serviceOrder where idService = :old.idService;
    if ServiceActive > 0 then
       RAISE_APPLICATION_ERROR(-20000,'dich vu nay hien tai da co nguoi dat khong duoc update'); 
    end if;
end;
--------------------------
create or replace trigger trg_updateStaff
before update on Staff
for each row
begin
    if :new.numberPhone != :old.numberPhone then
        update account set USERNAME = :new.numberphone;
    end if;
end;
-----procedure
create or replace procedure sp_changePasswordStaff(
    v_numberphone in account.username%type,
    v_password in account.password%type,
    v_newPassword in account.password%type,
    V_return out varchar2
)
is result account.username%type;
begin
    
--    select account.username into result
--    from account
--    where v_numberphone = account.username and v_password = account.password;
--    v_return := 'true';
    
    update account set password = v_newPassword  where v_numberphone=username and v_password=password;
    
    IF SQL%ROWCOUNT = 0 THEN
        v_return := 'false';
    else v_return := 'true';
    END IF;
    
--    EXCEPTION 
--        WHEN NO_DATA_FOUND then
--            DBMS_OUTPUT.PUT_LINE( 'tim ok' );
--            v_return := 'User khong ton tai';

--        else v_return:= 'false';

--    begin
--        if result = '' then
--            v_return := 'User khong ton tai';
--        else
--            update account set password = v_newPassword  where v_numberphone=username;
--            v_return := 'true';
--        end if;
--    end;

end;
----------
create or replace procedure sp_deleteCustomer(
    V_idCus in customer.idCustomer%type,
    V_return out varchar2
)
is cusTmp number(1);
begin
    select count(*) into cusTmp from customer where V_idCus = idCustomer;
    if cusTmp = 0 then
        V_return := 'false';
    else
        delete from customer where idcustomer = V_idcus;
        select count(*) into cusTmp from customer where idcustomer = V_idcus;
        if cusTmp = 0 then
            V_return := 'true';
        else
            V_return := 'false';
        end if;
    end if;
end;
-----------
create or replace procedure sp_deleteFood (
    V_idFood in food.idFood%type,
    V_return out varchar2
)
is foodTmp number(1);
begin
    select count(*) into foodTmp from Food Where idFood = V_idFood;
    if foodTmp = 0 then
        V_return := 'false';
    else
        delete from Food where idFood = V_idFood;
        select count(*) into foodTmp from Food Where idFood = V_idFood;
        DBMS_OUTPUT.PUT_LINE(foodTmp);
        if foodTmp = 0 then
            V_return := 'true';
        else
            V_return := 'false';
        end if;
    end if;
end;
-------------
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
-----------
create or replace procedure sp_deleteInforWedding(
    V_idWedding in InforWedding.idWedding%type,
    V_return out varchar2
)
is InforWeddingTmp number(1);
begin
    delete from InforWedding where idWedding = V_idWedding;
--    select count(*) into InforWeddingTmp from InforWedding where idWedding = V_idWedding;
--    if InforWeddingTmp = 0 then
--        V_return := 'true';
--    else
--        V_return := 'false';
--    end if;
    IF SQL%ROWCOUNT = 0 THEN
        v_return := 'false';
    else v_return := 'true';
    END IF;
end;
-----------
create or replace procedure sp_deleteLobby(
    V_idLobby in Lobby.idLobby%type,
    V_return out varchar2
)
is lobbyTmp number(1);
begin
    delete from Lobby where idLobby = V_idLobby;
    select count(*) into lobbyTmp from Lobby where V_idLobby = idLobby;
    if lobbyTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
-----------
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
------------
create or replace procedure sp_deleteServiceOrder(
    V_idService in ServiceOrder.idService%type,
    V_idWedding in ServiceOrder.idWedding%type,
    V_return out varchar2
)
is ServiceOrderTmp number(1);
begin
    delete from ServiceOrder where idService = V_idService and V_idWedding = idWedding;
    select count(*) into ServiceOrderTmp from ServiceOrder where idService = V_idService and V_idWedding = idWedding;
    if ServiceOrderTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
-----------------
create or replace procedure sp_deleteStaff(
    V_idStaff in staff.idStaff%type,
    V_return out varchar2
)
is
    staffTmp number(1);
begin
    select count(*) into staffTmp from staff where V_idStaff = idStaff;
    if staffTmp = 0 then
        V_return := 'false';
    else
        delete from staff where idstaff = V_idStaff;
        select count(*) into staffTmp from Staff where idStaff = V_idStaff;
        if staffTmp = 0 then
            V_return := 'true';
        else
            V_return := 'false';
        end if;
    end if;
end;
-----------------
create or replace procedure sp_deteService(
    V_idService in service.idService%type,
    V_return out varchar2
)
is   serviceTmp number(1);
begin
    select count(*) into serviceTmp from service where idService = v_idservice;
    if serviceTmp = 0 then
        V_return := 'false';
    else
        delete from service where idService = V_idService;
        select count(*) into serviceTmp from service where idService = v_idservice;
        DBMS_OUTPUT.PUT_LINE(serviceTmp);
        if serviceTmp = 0 then
            V_return := 'true';
        else
            V_return := 'false';
        end if;
    end if;
end;
----------------
create or replace procedure sp_findStaffByPhone 
(v_numberPhone in staff.numberPhone%type, v_typeStaff out staff.typestaff%type) 
is 
begin
    select typestaff into v_typeStaff
    from staff
    where v_numberphone = numberphone;
    EXCEPTION
    when no_data_found 
    then v_typeStaff := '';
end;
--------------
create or replace procedure sp_getAllBill(cur_BillOut OUT SYS_REFCURSOR)
is
begin
    open cur_BillOut for
    select idBill, idStaff, idCustomer, idWedding, money, dateOfPayment
    from  bill;
end;
---------------
create or replace procedure sp_getAllCustomer(cur_customerOut OUT SYS_REFCURSOR)
is
begin
    open cur_customerOut for
    select * 
    from  customer;
end;
---------------
create or replace procedure sp_getAllFood(cur_foodOut OUT SYS_REFCURSOR)
is
begin
    open cur_foodout for
    select * 
    from  food;
end;
---------------
create or replace procedure sp_getAllFoodOrder(cur_foodOrderOut OUT SYS_REFCURSOR)
is
begin
    open cur_foodOrderOut for
    select * 
    from  foodOrder;
end;
---------------
create or replace procedure sp_getAllInFoWed(cur_infoWedOut OUT SYS_REFCURSOR)
is
begin
    open cur_infoWedOut for
    select * 
    from  inforWedding;
end;
---------------
create or replace procedure sp_getAllLobby(cur_lobbyOut OUT SYS_REFCURSOR)
is
begin
    open cur_lobbyOut for
    select * 
    from  lobby;
end;
--------------
create or replace procedure sp_getAllOrderWed (cur_orderWedOut OUT SYS_REFCURSOR)
is
begin
    open cur_orderWedOut for
    select idWedding, idLobby, idStaff, customer.idCustomer, numberOfFood, numberOfService, deposit, orderwedding.money, numberoftable, dateorderdate, datestart, active, customer.namecustomer, customer.numberphone
    from  orderwedding, customer
    where orderwedding.idcustomer = customer.idCustomer;
end;
----------------
create or replace procedure sp_getAllService(cur_serviceOut OUT SYS_REFCURSOR)
is
begin
    open cur_serviceOut for
    select * 
    from  service;
end;
------------------
create or replace procedure sp_getAllStaff(cur_userOut OUT SYS_REFCURSOR)
is
begin
    open cur_userOut for
    select  *
    from staff;
end;
------------------
create or replace procedure sp_getBillById(
v_idBill in bill.idbill%type,
cur_customerOut out SYS_REFCURSOR
)
is
begin
    open cur_customerOut for
    select * 
    from bill
    where v_idBill = idBill;
end;
------------------
create or replace procedure sp_getOrderWeddingById(
    v_idWedding in orderwedding.idwedding%type,
    cur_orderWeddingOut out SYS_REFCURSOR,
    cur_foodOrderOut out SYS_REFCURSOR,
    cur_serviceOrderOut out SYS_REFCURSOR,
    cur_infoWedding out SYS_REFCURSOR,
    cur_customer out SYS_REFCURSOR,
    cur_lobby out SYS_REFCURSOR
)
is
begin
    open cur_orderWeddingOut for 
    select *
    from orderWedding
    where idWedding = v_idWedding;
--    close cur_orderWeddingOut;
    
    open cur_foodorderout for 
    select food.idfood, food.namefood, food.pricefood, food.typefood
    from foodorder, food
    where idWedding = v_idWedding and food.idfood = foodorder.idfood;
--    close cur_foodorderout;
    
    open cur_serviceOrderOut for
    select service.idservice, service.nameservice, service.price
    from service, serviceorder 
    where idWedding = v_idWedding and service.idservice= serviceorder.idservice;
--    close cur_serviceOrderOut;

    open cur_infoWedding for
    select idWedding, namebride, namegroom
    from inforwedding 
    where idWedding = v_idWedding;
    
    open cur_customer for
    select customer.idCustomer, nameCustomer, numberPhone, dayregister, customer.money, discount
    from customer, orderwedding
    where customer.idCustomer = orderwedding.idcustomer and v_idWedding = orderwedding.idwedding;
    
    open cur_lobby for
    select lobby.idLobby, namelobby, lobbytype, maxtable, pricetable, priceLobby
    from lobby, orderWedding
    where  orderwedding.idwedding = v_idWedding and  lobby.idLobby = orderWedding.idLobby ;
    
end;
----------------
create or replace procedure sp_getReport (cur_reportOut OUT SYS_REFCURSOR)
is
begin
    open cur_reportOut for
    select closingdate 
    from  report;
end;
-----------------
create or replace procedure sp_getServiceOrder (cur_setviceOrderOut OUT SYS_REFCURSOR)
is
begin
    open cur_setviceOrderOut for
    select * 
    from  serviceOrder;
end;
-----------------
create or replace procedure sp_getStaffById(
v_idStaff in out staff.idstaff%type,
v_nameStaff out staff.namestaff%type,
v_numberPhone out staff.numberphone%type,
v_address out staff.address%type,
v_identitycard out staff.identitycard%type,
v_startWork out staff.startwork%type,
v_typeStaff out staff.typestaff%type
)
is
begin
    select idstaff,namestaff,numberphone, address, identitycard, startwork, typestaff
    into v_idStaff,v_nameStaff, v_numberPhone, v_address, v_identitycard, v_startWork, v_typeStaff
    from staff
    where v_idstaff = idstaff;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            v_typestaff := '' ;
            v_idStaff :='';
            v_nameStaff:='';
            v_numberPhone:='';
            v_address:='';
            v_identitycard:='';
            v_startWork:='';
end;
-------------------
create or replace procedure sp_insert_food(
    v_nameFood in food.nameFood%type,
    v_priceFood in food.priceFood%type,
    v_typeFood in food.typeFood%type,
    V_return out varchar2
)
is
    FoodBeforeInsert number(2);
    FoodAfterInsert number(2);
begin
   -- select count(*) into FoodBeforeInsert from Food;
    insert into food(nameFood,priceFood,typeFood) values(
        v_nameFood,v_priceFood,v_typeFood
    );
--   -- select count(*) into FoodAfterInsert from Food;
--    if FoodAfterInsert > FoodBeforeInsert then
--        V_return := 'true';
--    else
--        V_return := 'false';
--    end if;
   IF SQL%ROWCOUNT = 0 THEN
        v_return := 'false';
    else v_return := 'true';
    END IF;
end;
--------------------
create or replace procedure sp_insert_infoWedding(
    V_nameBride in inforwedding.namebride%type,
    v_namegroom in inforwedding.namegroom%type,
    v_idWedding out inforwedding.idwedding%type
)
is  
begin
    insert into inforwedding(namebride, namegroom) values(
        V_nameBride, v_namegroom
    ) returning idwedding into v_idWedding;
end;
---------------------
create or replace procedure sp_insert_staff(
    V_nameStaff in staff.nameStaff%type,
    V_NUMBERPHONE in staff.NUMBERPHONE%type,
    V_ADDRESS in staff.ADDRESS%type,
    V_IDENTITYCARD in staff.IDENTITYCARD%type,
    V_TYPESTAFF in staff.TYPESTAFF%type,
    V_return out varchar2
)
is 
    StaffBeforeInsert number(2);
    StaffAfterInsert number(2);
begin
    /*so nhan vien truoc khi in*/
    select count(*) into StaffBeforeInsert from Staff;

    insert into Staff (nameStaff, numberPhone, address, IDENTITYCARD,TYPESTAFF) values(
        V_nameStaff, V_NUMBERPHONE, V_ADDRESS, V_IDENTITYCARD, V_TYPESTAFF
    );
    /*so nhan vien sau khi in*/
    select count(*) into StaffAfterInsert from Staff;

    /*neu nhan vien sau khi in lon hon truoc khi in la da in thanh cong*/
    if StaffAfterInsert > StaffBeforeInsert then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
-------------------------
create or replace procedure sp_insertBill(
    V_idStaff in Bill.idStaff%type,
    V_idCustomer in bill.idcustomer%type,
    V_idWedding in bill.idwedding%type,
    V_return out varchar2
)
is
begin
    insert into Bill(idStaff,idcustomer,idwedding) values(
        V_idStaff,V_idCustomer,V_idWedding
    ) returning idBill into v_return;
  
end;
-------------------------
create or replace procedure sp_insertFoodOrder(
    V_idFood in food.idfood%type,
    V_idWedding in inforwedding.idwedding%type,
    V_return out varchar2
)
is 
--    FoodOrderInsertBefore number(7);
--    FoodOrderInsertAfter number(7);
begin
--    select count(*) into FoodOrderInsertBefore from FoodOrder;
    insert into FoodOrder(idFood,idWedding) values(
        V_idFood,V_idWedding
    );
--    select count(*) into FoodOrderInsertAfter from FoodOrder;
--    
--    if foodorderinsertafter > FoodOrderInsertBefore then
--        V_return := 'true';
--    else
--        V_return := 'false';
--    end if;
    
    IF SQL%ROWCOUNT = 0 THEN
        v_return := 'false';
    else v_return := 'true';
    END IF;
end;
---------------------
create or replace procedure sp_insertLobby(
    V_NAMELOBBY in Lobby.NAMELOBBY%type,
    V_LOBBYTYPE in Lobby.LOBBYTYPE%type,
    V_MAXTABLE in Lobby.MAXTABLE%type,
    V_PRICETABLE in Lobby.PRICETABLE%type,
    V_PRICELOBBY in Lobby.PRICELOBBY%type,
    V_return out varchar2
)
is  
    lobbyBeforeInsert number(4);
    lobbyAfterInsert number(4);
begin
    select count(*) into lobbyBeforeInsert from Lobby;
    insert into Lobby(NAMELOBBY,LOBBYTYPE,MAXTABLE,PRICETABLE,PRICELOBBY) values(
        V_NAMELOBBY,V_LOBBYTYPE,V_MAXTABLE,V_PRICETABLE,V_PRICELOBBY
    );
    select count(*) into lobbyAfterInsert from Lobby;
    if lobbyAfterInsert > lobbyBeforeInsert then
        V_return := 'true';
    else 
        V_return := 'false';
    end if;
end;
------------------------
create or replace procedure sp_insertOrderWedding(
    V_idWedding in InforWedding.idWedding%type,
    V_nameCus in customer.namecustomer%type,
    V_numberPhone in customer.numberphone%type,
    V_IDLOBBY in OrderWedding.IDLOBBY%type,
    V_IDSTAFF in OrderWedding.IDSTAFF%type,
    V_NUMBEROFTABLE in OrderWedding.NUMBEROFTABLE%type,
    V_DATESTART in OrderWedding.DATESTART%type,
    V_return out varchar2,
    V_money out number,
    V_deposit out number
)
is 
    OrderWeddingTmp number(1);
    CustomerTmp number(1);
    GetIdCustomer varchar(8);
    OrderTmp number(1);
begin
    select count(*) into CustomerTmp from Customer where V_numberPhone = numberPhone;
    if CustomerTmp = 0 then
        insert into Customer(nameCustomer,numberPhone) values(
            V_nameCus,V_numberPhone
        );
    end if;
    select idCustomer into GetIdCustomer from Customer where V_numberPhone = numberPhone;
    
    insert into OrderWedding (IDWEDDING,IDLOBBY,IDSTAFF,IDCUSTOMER,NUMBEROFTABLE,DATESTART) values(
        V_idWedding,V_IDLOBBY,V_IDSTAFF,GetIdCustomer,V_NUMBEROFTABLE,V_DATESTART
    ) returning money into V_money;
    V_deposit := V_money*0.3;
    
    select count(*) into OrderTmp from OrderWedding where idwedding = V_idWedding;
    
    if OrderTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
----------------------
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
-------------------------
create or replace procedure sp_insertService (
    V_nameService in service.nameService%type,
    V_price in service.price%type,
    V_return out varchar2
)
is
    serviceBeforeInsert number(4);
    serviceAfterInsert number(4);
begin
    select count(*) into serviceBeforeInsert from Service;
    insert into service(nameService,price) values(
        V_nameService, V_price
    );
    select count(*) into serviceAfterInsert from service;
    if serviceAfterInsert > serviceBeforeInsert then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
-------------------------
create or replace procedure sp_insertServiceOrder(
    V_idService in ServiceOrder.idService%type,
    V_idWedding in ServiceOrder.idWedding%type,
    V_return out varchar2
)
is  
--ServiceOrderTmp number(1);
begin
    insert into ServiceOrder(idService,idWedding) values(
        V_idService,V_idWedding
    );
--    select count(*) into ServiceOrderTmp from ServiceOrder where idService = V_idService and idWedding = V_idWedding;
--    if ServiceOrderTmp = 1 then
--        V_return := 'true';
--    else
--        V_return := 'false';
--    end if;
    IF SQL%ROWCOUNT = 0 THEN
        v_return := 'false';
    else v_return := 'true';
    END IF;
end;
----------------------------------
create or replace procedure sp_Login (
    v_username in  account.username%type, 
    v_password in account.password%type,
    v_typestaff out staff.TYPESTAFF%type,
    v_idStaff out staff.idstaff%type
)
is
begin
    select staff.typestaff,  staff.idstaff  into v_typestaff, v_idStaff 
    from staff, account
    where staff.idstaff = account.idstaff and v_username=account.username and v_password = account.password;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        v_typestaff := '' ;
        v_idStaff :='';
end;
--------------------------------
create or replace procedure sp_numberOfLobby(
    V_idLobby in Lobby.idLobby%type,
    numberOfLobby out number
)
is
begin
    select count(*) into numberOfLobby from OrderWedding where idLobby = V_idLobby;
end;
------------------------------------
create or replace procedure sp_resetPasswordStaff(
    v_numberphone in account.username%type,
    V_return out varchar2
)
is result account.username%type;
begin

    update account set password = '123456' where v_numberphone=username;

    IF SQL%ROWCOUNT = 0 THEN
        v_return := 'false';
    else v_return := 'true';
    END IF;

end;
----------------------------------------------------
create or replace procedure sp_update_staff(
    v_idStaff in staff.idStaff%type,
    V_nameStaff in staff.nameStaff%type,
    V_ADDRESS in staff.ADDRESS%type,
    V_TYPESTAFF in staff.TYPESTAFF%type,
    V_return out varchar2
)
is 
    staffTmp number(1);
begin
    UPDATE staff
    SET 
        nameStaff = v_nameStaff,
        ADDRESS = V_ADDRESS,
        TYPESTAFF = V_TYPESTAFF
    WHERE v_idStaff=idStaff;
    select count(*) into staffTmp from Staff where nameStaff = v_nameStaff and ADDRESS = V_ADDRESS and TYPESTAFF = V_TYPESTAFF;
    DBMS_OUTPUT.PUT_LINE(staffTmp);
    if staffTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
------------------------------------------
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
-------------------------------------------
create or replace procedure sp_updateCustomer (
    V_idCus in customer.idCustomer%type,
    V_nameCus in customer.NameCustomer%type,
    V_numberPhone in customer.numberPhone%type,
    V_return out varchar2
)
is  cusTmp number(1);
begin
    update customer set
        nameCustomer = V_nameCus,
        numberPhone = V_numberPhone
    where idCustomer = V_idCus;
    select count(*) into cusTmp from customer where V_idCus = idCustomer and nameCustomer = V_nameCus and numberPhone = V_numberPhone;
    if cusTmp = 1 then
        v_return := 'true';
    else
        V_return := 'false';
    end if;
end;
----------------------------------------------
create or replace procedure sp_updateFood(
    V_idFood in food.idFood%type,
    V_nameFood in food.nameFood%type,
    V_priceFood in food.priceFood%type,
    V_typeFood in food.typeFood%type,
    V_return out varchar2
)
is
    foodTmp number(1);
begin
    update food 
    set
        namefood = V_nameFood,
        priceFood = V_priceFood,
        typeFood = V_typeFood
    where idFood = V_idFood;
    select count(*) into foodTmp from Food where v_idFood=idFood and namefood = V_nameFood and priceFood = V_priceFood and typeFood = V_typeFood;

    if foodTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
--------------------------------------------
create or replace procedure sp_updateInforWedding(
    V_idWedding in InforWedding.idWedding%type,
    V_nameBride in InforWedding.nameBride%type,
    V_nameGroom in InforWedding.nameGroom%type,
    V_return out varchar2
)
is  InforWeddingTmp number(1);
begin
    update InforWedding set
        nameBride = V_nameBride,
        nameGroom = V_nameGroom
    where idWedding = V_idWedding;
    select count(*) into InforWeddingTmp from InForWedding where idWedding = V_idWedding and nameBride = V_nameBride and nameGroom = V_nameGroom;
    if InforWeddingTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
--------------------------------------
create or replace procedure sp_UpdateLobby(
    V_idLobby in Lobby.idLobby%type,
    V_NAMELOBBY in Lobby.NAMELOBBY%type,
    V_MAXTABLE in Lobby.MAXTABLE%type,
    V_PRICETABLE in Lobby.PRICETABLE%type,
    V_PRICELOBBY in Lobby.PRICELOBBY%type,
    V_LOBBYTYPE in Lobby.LOBBYTYPE%type,
    V_return out varchar2
)
is LobbyTmp number(1);
begin
    update Lobby set
        NAMELOBBY = V_NAMELOBBY,
        MAXTABLE = V_MAXTABLE,
        PRICETABLE = V_PRICETABLE,
        PRICELOBBY = V_PRICELOBBY,
        LOBBYTYPE = V_LOBBYTYPE
    where idLobby = V_idLobby;
    select count(*) into LobbyTmp from Lobby
    where idLobby = V_idLobby and NAMELOBBY = V_NAMELOBBY and MAXTABLE = V_MAXTABLE and PRICETABLE = V_PRICETABLE 
    and PRICELOBBY = V_PRICELOBBY and LOBBYTYPE = V_LOBBYTYPE;
    if LobbyTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
-----------------------------------------
create or replace procedure sp_updateOrderWedding(
    V_IDWEDDING in OrderWedding.idWedding%type,
    V_IDLOBBY in OrderWedding.IDLOBBY%type,
    V_IDSTAFF in OrderWedding.IDSTAFF%type,
    V_NUMBEROFTABLE in OrderWedding.NUMBEROFTABLE%type,
    V_DATESTART in OrderWedding.DATESTART%type,
    V_return out varchar2
)
is OrderWeddingTmp number(1);
begin
    update OrderWedding set
        IDLOBBY = V_IDLOBBY,
        IDSTAFF = V_IDSTAFF,
        NUMBEROFTABLE = V_NUMBEROFTABLE,
        DATESTART = V_DATESTART
    where idWedding = V_IDWEDDING;
    select count(*) into OrderWeddingTmp from OrderWedding where IDLOBBY = V_IDLOBBY and  IDSTAFF = V_IDSTAFF and NUMBEROFTABLE = V_NUMBEROFTABLE and idWedding = V_IDWEDDING;
    if OrderWeddingTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;
--------------------------------------
create or replace procedure sp_updateService(
    V_idService in service.idService%type,
    V_nameService in service.nameService%type,
    V_price in service.price%type,
    V_return out varchar2
)
is serviceTmp number(1);
begin
    update Service
    set 
        nameService = V_nameService,
        price = V_price
    where idService = v_idservice;
    select count(*) into servicetmp from service where idService = v_idservice and price = V_price and nameService = V_nameService;
    if serviceTmp = 1 then
        V_return := 'true';
    else 
        V_return := 'false';
    end if;
end;