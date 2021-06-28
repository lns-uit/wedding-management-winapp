package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;


public class LobbyModel {
	
	public static ArrayList<Lobby> getAllLobby() throws SQLException {
		String sqlString = "begin SP_GETALLLOBBY(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<Lobby> arrLobby = new ArrayList<Lobby>();
		
		try {
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);

			while (rs.next()) {
				String idLobby = rs.getString(2);
				String nameLobby = rs.getString(3);
				String typeLobby = rs.getString(4);
				Number maxTable  = rs.getInt(5);
				Number priceTable = rs.getInt(6);
				Number priceLobby = rs.getInt(7);
				String acctiveLobby = rs.getString(8);
				
				if (acctiveLobby.equals("ON")) {
					Lobby a = new Lobby(idLobby, nameLobby, typeLobby, maxTable, priceTable, priceLobby, "");
					arrLobby.add(a);		
				}
				
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		cStmt.close();
		return arrLobby;
	}
	
	public static String addLobby (String name, String type, int numberTable, int priceTable, int priceLobby) throws SQLException {
		String sqlString = "begin sp_insertLobby(?,?,?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, name);
			cStmt.setString(2, type);
			cStmt.setInt(3, numberTable);
			cStmt.setInt(4, priceTable);
			cStmt.setInt(5, priceLobby);
			cStmt.registerOutParameter(6, OracleTypes.VARCHAR);
			cStmt.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			cStmt.close();
			return "false";
		}
		
		String resultString = cStmt.getString(6);
		cStmt.close();
		return resultString;
	}
	public static String updateLobby (String id,String name, String type, int numberTable, int priceTable, int priceLobby) throws SQLException {
		String sqlString = "begin sp_updateLobby(?,?,?,?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, id);
			cStmt.setString(2, name);
			cStmt.setInt(3, numberTable);
			cStmt.setInt(4, priceTable);
			cStmt.setInt(5, priceLobby);
			cStmt.setString(6, type);
			cStmt.registerOutParameter(7, OracleTypes.VARCHAR);
			cStmt.execute();
			
		} catch (Exception e) {
			cStmt.close();
			return "error";
		}
		
		String resultString = cStmt.getString(7);
		cStmt.close();
		return resultString;
	}
	
	public static String deleteLobby (String id) throws SQLException {
		String sqlString = "begin sp_deleteLobby(?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, id);
			cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
			cStmt.execute();
			
		} catch (Exception e) {
			cStmt.close();
			return "error";
		}
		
		String resultString = cStmt.getString(2);
		cStmt.close();
		return resultString;
	}
}
