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
}
