package application;

import java.sql.SQLException;

public class StaffController {
	public static void searchStaff() throws SQLException {
		System.out.println(StaffModel.getAllStaff().get(1).getName()); 
	}
}
