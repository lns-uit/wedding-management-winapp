package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class ReportModel {
	public static ArrayList<String> getAllReport () throws SQLException {
		String sqlString = "begin sp_getReport(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
	
		ArrayList<String> arrReport = new ArrayList<String>();
	
		try {
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);
			
			while (rs.next()) {
				String date = rs.getString(1);
				arrReport.add(date);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		cStmt.close();
		return arrReport;
	}
}