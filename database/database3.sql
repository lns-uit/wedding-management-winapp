insert into staff(nameStaff) values(
    'Vô Danh'
);

create or replace procedure sp_insertCustomer(
    V_nameCus in customer.namecustomer%type,
    V_NUMBERPHONE in customer.NUMBERPHONE%type,
    V_return out varchar2
)
is  
    customerBeforeInsert number(4);
    customerAfterInsert number(4);
begin
    select count(*) into customerBeforeInsert from customer;
    insert into customer(namecustomer,numberphone) values(
        V_nameCus,V_NUMBERPHONE
    );
    select count(*) into customerAfterInsert from customer;
    if customerAfterInsert > customerBeforeInsert then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_updateCustomer (
    V_idCus in customer.idCustomer%type,
    V_nameCus in customer.idNameCustomer%type,
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

create or replace procedure sp_deleteCustomer(
    V_idCus in customer.idCustomer%type,
    V_return out varchar2
)
is cusTmp number(1);
begin
    delete from customer where idcustomer = V_idcus;
    select count(*) into cusTmp from customer where idcustomer = V_idcus;
    if cusTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;