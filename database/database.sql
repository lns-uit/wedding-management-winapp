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
    stt number(7) not null,
    idLobby varchar(8),
    nameLobby varchar(30),
    lobbyType varchar(7),
    maxTable number(4),
    priceTable number(9),
    priceLobby number(9),
    status varchar(10),
    note varchar(100),
    constraint Lobby_pk primary key (idLobby)
);
create table Service(
    stt number(7) not null,
    idService varchar2(8),
    nameService varchar2(30),
    price number(9),
    note varchar2(100),
    constraint Service_pk primary key (idService)
);
create table Food(
    stt number(7) not null,
    idFood varchar2(8),
    nameFood varchar2(30),
    priceFood number(9),
    note varchar2(100),
    constraint Food_pk primary key (idFood)
);
create table InforWedding(
    stt number(7) not null,
    idWedding varchar(8),
    nameBride varchar(30),
    nameGroom varchar(30),
    constraint InforWedding_pk primary key (idWedding)
);
create table OrderWedding(
    stt number(7) not null,
    idWedding varchar2(8),
    idLobby varchar2(8),
    idStaff varchar2(9),
    idCustomer varchar2(8),
    numberOfFood number(3),
    numberOfService number(3),
    deposit number(12),
    money number(12),
    numberOfTable number(10),
    dateOrderDate date,
    dateStart  date,
    constraint OrderWedding_pk primary key (idWedding,idLobby,idStaff,idCustomer)
);
create table ListWedding(
    stt number(7) not null,
    idWedding varchar2(8),
    constraint ListWedding_pk primary key (idWedding)
);
create table Staff(
    stt number(7) not null,
    idStaff varchar2(9),
    nameStaff varchar2(30),
    numberPhone varchar2(10),
    address varchar2(50),
    identityCard varchar2(9),
    startWork date,
    typeStaff varchar2(10),
    constraint Staff_pk primary key (idStaff)
);
create table Bill(
    stt number(7) not null,
    idBill varchar2(8),
    idStaff varchar2(9),
    idCustomer varchar2(8),
    idWedding varchar(8),
    money number(10),
    dateOfPayment date,
    constraint Bill_pk primary key (idBill)
);
create table Customer(
    stt number(7) not null,
    idCustomer varchar2(8),
    nameCustomer varchar2(30),
    numberPhone varchar2(10),
    address varchar2(50),
    birthDay date,
    dayRegister date,
    money number(12),
    discount number(3),
    constraint Customer_pk primary key (idCustomer)
);
create table Account(
    stt number(7) not null,
    idAccount varchar2(10),
    idStaff varchar2(9),
    userName varchar2(20),
    password varchar2(20),
    status varchar(20),
    constraint Account_pk primary key (idAccount)
);
create table Report(
    stt number(7) not null,
    idReport varchar2(9),
    totalBillOfMonth number(12),
    closingDate date,
    constraint Reports_pk primary key (idReport)
);  
create table ServiceOrder(
    stt number(7) not null,
    idService varchar2(8),
    idWedding varchar2(8),
    constraint ServiceOrder_pk primary key (idService,idWedding)
);

create table FoodOrder(
    stt number(7) not null,
    idFood varchar2(8),
    idWedding varchar2(8),
    constraint FoodOrder_pk primary key (idFood,idWedding)
);
/*create foreign*/
alter table OrderWedding add constraint OrderWedding_Lobby_fk foreign key(idLobby) references Lobby(idLobby);
alter table OrderWedding add constraint OrderWedding_Customer_fk foreign key(idCustomer) references Customer(idCustomer);
alter table OrderWedding add constraint OrderWedding_Staff_fk foreign key(idStaff) references Staff(idStaff);
alter table OrderWedding add constraint OrderWedding_InforWedding_fk foreign key(idWedding) references InforWedding(idWedding);
alter table ListWedding add constraint ListWedding_InforWedding_fk foreign key (idWedding) references InforWedding(idWedding);
alter table Bill add constraint Bill_Staff_fk foreign key (idStaff) references Staff(idStaff);
alter table Bill add constraint Bill_Customer_fk foreign key (idCustomer) references Customer(idCustomer);
alter table ServiceOrder add constraint ServiceOrder_InforWedding_fk foreign key (idWedding) references InforWedding(idWedding);
alter table ServiceOrder add constraint ServiceOrder_Service_fk foreign key (idService) references Service(idService);
alter table FoodOrder add constraint FoodOrder_InforWedding_fk foreign key (idWedding) references InforWedding(idWedding);
alter table FoodOrder add constraint FoodOrder_Service_fk foreign key (idFood) references Food(idFood);
alter table Account add constraint Account_Staff_fk foreign key (idStaff) references Staff(idStaff);
/*create trigger*/
/*trigger Lobby*/
create or replace trigger Lobby_stt_and_id
before insert on Lobby
for each row
declare tmp number(7);
begin
    tmp := Lobby_stt.nextval;
    :new.stt := tmp;
    :new.idLobby := concat('LOB',to_char(tmp));
