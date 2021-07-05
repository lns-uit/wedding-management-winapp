package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public static Connection getOracleConnection() throws SQLException, ClassNotFoundException {



		
		String hostName = "181.215.242.88";
		String sid = "xe";
		String userName = "my_user";
		String password = "my_user123";
	
		return getOracleConnection(hostName, sid, userName, password);
	}

	public static Connection getOracleConnection(String hostName, String sid,
    String userName, String password) throws ClassNotFoundException,
    SQLException {


		String connectionURL = "jdbc:oracle:thin:@" + hostName + ":37120:" + sid;

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		
		System.out.println(conn);
		
		return conn;
	}
	
}
