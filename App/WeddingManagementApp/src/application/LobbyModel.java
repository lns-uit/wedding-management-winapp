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
				String noteLobby = rs.getString(8);
				
				Lobby a = new Lobby(idLobby, nameLobby, typeLobby, maxTable, priceTable, priceLobby, noteLobby);
				arrLobby.add(a);	
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		cStmt.close();
		return arrLobby;
	}
}