end;

SET SERVEROUTPUT ON
create or replace trigger Order_Wedding
before insert on OrderWedding
for each row
declare 
    statusTmp varchar2(10);
    maxTableTmp number(4);
    priceLobbyTmp number(10);
    priceTableTmp number(10);
    sumService number(12);
    sumFood number(12);
    tmp number(7);
    numberOfFoodTmp number(3);
    numberOfServiceTmp number(3);
begin
    select count(*) into numberOfFoodTmp from FoodOrder where idWedding = :new.idWedding;
    select count(*) into numberOfServiceTmp from ServiceOrder where idWedding = :new.idWedding;
    select maxTable into maxTableTmp from Lobby where idLobby = :new.idLobby;
    select status into statusTmp from Lobby where idLobby = :new.idLobby;
    select priceLobby into priceLobbyTmp from Lobby where idLobby = :new.idLobby;
    select priceTable into priceTableTmp from Lobby where idLobby = :new.idLobby;
    select Sum(price) into sumService from Service, ServiceOrder where Service.idService = ServiceOrder.idService and ServiceOrder.idWedding = :new.idWedding;
    select Sum(priceFood) into sumFood from Food, FoodOrder where Food.idFood = FoodOrder.idFood and FoodOrder.idWedding = :new.idWedding;
    if statusTmp = 'ON' then
        DBMS_OUTPUT.PUT_LINE('sanh nay da co nguoi dat');
        return;
    else
        if maxTableTmp > :new.numberOfTable then
            tmp := Order_Wedding_stt.nextval;
            :new.stt := tmp;
            :new.numberOfFood := numberOfFoodTmp;
            :new.NUMBEROFSERVICE := numberOfServiceTmp;
            :new.money := priceLobbyTmp+(priceTableTmp+sumFood)*:new.numberOfTable + sumService;
            :new.deposit := :new.money*0.3;
            update Lobby set status = 'ON' where idLobby = :new.idLobby;
        else
            DBMS_OUTPUT.PUT_LINE('so luong ban vuot qua gioi han');
            return;
        end if;
    end if;
end;

create or replace trigger InforWedding_stt_and_id
before insert on InforWedding
for each row
declare tmp number(7);
begin
    tmp := InforWedding_stt.nextval;
    :new.stt := tmp;
    :new.idWedding := concat('WED',to_char(tmp));
end;

create or replace trigger Staff_stt_and_id
before insert on Staff
for each row
declare tmp number(7);
begin
    tmp := Staff_stt.nextval;
    :new.stt := tmp;
    :new.idStaff := concat('ST',to_char(tmp));
end;

create or replace trigger Customer_stt_and_id
before insert on Customer
for each row
declare 
    tmp number(7);
    birthDay number(5);
    dayRegisters number(5);
begin
    tmp := Customer_stt.nextval;
    :new.stt := tmp;
    :new.idCustomer := concat('CUS',to_char(tmp));
    :new.money := 0;
    if :new.money > 100000000 then
        :new.discount := 20;
    else
        :new.discount := 0;
    end if;
end;

create or replace trigger Account_stt_and_id
After insert on Staff
for each row
declare tmp number(7);
begin
    tmp := Account_stt.nextval;
    insert into Account values(
        tmp, concat('Acc',tmp) , :new.idStaff, :new.numberPhone, '123456', 'OFF'
    );
end;

create or replace trigger Service_stt_and_id
before insert on Service
for each row
declare tmp number(7);
begin
    tmp := Service_Stt.nextval;
    :new.stt := tmp;
    :new.idService := concat('SER',to_char(tmp));
end;

