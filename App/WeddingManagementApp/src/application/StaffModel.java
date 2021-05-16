package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

public class StaffModel {
	
	public static ArrayList<Staff> getAllStaff() throws SQLException {
		String sqlString = "begin SP_getAllStaff(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<Staff> arrStaff = new ArrayList<Staff>();
		
		try {
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);
			
			while (rs.next()) {
				Staff a = new Staff();
				String idStaffColumn = rs.getString(1);
				String nameStaffColumn = rs.getString(2);
				String bornDateStaffColumn = rs.getString(3);
				String addRessStaffColumn = rs.getString(4);
				String phoneNumberStaffColumn = rs.getString(5);
				String emailStaffColumn = rs.getString(6);
				String typeStaffColumn = rs.getString(7);
				
				a.setId(idStaffColumn);
				a.setName(nameStaffColumn);
				a.setBornDate(bornDateStaffColumn);
				a.setPhoneNumber(phoneNumberStaffColumn);
				a.setEmail(emailStaffColumn);
				a.setType(typeStaffColumn);
				
				arrStaff.add(a);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		cStmt.close();
		return arrStaff;
	}
}
