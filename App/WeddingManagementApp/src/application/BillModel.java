package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class BillModel {
	public static String createBill (String idStaff, String idCus, String idWedding) throws SQLException {
	
		String sqlString = "begin sp_insertBill(?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, idStaff);
			cStmt.setString(2, idCus);
			cStmt.setString(3, idWedding);
			cStmt.registerOutParameter(4, OracleTypes.VARCHAR);
			
			cStmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			cStmt.close();
			return "error";
		}
		
		String message  = cStmt.getString(4);
		cStmt.close();
		return message;
		
	}
	
	public static ArrayList<Bill> getAllBill() throws SQLException {
		String sqlString = "begin sp_getAllBill(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<Bill> arrBill  = new ArrayList<Bill>();
		
		try {
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);
			
			while (rs.next()) {
				String idBill  = rs.getString(1);
				String idStaff  = rs.getString(2);
				String idCus  = rs.getString(3);
				String idWedding  = rs.getString(4);
				long money  = rs.getLong(5);
				String dateOfPayment  = rs.getString(6);
				
				Bill a = new Bill(idBill, idStaff, idCus, idWedding, money, dateOfPayment);
				arrBill.add(a);
			}
 			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		cStmt.close();
		return arrBill;
	}
}
