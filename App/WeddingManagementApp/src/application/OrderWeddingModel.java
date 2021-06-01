package application;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public static OrderWedding callOrderWedding (String idWedding, String nameCus, String numberPhoneCus, String idLobby, String idStaff, Number number, String dateStart) throws SQLException {
		
		String sqlString = "begin sp_insertOrderWedding(?,?,?,?,?,?,?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, idWedding);
			cStmt.setString(2, nameCus);
			cStmt.setString(3, numberPhoneCus);
			cStmt.setString(4, idLobby);
			cStmt.setString(5, idStaff);
			cStmt.setInt(6, (int) number);
			cStmt.setString(7, dateStart);
			
			cStmt.registerOutParameter(8, OracleTypes.VARCHAR);
			cStmt.registerOutParameter(9, OracleTypes.INTEGER);
			cStmt.registerOutParameter(10, OracleTypes.INTEGER);
			
			cStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		OrderWedding result = new OrderWedding();
		String message = cStmt.getString(8);
		if (message.equals("true")) {
			result.setIdWedding(idWedding);
			result.setMoney(cStmt.getInt(9));
			result.setDeposit(cStmt.getInt(10));
			cStmt.close();
			return result;
		} 
		
		cStmt.close();
		return null;
	}
	
	public static ArrayList<OrderWedding> getAllOrderWedding() throws SQLException {
		String sqlString = "begin sp_getAllOrderWed(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<OrderWedding> arrOrderWedding = new ArrayList<OrderWedding>();
		
		try {
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);
			
			while (rs.next()) {
				String idWedding = rs.getString(2);
				String idLobby = rs.getString(3);
				String idStaff = rs.getString(4);
				String idCustomer = rs.getString(5);
				int numberFood = rs.getInt(6);
				int numberService = rs.getInt(7);
				long deposit = rs.getLong(8);
				long money = rs.getLong(9);
				int numberTable = rs.getInt(10);
				String dateOrder = rs.getString(11);
				String dateStart = rs.getString(12);
				
				OrderWedding a = new OrderWedding(idWedding, idLobby, idStaff, idCustomer, numberFood, numberService, deposit, money, numberTable, dateOrder, dateStart);
				arrOrderWedding.add(a);
 			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		cStmt.close();
		return arrOrderWedding;
		
		
	}
}
