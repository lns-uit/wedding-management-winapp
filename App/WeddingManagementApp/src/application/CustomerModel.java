package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.Const;

import oracle.jdbc.internal.OracleTypes;

public class CustomerModel {

	public static ArrayList<Customer> getAllCus () throws SQLException {
		String sqlString = "begin sp_getAllCustomer(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);

		ArrayList<Customer> arrCus = new ArrayList<Customer>();

		try {

			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();

			ResultSet rs = (ResultSet) cStmt.getObject(1);

			while (rs.next()) {
				String idCus  = rs.getString(2);
				String nameCus  = rs.getString(3);
				String numberPhoneCus  = rs.getString(4);
				String dayRegister  = rs.getString(5);
				long money  = rs.getLong(6);
				int discount  = rs.getInt(7);

				Customer a = new Customer(idCus, nameCus, numberPhoneCus, money, discount);
				arrCus.add(a);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		cStmt.close();

		return arrCus;
	}



}