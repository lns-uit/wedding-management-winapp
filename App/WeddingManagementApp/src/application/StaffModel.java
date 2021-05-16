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
				String idStaff = rs.getString(1);
				String nameStaff = rs.getString(2);
				String phoneNumberStaff = rs.getString(3);
				String addRessStaff = rs.getString(4);
				String identityCardStaff = rs.getString(5);
				String startWorkStaff = rs.getString(6);
				String typeStaff = rs.getString(7);
				
				a.setId(idStaff);
				a.setName(nameStaff);
				a.setPhoneNumber(phoneNumberStaff);
				a.setIdentityCard(identityCardStaff);
				a.setStartWork(startWorkStaff);
				a.setType(typeStaff);
				
				arrStaff.add(a);
				System.out.println(idStaff+ nameStaff+phoneNumberStaff + addRessStaff+identityCardStaff+startWorkStaff+typeStaff);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		cStmt.close();
		return arrStaff;
	}
}
