package application;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

public class OrderWeddingModel {
	public static String CreateInfoWedding(String nameBride, String nameGroom) throws SQLException {
		String sqlString = "begin sp_insert_infoWedding(?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, nameBride);
			cStmt.setString(2, nameGroom);
			
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		String idInfoWedding = cStmt.getString(3);
		
		cStmt.close();
		return idInfoWedding;
	}
	
	public static String CreateFoodOrder (String idWedding, String idFood) throws SQLException {
		String sqlString = "begin sp_insertFoodOrder(?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, idFood);
			cStmt.setString(2, idWedding);
			
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		String message = cStmt.getString(3);
		
		cStmt.close();
		return message;
	}
	
	public static String CreateServiceOrder (String idWedding, String idService) throws SQLException {
		String sqlString = "begin sp_insertServiceOrder(?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, idService);
			cStmt.setString(2, idWedding);
			
			cStmt.registerOutParameter(3, OracleTypes.VARCHAR);
			cStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		String message = cStmt.getString(3);
		
		cStmt.close();
		return message;
	}
	
	public static OrderWedding callOrderWedding (String idWedding, String nameCus, String numberPhoneCus, String idLobby, String idStaff, Number number, String dateStart) throws SQLException {
		
		String sqlString = "begin sp_insertOrderWedding(?,?,?,?,?,?,?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			cStmt.setString(1, idWedding);
			cStmt.setString(2, nameCus);
			cStmt.setString(3, numberPhoneCus);
			cStmt.setString(4, idLobby);
			cStmt.setString(5, idStaff);
			cStmt.setInt(6, (int) number);
			cStmt.setString(7, dateStart);
			
			cStmt.registerOutParameter(8, OracleTypes.VARCHAR);
			cStmt.registerOutParameter(9, OracleTypes.INTEGER);
			cStmt.registerOutParameter(10, OracleTypes.INTEGER);
			
			cStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		OrderWedding result = new OrderWedding();
		String message = cStmt.getString(8);
		if (message.equals("true")) {
			result.setIdWedding(idWedding);
			result.setMoney(cStmt.getInt(9));
			result.setDeposit(cStmt.getInt(10));
			cStmt.close();
			return result;
		} 
		
		cStmt.close();
		return null;
	}
	