create or replace trigger Food_stt_and_id
before insert on Food
for each row
declare tmp number(7);
begin
    tmp := Food_stt.nextval;
    :new.stt := tmp;
    :new.idFood := concat('F',to_char(tmp));
end;

create or replace trigger ServiceOrder_stt_and_id
before insert on ServiceOrder
for each row
declare tmp number(7);
begin
    tmp := Service_Order_stt.nextval;
    :new.stt := tmp;
end;

create or replace trigger FoodOrder_stt_and_id
before insert on FoodOrder
for each row
declare tmp number(7);
begin
    tmp := Food_Order_stt.nextval;
    :new.stt := tmp;
end;


create or replace trigger ListWeddings
before insert on ListWedding
for each row
declare tmp number(7);
begin
    tmp := List_Wedding_STT.nextval;
    :new.stt := tmp;
    :new.status := 'ON';
end;

create or replace trigger UpdateListWedding
before update on ListWedding
for each row
declare idLobbyTmp varchar2(8);
begin
    select idLobby into idLobbyTmp from OrderWedding where :old.idWedding = idWedding;
    if :new.status = 'OFF' then
        update Lobby set status = 'OFF' where idLobbyTmp = idLobby;
    end if;
end;

create or replace trigger Bill
before insert on Bill
for each row
declare 
    tmp number(7);
    moneyPayment number(14);
begin
    select money into moneyPayment from OrderWedding where idWedding = :new.idWedding;
    tmp := Bill_stt.nextval;
    :new.stt := tmp;
    :new.idBill := concat('B',tmp);
    :new.money := moneyPayment;
    update Customer set money = money + moneyPayment where idCustomer = :new.idCustomer;
end;

create or replace trigger UpdateCustomer
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

create or replace trigger Reports
before insert on Report
for each row
declare
    tmp number(7);
    sumReport number(13);
    monthlyTmp number(2);
    yearTmp number(4);
begin
    tmp := report_monthly_stt.nextval;
    :new.stt := tmp;
    :new.idReport := concat('RE',tmp);
    select SUM(money) into sumReport from Bill 
    where EXTRACT(YEAR FROM dateOfPayment) = EXTRACT(YEAR FROM :new.closingDate) and EXTRACT(MONTH FROM dateOfPayment) = EXTRACT(MONTH FROM :new.closingDate);
    :new.TOTALBILLOFMONTH := sumReport;
