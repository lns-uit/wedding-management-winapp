package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.Const;

import oracle.jdbc.OracleTypes;


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
				String idStaff = rs.getString(2);
				String nameStaff = rs.getString(3);
				String phoneNumberStaff = rs.getString(4);
				String addRessStaff = rs.getString(5);
				String identityCardStaff = rs.getString(6);
				String startWorkStaff = rs.getString(7);
				String typeStaff = rs.getString(8);
				
				a.setId(idStaff);
				a.setName(nameStaff);
				a.setPhoneNumber(phoneNumberStaff);
				a.setIdentityCard(identityCardStaff);
				a.setStartWork(startWorkStaff);
				a.setType(typeStaff);
				a.setAddress(addRessStaff);
				if (!typeStaff.equals("admin")) {
					arrStaff.add(a);	
				}
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		cStmt.close();
		return arrStaff;
	}
	public static String findStaffByPhone(String phone) throws SQLException {
		String sqlString = "begin sp_findStaffByPhone(?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, phone);
			cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String resultString = cStmt.getString(2);
		System.out.print(resultString);
		if (resultString!= null ) {
			cStmt.close();
			return resultString;
		}
		
		cStmt.close();
		return null;
	}
	
	public static void addStaff(Staff newStaff) throws SQLException {
		String sqlString = "begin sp_insert_staff(?,?,?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, newStaff.getName());
			cStmt.setString(2, newStaff.getPhoneNumber());
			cStmt.setString(3, newStaff.getAddress());
			cStmt.setString(4, newStaff.getIdentityCard());
			cStmt.setString(5, newStaff.getType());
			cStmt.registerOutParameter(6, OracleTypes.VARCHAR);
			
			cStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String message = cStmt.getString(6);
		System.out.println(message);
		cStmt.close();
	}
	
	public static String updateStaff(String idStaff, String newName, String newAddress, String newTypeStaff) throws SQLException {
		String sqlString = "begin sp_update_staff(?,?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, idStaff);
			cStmt.setString(2, newName);
			cStmt.setString(3, newAddress);
			cStmt.setString(4, newTypeStaff);
			cStmt.registerOutParameter(5, OracleTypes.NVARCHAR);
			
			cStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cStmt.getString(5));
		String result = cStmt.getString(5);
		cStmt.close();
		return result;
	}
	
	public static String deleteStaff(String idStaff) throws SQLException {
		String sqlString = "begin sp_deleteStaff(?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, idStaff);
			cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
			cStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String result = cStmt.getString(2);
		cStmt.close();
		return result;
	}
}
