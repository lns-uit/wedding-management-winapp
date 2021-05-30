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

create or replace trigger trg_deleteService
before delete on Service
for each row
declare serviceActive number(5);
begin
    select count(*) into serviceActive from Service where idService = :old.idService;
    if serviceActive > 0 then
        RAISE_APPLICATION_ERROR(-20000,'dich vu nay da co nguoi dat khong the xoa');
    end if;
end;

create or replace trigger trg_deleteLobby
before delete on Lobby
for each row
declare lobbyActive number(5);
begin
    select count(*) into LobbyActive from OrderWedding where idLobby = :old.idLobby and active = 'ON';
    if LobbyActive > 0 then
         RAISE_APPLICATION_ERROR(-20000,'sanh da duoc dat khong the xoa');
    else
        update OrderWedding set idLobby = null where idLobby = :old.idLobby;
    end if;
end;