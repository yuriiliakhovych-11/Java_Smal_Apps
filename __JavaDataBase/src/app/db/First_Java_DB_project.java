package app.db;

import java.sql.*;

public class First_Java_DB_project {

	public static void main(String[] args) {

		String dbUrl = "jdbc:sqlite:src/hotel.db";
		
		
		try (Connection conn = DriverManager.getConnection(dbUrl)) {
			if (conn !=null) {
			System.out.println("Connected to the database.");
			}
			
			String sql = "SELECT guest_id, name, phone, email FROM Guests";
			String sql2 = "SELECT * FROM Guests";
			
			String sql_Rooms = "SELECT room_id, room_number, room_type, price_per_night FROM Rooms";
			
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);	
			
			System.out.println("~~~~~~~~~~~~~~~~~~");		
			while(rs.next()) {
				int id = rs.getInt("guest_id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				System.out.printf("Guest %d: %s, Phone: %s, Email: %s%n", id, name, phone, email);
			}
					
			
//			Rooms (
//				    room_id INTEGER  ,
//				    room_number TEXT,
//				    room_type TEXT ,
//				    price_per_night REAL
			

			ResultSet rs_Rooms = stmt.executeQuery(sql_Rooms);	
			
			System.out.println("~~~~~~~~~~~~~~~~~~");
			while(rs_Rooms.next()) {
				int id = rs_Rooms.getInt("room_id");
				String number = rs_Rooms.getString("room_number");
				String type = rs_Rooms.getString("room_type");
				double price = rs_Rooms.getDouble("price_per_night");
				
				System.out.printf("Room %d: %s, Type: %s, Price p/n: %.2f €%n", 
				        id, number, type, price);
			}
			
			
			
			
					
		} catch (SQLException e) {
			System.out.println("Database connected failed.");
			e.printStackTrace();
		}
		

	}

}
