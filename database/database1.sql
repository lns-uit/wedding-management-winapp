alter table report add totalWeddingOfMonth number(5);

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
        V_return := 'thêm thành công';
    else
        V_return := 'thêm không thành công';
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
        V_return := 'them nhan vien thanh cong';
    else
        V_return := 'them khong thanh cong';
    end if;
end;