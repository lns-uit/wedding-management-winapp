/*create user 
    *note: nh? ??a v? system m?i thêm ???c user không thì b? l?i : )
*/
alter session set "_ORACLE_SCRIPT"=true;  
create user KhoiProject identified by khoild11;

grant connect, resource, oem_monitor to KhoiProject;
alter user KhoiProject quota 100M on 'USERS';
grant unlimited tablespace to KhoiProject;
/*create sequence*/
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
    numberOf number(3),
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
    deposit number(10),
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
    idOrderWedding varchar(8),
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
    idBill varchar2(8),
    idService varchar2(8),
    idWedding varchar2(8),
    constraint ServiceOrder_pk primary key (idService,idBill,idWedding)
);

create table FoodOrder(
    stt number(7) not null,
    idBill varchar2(8),
    idFood varchar2(8),
    idWedding varchar2(8),
    constraint FoodOrder_pk primary key (idBill,idFood,idWedding)
);

alter table OrderWedding add constraint OrderWedding_Lobby_fk foreign key(idLobby) references Lobby(idLobby);
alter table OrderWedding add constraint OrderWedding_Customer_fk foreign key(idCustomer) references Customer(idCustomer);
alter table OrderWedding add constraint OrderWedding_Staff_fk foreign key(idStaff) references Staff(idStaff);
alter table OrderWedding add constraint OrderWedding_InforWedding_fk foreign key(idWedding) references InforWedding(idWedding);
alter table ListWedding add constraint ListWedding_InforWedding_fk foreign key (idWedding) references InforWedding(idWedding);
alter table Bill add constraint Bill_Staff_fk foreign key (idStaff) references Staff(idStaff);
alter table Bill add constraint Bill_Customer_fk foreign key (idCustomer) references Customer(idCustomer);
alter table ServiceOrder add constraint ServiceOrder_Bill_fk foreign key (idBill) references Bill(idBill);
alter table ServiceOrder add constraint ServiceOrder_InforWedding_fk foreign key (idWedding) references InforWedding(idWedding);
alter table ServiceOrder add constraint ServiceOrder_Service_fk foreign key (idService) references Service(idService);
alter table FoodOrder add constraint FoodOrder_Bill_fk foreign key (idBill) references Bill(idBill);
alter table FoodOrder add constraint FoodOrder_InforWedding_fk foreign key (idWedding) references InforWedding(idWedding);
alter table FoodOrder add constraint FoodOrder_Service_fk foreign key (idFood) references Food(idFood);
alter table Account add constraint Account_Staff_fk foreign key (idStaff) references Staff(idStaff);
insert into Staff values(
    1,'ST1','Tran Dinh Khoi', '0792545700','218/1/4 to ngoc van', '32141382', '08-MAR-90', 'nhan vien'
);
insert into Staff values(
    2,'ST2','Pham Manh Loi', '0792545701','216/1/4 to ngoc van', '32141282', '08-MAR-92', 'quan ly'
);

insert into Account values(
    '1', 'AC1', 'ST1' ,'0792545700', '123', 'chua dang nhap'
);
insert into Account values(
    '2', 'AC2', 'ST2' ,'0792545701', '123', 'chua dang nhap'
);