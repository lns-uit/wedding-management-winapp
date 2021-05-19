create or replace NONEDITIONABLE procedure sp_update_staff(
    v_idStaff in staff.idStaff%type,
    V_nameStaff in staff.nameStaff%type,
    V_ADDRESS in staff.ADDRESS%type,
    V_TYPESTAFF in staff.TYPESTAFF%type,
    V_return out boolean
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
    if staffTmp = 1 then
        V_return := true;
    else
        V_return := false;
    end if;
end;