package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.Const;

import oracle.jdbc.internal.OracleTypes;

public class CustomerModel {
<<<<<<< HEAD
	
	public static ArrayList<Customer> getAllCus () throws SQLException {
		String sqlString = "begin sp_getAllCustomer(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<Customer> arrCus = new ArrayList<Customer>();
		
		try {
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);
			
=======

	public static ArrayList<Customer> getAllCus () throws SQLException {
		String sqlString = "begin sp_getAllCustomer(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);

		ArrayList<Customer> arrCus = new ArrayList<Customer>();

		try {

			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();

			ResultSet rs = (ResultSet) cStmt.getObject(1);

>>>>>>> f32664d1fd650b90258594b51f88f5a9e1951493
			while (rs.next()) {
				String idCus  = rs.getString(2);
				String nameCus  = rs.getString(3);
				String numberPhoneCus  = rs.getString(4);
				String dayRegister  = rs.getString(5);
				long money  = rs.getLong(6);
				int discount  = rs.getInt(7);
<<<<<<< HEAD
				
				Customer a = new Customer(idCus, nameCus, numberPhoneCus, money, discount);
				System.out.println(idCus+ " " + discount);
				arrCus.add(a);
				
			}
			
=======

				Customer a = new Customer(idCus, nameCus, numberPhoneCus, money, discount);
				arrCus.add(a);

			}

>>>>>>> f32664d1fd650b90258594b51f88f5a9e1951493
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
<<<<<<< HEAD
		
		cStmt.close();
		
		return arrCus;
	}
	
	

}
=======

		cStmt.close();

		return arrCus;
	}



}
>>>>>>> f32664d1fd650b90258594b51f88f5a9e1951493