end;
/*insert Lobby*/
insert into Lobby (nameLobby,lobbyType,maxTable,priceTable,priceLobby,status,note) values(
   'thien duong', 'vip', 40, 1000000, 10000000, 'OFF', 'nothing' 
);
insert into Lobby (nameLobby,lobbyType,maxTable,priceTable,priceLobby,status,note) values(
   'thien duong', 'thuong', 30, 500000, 5000000, 'OFF', 'nothing' 
);
insert into Lobby (nameLobby,lobbyType,maxTable,priceTable,priceLobby,status,note) values(
   'Co Dien', 'vip', 70, 2000000, 50000000, 'OFF', 'nothing' 
);
insert into Lobby (nameLobby,lobbyType,maxTable,priceTable,priceLobby,status,note) values(
   'Co Dien', 'thuong', 50, 1500000, 20000000, 'OFF', 'nothing' 
);
/*insert Food*/
insert into Food(nameFood,priceFood) values(
    'soul', 50000
);
insert into Food(nameFood,priceFood) values(
    'cua', 400000
);
insert into Food(nameFood,priceFood) values(
    'soi ga', 100000
);
insert into Food(nameFood,priceFood) values(
    'rau cau', 20000
);
/*insert Service*/
insert into Service(nameService,price) values(
    'ca hat', 5000000
);
insert into Service(nameService,price) values(
    'ao thuat', 2000000
);
insert into Service(nameService,price) values(
    'nhac song', 3000000
);
/*insert Staff*/
insert into Staff (nameStaff, numberPhone, address, IDENTITYCARD,STARTWORK,TYPESTAFF,birthday) values(
    'Tran Dinh  Khoi', '0792545700', 'sadasdas', '3213123', '19-MAR-2025', 'admin', '14-FEB-2001'
);
insert into Staff (nameStaff, numberPhone, address, IDENTITYCARD,STARTWORK,TYPESTAFF,birthday) values(
    'Do Thanh Van', '0792545701', 'sadasas', '1213123', '19-MAR-2023', 'nhan vien', '15-FEB-2001'
);
insert into Staff (nameStaff, numberPhone, address, IDENTITYCARD,STARTWORK,TYPESTAFF,birthday) values(
    'Nguyen Van A', '0792545702', 'sadasas', '1213123', '19-MAR-2023', 'quan ly', '15-FEB-2001'
);
/*insert cus*/
insert into Customer(nameCustomer,numberPhone,ADDRESS,BIRTHDAY,DAYREGISTER) values(
    'Tran Dinh Khoi', '0792545708', 'ádfasdfasdf', '14-FEB-2001', '14-MAR-2020'
);
/*insert infor wedding*/
insert into InForWedding(nameBride, nameGroom) values(
    'A', 'B'
);
insert into InForWedding(nameBride, nameGroom) values(
    'C', 'D'
);
insert into InForWedding(nameBride, nameGroom) values(
    'GK', 'SD'
);
/*insert service order*/
insert into ServiceOrder(IDSERVICE,IDWEDDING) values(
    'SER1', 'WED1'
);
insert into ServiceOrder(IDSERVICE,IDWEDDING) values(
    'SER2', 'WED1'
);
insert into ServiceOrder(IDSERVICE,IDWEDDING) values(
    'SER1', 'WED2'
);
insert into ServiceOrder(IDSERVICE,IDWEDDING) values(
    'SER3', 'WED2'
);
insert into ServiceOrder(IDSERVICE,IDWEDDING) values(
    'SER1', 'WED3'
);
insert into ServiceOrder(IDSERVICE,IDWEDDING) values(
    'SER3', 'WED3'
);
/*insert Food Order*/
insert into FoodOrder(IDFOOD,IDWEDDING) values(
    'F1', 'WED1'
);
insert into FoodOrder(IDFOOD,IDWEDDING) values(
    'F2', 'WED1'
);
insert into FoodOrder(IDFOOD,IDWEDDING) values(
    'F3', 'WED2'
);
insert into FoodOrder(IDFOOD,IDWEDDING) values(
    'F1', 'WED2'
);
insert into FoodOrder(IDFOOD,IDWEDDING) values(
    'F3', 'WED3'
);
insert into FoodOrder(IDFOOD,IDWEDDING) values(
    'F1', 'WED3'
);
/*insert OrderWedding*/
insert into OrderWedding(IDWEDDING,IDLOBBY,IDSTAFF,IDCUSTOMER,NUMBEROFTABLE,DATEORDERDATE,DATESTART) values(
    'WED1', 'LOB1', 'ST4', 'CUS1', 20,'14-FEB-2001','16-FEB-2001'
);
insert into OrderWedding(IDWEDDING,IDLOBBY,IDSTAFF,IDCUSTOMER,NUMBEROFTABLE,DATEORDERDATE,DATESTART) values(
    'WED2', 'LOB3', 'ST1', 'CUS1', 10,'14-FEB-2001','16-FEB-2001'
);
insert into OrderWedding(IDWEDDING,IDLOBBY,IDSTAFF,IDCUSTOMER,NUMBEROFTABLE,DATEORDERDATE,DATESTART) values(
    'WED3', 'LOB4', 'ST4', 'CUS1', 10,'14-FEB-2001','16-FEB-2001'
);
/*insert ListWedding*/
insert into ListWedding(idWedding) values(
    'WED1'
);
insert into ListWedding(idWedding) values(
    'WED2'
);
insert into ListWedding(idWedding) values(
    'WED3'
);
update ListWedding set status = 'OFF' where idWedding = 'WED1';
/*insert bill*/
insert into Bill(IDSTAFF,IDCUSTOMER,IDWEDDING,DATEOFPAYMENT) values(
    'ST4','CUS1','WED1', '14-FEB-2020'
);
insert into Bill(IDSTAFF,IDCUSTOMER,IDWEDDING,DATEOFPAYMENT) values(
    'ST4','CUS1','WED2', '14-FEB-2020'
);
insert into Bill(IDSTAFF,IDCUSTOMER,IDWEDDING,DATEOFPAYMENT) values(
    'ST4','CUS1','WED3', '16-FEB-2020'
);
/*insert report*/
insert into Report(CLOSINGDATE) values(
    '28-FEB-2020'
);