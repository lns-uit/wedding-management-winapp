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

insert into customer (nameCustomer) values(
    'Vô Danh'
);

create or replace trigger deleteCustomer
before delete ON Customer
for each row
begin
    update OrderWedding set idCustomer = 'Cus2' where idcustomer = :old.idcustomer;
    update Bill set idCustomer = 'CUS2' where idcustomer = :old.idcustomer;
end;

create or replace procedure sp_insertLobby(
    V_NAMELOBBY in Lobby.NAMELOBBY%type,
    V_MAXTABLE in Lobby.MAXTABLE%type,
    V_PRICETABLE in Lobby.PRICETABLE%type,
    V_PRICELOBBY in Lobby.PRICELOBBY%type,
    V_NOTE in Lobby.NOTE%type,
    V_LOBBYTYPE in Lobby.LOBBYTYPE%type,
    V_return out varchar2
)
is  
    lobbyBeforeInsert number(4);
    lobbyAfterInsert number(4);
begin
    select count(*) into lobbyBeforeInsert from Lobby;
    insert into Lobby(NAMELOBBY,MAXTABLE,PRICETABLE,PRICELOBBY,NOTE,LOBBYTYPE) values(
        V_NAMELOBBY,V_MAXTABLE,V_PRICETABLE,V_PRICELOBBY,V_NOTE,V_LOBBYTYPE
    );
    select count(*) into lobbyAfterInsert from Lobby;
    if lobbyAfterInsert > lobbyBeforeInsert then
        V_return := 'true';
    else 
        V_return := 'false';
    end if;
end;

create or replace procedure sp_UpdateLobby(
    V_idLobby in Lobby.idLobby%type,
    V_NAMELOBBY in Lobby.NAMELOBBY%type,
    V_MAXTABLE in Lobby.MAXTABLE%type,
    V_PRICETABLE in Lobby.PRICETABLE%type,
    V_PRICELOBBY in Lobby.PRICELOBBY%type,
    V_NOTE in Lobby.NOTE%type,
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
        NOTE = V_NOTE,
        LOBBYTYPE = V_LOBBYTYPE
    where idLobby = V_idLobby;
    select count(*) into LobbyTmp from Lobby
    where idLobby = V_idLobby and NAMELOBBY = V_NAMELOBBY and MAXTABLE = V_MAXTABLE and PRICETABLE = V_PRICETABLE 
    and PRICELOBBY = V_PRICELOBBY and NOTE = V_NOTE and LOBBYTYPE = V_LOBBYTYPE;
    if LobbyTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_deleteLobby(
    V_idLobby in Lobby.idLobby%type,
    V_note in Lobby.note%type,
    V_return out varchar2
)
is lobbyTmp number(1);
begin
    update Lobby set ACTIVCE = 'false'  where V_idLobby = idLobby;
    select count(*) into lobbyTmp from Lobby where V_idLobby = idLobby and ACTIVCE = 'false'  ;
    if lobbyTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace trigger Lobby_stt_and_id
before insert on Lobby
for each row
declare tmp number(7);
begin
    tmp := Lobby_stt.nextval;
    :new.stt := tmp;
    :new.idLobby := concat('LOB',to_char(tmp));
    :new.ACTIVCE := 'true';
end;

create or replace NONEDITIONABLE procedure sp_getAllLobby(cur_lobbyOut OUT SYS_REFCURSOR)
is
begin
    open cur_lobbyOut for
    select * 
    from  lobby;
end;

create or replace procedure sp_insertFoodOrder(
    V_idFood in FoodOrder.idFood%type,
    V_idWedding in FoodOrder.idWedding%type,
    V_return out varchar2
)
is  foodOrderTmp number(1);
begin
    insert into FoodOrder(idFood,idWedding) values(
        V_idFood,V_idWedding
    );
    select count(*) into foodOrderTmp from FoodOrder where idFood = V_idFood and idWedding = V_idWedding;
    if foodOrderTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_deleteFoodOrder(
    V_idFood in FoodOrder.idFood%type,
    V_idWedding in FoodOrder.idWedding%type,
    V_return out varchar2
)
is foodOrderTmp number(1);
begin
    delete from FoodOrder where idFood = V_idFood and V_idWedding = idWedding;
    select count(*) into foodOrderTmp from FoodOrder where idFood = V_idFood and V_idWedding = idWedding;
    if foodOrderTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_insertServiceOrder(
    V_idService in FoodOrder.idService%type,
    V_idWedding in FoodOrder.idWedding%type,
    V_return out varchar2
)
is  ServiceOrderTmp number(1);
begin
    insert into FoodOrder(idService,idWedding) values(
        V_idService,V_idWedding
    );
    select count(*) into ServiceOrderTmp from FoodOrder where idService = V_idService and idWedding = V_idWedding;
    if ServiceOrderTmp = 1 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_deleteServiceOrder(
    V_idService in FoodOrder.idService%type,
    V_idWedding in FoodOrder.idWedding%type,
    V_return out varchar2
)
is ServiceOrderTmp number(1);
begin
    delete from FoodOrder where idService = V_idService and V_idWedding = idWedding;
    select count(*) into ServiceOrderTmp from FoodOrder where idService = V_idService and V_idWedding = idWedding;
    if ServiceOrderTmp = 0 then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;

create or replace procedure sp_insertInforWedding(
    V_nameBride in InforWedding.nameBride%type,
    V_nameGroom in InforWedding.nameGroom%type,
    V_return out varchar2
)
is  
    InforWeddingBeforeInsert number(4);
    InforWeddingAfterInsert number(4);
begin
    select count(*) into InforWeddingBeforeInsert from InforWedding;
    insert into InForWedding(nameBride,nameGroom) values(
        nameBride,nameGroom
    );
    select count(*) into InforWeddingAfterInsert from InforWedding;
    if InforWeddingAfterInsert > InforWeddingBeforeInsert then
        V_return := 'true';
    else
        V_return := 'false';
    end if;
end;