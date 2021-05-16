package application;

import java.sql.CallableStatement;
import java.sql.SQLException;

import oracle.jdbc.internal.OracleTypes;

public class AccountModel {
	
	public static Staff Login(String username, String passwod) throws SQLException {
		String sqlString = "begin sp_login(?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, username);
			cStmt.setString(2, passwod);
			
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			cStmt.registerOutParameter(4, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		String idStaff = cStmt.getString(4);
		if (idStaff!=null) {
			String staffSqlString = "begin sp_getstaffbyid(?,?,?,?,?,?,?); end;";
			CallableStatement staffCStmt = Main.connection.prepareCall(staffSqlString);
			
			try {
				staffCStmt.setString(1,idStaff);
				staffCStmt.registerOutParameter(1, OracleTypes.VARCHAR);
				staffCStmt.registerOutParameter(2, OracleTypes.VARCHAR);
				staffCStmt.registerOutParameter(3, OracleTypes.VARCHAR);
				staffCStmt.registerOutParameter(4, OracleTypes.VARCHAR);
				staffCStmt.registerOutParameter(5, OracleTypes.VARCHAR);
				staffCStmt.registerOutParameter(6, OracleTypes.VARCHAR);
				staffCStmt.registerOutParameter(7, OracleTypes.VARCHAR);
				staffCStmt.executeUpdate();
				String id = staffCStmt.getString(1);
				String name = staffCStmt.getString(2);
				String numberPhone = staffCStmt.getString(3);
				String address = staffCStmt.getString(4);
				String identitycard = staffCStmt.getString(5);
				String startWork = staffCStmt.getString(6);
				String typeStaff = staffCStmt.getString(7);
				
				Staff resultStaff =  new Staff(id,name,  address, numberPhone,identitycard, startWork, typeStaff);
				staffCStmt.close();
				cStmt.close();
				return resultStaff;
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			staffCStmt.close();
		}
		String type = cStmt.getString(3);
		
		cStmt.close();
		System.out.print(type);
		return null;
	}

}