	public static ArrayList<OrderWedding> getAllOrderWedding() throws SQLException {
		String sqlString = "begin sp_getAllOrderWed(?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<OrderWedding> arrOrderWedding = new ArrayList<OrderWedding>();
		
		try {
			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			ResultSet rs = (ResultSet) cStmt.getObject(1);
			
			while (rs.next()) {
				String idWedding = rs.getString(1);
				String idLobby = rs.getString(2);
				String idStaff = rs.getString(3);
				String idCustomer = rs.getString(4);
				int numberFood = rs.getInt(5);
				int numberService = rs.getInt(6);
				long deposit = rs.getLong(7);
				long money = rs.getLong(8);
				int numberTable = rs.getInt(9);
				String dateOrder = rs.getString(10);
				String dateStart = rs.getString(11);
			
				String cusName = rs.getString(13);
				String cusPhoneNumber = rs.getString(14);
				
				OrderWedding a = new OrderWedding(idWedding, idLobby, idStaff, idCustomer, numberFood, numberService, deposit, money, numberTable, dateOrder, dateStart);
				a.setNameCus(cusName);
				a.setPhoneCus(cusPhoneNumber);
				a.setStatusPay((money>deposit)?"Chưa thanh toán": "Đã thanh toán");
				arrOrderWedding.add(a);
 			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		cStmt.close();
		return arrOrderWedding;
	}
	
	public static String DeleteOrderWedding (String idWedding) throws SQLException {
		String sqlString = "begin sp_deleteInforWedding(?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		try {
			
			cStmt.setString(1, idWedding);
			cStmt.registerOutParameter(2, OracleTypes.VARCHAR);
			
			cStmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		String message = cStmt.getString(2);
		return message;
	}
	
	public static void getDetailWedding (String idWedding) throws SQLException {
		String sqlString = "begin sp_getOrderWeddingById(?,?,?,?,?,?,?); end;" ;
		CallableStatement cStmt = Main.connection.prepareCall(sqlString);
		
		ArrayList<Food> arrFoodOrder = new ArrayList<Food>();
		ArrayList<ServiceWedding> arrServiceOrder = new ArrayList<ServiceWedding>();
		HolderManager holderManager = HolderManager.getInstance();
		
		try {
			
			cStmt.setString(1, idWedding);
			cStmt.registerOutParameter(2, OracleTypes.CURSOR);
			cStmt.registerOutParameter(3, OracleTypes.CURSOR);
			cStmt.registerOutParameter(4, OracleTypes.CURSOR);
			cStmt.registerOutParameter(5, OracleTypes.CURSOR);
			cStmt.registerOutParameter(6, OracleTypes.CURSOR);
			cStmt.registerOutParameter(7, OracleTypes.CURSOR);
			
			cStmt.executeUpdate();

			ResultSet rs2 = (ResultSet) cStmt.getObject(2);
			ResultSet rs3 = (ResultSet) cStmt.getObject(3);
			ResultSet rs4 = (ResultSet) cStmt.getObject(4);
			ResultSet rs5 = (ResultSet) cStmt.getObject(5);
			ResultSet rs6 = (ResultSet) cStmt.getObject(6);
			ResultSet rs7 = (ResultSet) cStmt.getObject(7);
			
			while (rs2.next()) {
				String idWeddingOrder = rs2.getString(2);
				String idLobby = rs2.getString(3);
				String idStaff = rs2.getString(4);
				String idCustomer = rs2.getString(5);
				int numberFood = rs2.getInt(6);
				int numberService = rs2.getInt(7);
				long deposit = rs2.getLong(8);
				long money = rs2.getLong(9);
				int numberTable = rs2.getInt(10);
				String dateOrder = rs2.getString(11);
				String dateStart = rs2.getString(12);
				
				OrderWedding a = new OrderWedding(idWeddingOrder, idLobby, idStaff, idCustomer, numberFood, numberService, deposit, money, numberTable, dateOrder, dateStart);
				holderManager.setDetailOrderWedding(a);
			}
			
			while (rs3.next()) {
				String idFood = rs3.getString(1);
				String nameFood = rs3.getString(2);
				long priceFood = rs3.getLong(3);
				String typeFood = rs3.getString(4);
				
				Food a = new Food(idFood, nameFood, priceFood, typeFood);
				arrFoodOrder.add(a);
			}
			holderManager.setArrFoodOrder(arrFoodOrder);
			
			while (rs4.next()) {
				String idService = rs4.getString(1);
				String nameService = rs4.getString(2);
				long priceService = rs4.getLong(3);
				
				ServiceWedding a = new ServiceWedding(idService, nameService, priceService);
				arrServiceOrder.add(a);
			}
			holderManager.setArrServiceOrder(arrServiceOrder);
			
			while (rs5.next()) {
				String idInfoWedding = rs5.getString(1);
				String nameBride = rs5.getString(2);
				String nameGroom = rs5.getString(3);
				
				InfoWedding a = new InfoWedding(idInfoWedding, nameGroom, nameBride);
				
				holderManager.setInfoOrder(a);
			}
			
			while (rs6.next()) {
				String idCus= rs6.getString(1);
				String nameCus = rs6.getString(2);
				String numberPhoneCus = rs6.getString(3);
				String dayRegisterCus = rs6.getString(4);
				long money = rs6.getLong(5);
				int discount = rs6.getInt(6);
				
				Customer a = new Customer(idCus, nameCus, numberPhoneCus, money, discount);
				holderManager.setCusOrder(a);
			}
			
			while (rs7.next()) {
				String idLobby = rs7.getString(1);
				String nameLobby = rs7.getString(2);
				String typeLobby = rs7.getString(3);
				int maxTableLobby = rs7.getInt(4);
				long priceTable = rs7.getLong(5);
				long priceLobby = rs7.getLong(6);
				
				Lobby a = new Lobby(idLobby, nameLobby, typeLobby, maxTableLobby, priceTable, priceLobby, "");
				holderManager.setLobbyOrder(a);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		cStmt.close();
	}
}
