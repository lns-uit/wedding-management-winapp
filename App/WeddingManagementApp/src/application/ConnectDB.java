package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public static Connection getOracleConnection() throws SQLException, ClassNotFoundException {
		String hostName = "localhost";
		String sid = "orcl";
		String userName = "pml";
		String password = "Phammanhloi5501";

		return getOracleConnection(hostName, sid, userName, password);
	}

	public static Connection getOracleConnection(String hostName, String sid,
    String userName, String password) throws ClassNotFoundException,
    SQLException {

		String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		
		System.out.println("Connect success");
		return conn;
	}
	
}