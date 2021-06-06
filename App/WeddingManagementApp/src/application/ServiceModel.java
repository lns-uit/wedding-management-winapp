package application;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class ServiceModel {
	public static ArrayList<ServiceWedding> getAllService() throws SQLException {
		String sqlString = "begin SP_getAllService(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<ServiceWedding> arrService = new ArrayList<ServiceWedding>();
		
		try {
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);

			while (rs.next()) {
				String idService = rs.getString(2);
				String nameService = rs.getString(3);
				int priceService = rs.getInt(4);
				String activeService = rs.getString(5);
				
				if (activeService.equals("ON")) {
					ServiceWedding a = new ServiceWedding(idService,nameService, priceService);
					arrService.add(a);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		cStmt.close();
		return arrService;
	}
	
	public static String addService(String nameService, int priceService) throws SQLException {
		String sqlString = "begin sp_insertservice(?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, nameService);
			cStmt.setInt(2, priceService);
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			
			cStmt.execute();
		} catch (Exception e) {
			cStmt.close();
			return "error";
		}
		
		String message = cStmt.getString(3);
		cStmt.close();
		return message;
	}
	
	public static String updateStaff(String idService , String newName, int newPrice) throws SQLException {
		String sqlString = "begin sp_updateservice(?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, idService);
			cStmt.setString(2, newName);
			cStmt.setInt(3, newPrice);
			cStmt.registerOutParameter(4, OracleTypes.NVARCHAR);
			
			cStmt.execute();
		} catch (Exception e) {
			cStmt.close();
			return "error";
		}
		
		String result = cStmt.getString(4);
		cStmt.close();
		
		return result;
	}
	
	public static String deleteService(String idService) throws SQLException {
		String sqlString = "begin sp_deteService(?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, idService);
			cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
			cStmt.execute();
		} catch (Exception e) {
			cStmt.close();
			return "error";
		}
		String result = cStmt.getString(2);
		cStmt.close();
		return result;
	}
}


