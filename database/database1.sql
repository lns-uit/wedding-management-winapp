alter table report add totalWeddingOfMonth number(5);
alter table Customer drop column ADDRESS;
alter table Customer drop column IDENTITYCARD;
alter table Customer drop column BIRTHDAY;
/*insert*/
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
    select count(*) into FoodBeforeInsert from Food;
    insert into food(nameFood,priceFood,typeFood) values(
        v_nameFood,v_priceFood,v_typeFood
    );
    select count(*) into FoodAfterInsert from Food;
    if FoodAfterInsert > FoodBeforeInsert then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace NONEDITIONABLE procedure sp_insert_staff(
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

create or replace NONEDITIONABLE procedure sp_getAllFood(cur_foodOut OUT SYS_REFCURSOR)
is
begin
    open cur_foodout for
    select * 
    from  food;
end;

create or replace NONEDITIONABLE procedure sp_getAllLobby(cur_lobbyOut OUT SYS_REFCURSOR)
is
begin
    open cur_lobbyOut for
    select * 
    from  lobby;
end;

create or replace NONEDITIONABLE procedure sp_getAllService(cur_serviceOut OUT SYS_REFCURSOR)
is
begin
    open cur_serviceOut for
    select * 
    from  service;
end;

create or replace NONEDITIONABLE procedure sp_getAllCustomer(cur_customerOut OUT SYS_REFCURSOR)
is
begin
    open cur_customerOut for
    select * 
    from  customer;
end;


create or replace NONEDITIONABLE procedure sp_getAllFoodOrder(cur_foodOrderOut OUT SYS_REFCURSOR)
is
begin
    open cur_foodOrderOut for
    select * 
    from  foodOrder;
end;


create or replace NONEDITIONABLE procedure sp_getAllInFoWed(cur_infoWedOut OUT SYS_REFCURSOR)
is
begin
    open cur_infoWedOut for
    select * 
    from  inforWedding;
end;

create or replace NONEDITIONABLE procedure sp_getAllWedding (cur_WeddingOut OUT SYS_REFCURSOR)
is
begin
    open cur_WeddingOut for
    select * 
    from  listwedding;
end;


create or replace NONEDITIONABLE procedure sp_getAllOrderWed (cur_orderWedOut OUT SYS_REFCURSOR)
is
begin
    open cur_orderWedOut for
    select * 
    from  orderwedding;
end;

create or replace NONEDITIONABLE procedure sp_getReport (cur_reportOut OUT SYS_REFCURSOR)
is
begin
    open cur_reportOut for
    select * 
    from  report;
end;

create or replace NONEDITIONABLE procedure sp_getServiceOrder (cur_setviceOrderOut OUT SYS_REFCURSOR)
is
begin
    open cur_setviceOrderOut for
    select * 
    from  serviceOrder;
end;
