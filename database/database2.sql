SET SERVEROUTPUT ON 
create or replace NONEDITIONABLE procedure sp_update_staff(
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

create or replace procedure sp_deleteStaff(
    V_idStaff in staff.idStaff%type,
    V_return out varchar2
)
is
    staffTmp number(1);
begin
    delete from Staff where idStaff = V_idStaff;
    select count(*) into staffTmp from Staff where idStaff = V_idStaff;
    if staffTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

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
    select count(*) into foodTmp from Food where namefood = V_nameFood and priceFood = V_priceFood and typeFood = V_typeFood;
    
    if foodTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace trigger deleteStaff
before delete on Staff
for each row
begin
    delete from Account where :old.idStaff = idStaff;
end;

create or replace procedure sp_deleteFood (
    V_idFood in food.idFood%type,
    V_return out varchar2
)
is foodTmp number(1);
begin
    delete from Food where idFood = V_idFood;
    select count(*) into foodTmp from Food Where idFood = V_idFood;
    if foodTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace trigger deleteFood
before delete on Food
for each row
begin
    delete from FoodOrder where idFood = :old.idFood;
end;

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

create or replace procedure sp_updateService(
    V_idService in service.idService%type,
    V_nameService in service.nameService%type,
    V_price in service.price%type,
    V_return out varchar2
)
is serviceTmp number(1);
begin
    update Service
    set nameService = V_nameService,
        price = V_price
    where idService = v_idservice;
    select count(*) into servicetmp from service where idService = v_idservice and price = V_price and nameService = V_nameService;
    if serviceTmp = 1 then
        V_return := 'true';
    else 
        V_return := 'false';
    end if;
end;

create or replace procedure sp_deteService(
    V_idService in service.idService%type,
    V_return out varchar2
)
is   serviceTmp number(1);
begin
    delete from service where idService = v_idservice;
    select count(*) into serviceTmp from service where idService = v_idservice;
    if serviceTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace trigger deleteService 
before delete on Service
for each row
begin
    delete from ServiceOrder where idservice = :old.idservice;
end;