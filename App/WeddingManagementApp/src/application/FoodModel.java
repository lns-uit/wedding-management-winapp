package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;


public class FoodModel {
	
	public static ArrayList<Food> getAllFood() throws SQLException {
		String sqlString = "begin SP_getAllFood(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<Food> arrFood = new ArrayList<Food>();
		
		try {
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);

			while (rs.next()) {
				String idFood = rs.getString(2);
				String nameFood = rs.getString(3);
				Number priceFood = rs.getInt(4);
				String typeFood = rs.getString(5);
				
				Food a = new Food(idFood, nameFood, priceFood, typeFood);
				
				arrFood.add(a);	
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		cStmt.close();
		return arrFood;
	}
	
	public static String deleteFood (String idFood) throws SQLException {
		String sqlString = "begin sp_deletefood(?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, idFood);
			cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
			
			cStmt.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String resultString = cStmt.getString(2);
		return resultString;
	}
	
	public static String addFood (String name, int price, String type ) throws SQLException {
		String sqlString = "begin sp_insert_food(?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, name);
			cStmt.setInt(2,price);
			cStmt.setString(3, type);
			cStmt.registerOutParameter(4, OracleTypes.VARCHAR);
			
			cStmt.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String resultString = cStmt.getString(4);
		cStmt.close();
		return resultString;
	}
	
	public static String updateFood (String id, String name, int price, String type ) throws SQLException {
		String sqlString = "begin sp_update_food(?,?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, id);
			cStmt.setString(2, name);
			cStmt.setInt(3,price);
			cStmt.setString(4, type);
			cStmt.registerOutParameter(5, OracleTypes.VARCHAR);
			
			cStmt.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String resultString = cStmt.getString(5);
		cStmt.close();
		return resultString;
	}
}
