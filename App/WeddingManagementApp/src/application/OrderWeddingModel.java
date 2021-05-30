package application;
import java.sql.CallableStatement;
import java.sql.SQLException;

import oracle.jdbc.internal.OracleTypes;

public class OrderWeddingModel {
	public static String CreateInfoWedding(String nameBride, String nameGroom) throws SQLException {
		String sqlString = "begin sp_insert_infoWedding(?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, nameBride);
			cStmt.setString(2, nameGroom);
			
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		String idInfoWedding = cStmt.getString(3);
		
		cStmt.close();
		return idInfoWedding;
	}
	
	public static String CreateFoodOrder (String idWedding, String idFood) throws SQLException {
		String sqlString = "begin sp_insertFoodOrder(?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, idFood);
			cStmt.setString(2, idWedding);
			
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		String message = cStmt.getString(3);
		
		cStmt.close();
		return message;
	}
	
	public static String CreateServiceOrder (String idWedding, String idService) throws SQLException {
		String sqlString = "begin sp_insertServiceOrder(?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, idService);
			cStmt.setString(2, idWedding);
			
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		String message = cStmt.getString(3);
		
		cStmt.close();
		return message;
	}
}
