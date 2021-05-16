package application;

import java.sql.CallableStatement;
import java.sql.SQLException;

import oracle.jdbc.internal.OracleTypes;

public class AccountModel {
	
	public static String Login(String username, String passwod) throws SQLException {
		String sqlString = "begin sp_login(?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, username);
			cStmt.setString(2, passwod);
			
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		String type = cStmt.getString(3);
		cStmt.close();
		System.out.print(type);
		return type;
	}

}
