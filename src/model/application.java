package model;

import java.sql.*;

public class application {
	
	public String insertApplication(String ID, String name, String nic, String address, String phone, String email, String area, String service_center, String solar_panel) {
		
		String output = "";
		
		try
		{ 
			
			connection con = new connection();
			Connection con1 = con.connect(); 

			if (con1 == null) 
			{return "Error while connecting to the database for inserting."; } 

			// create a prepared statement
			String query = "insert into personal values(`id`,`name`,`nic`,`address`,`phone`,`email`,`area`,`service_center`,`solar_panel`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 

			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, name); 
			preparedStmt.setString(3, nic); 
			preparedStmt.setString(4, address); 
			preparedStmt.setString(5, phone); 
			preparedStmt.setString(6, email); 
			preparedStmt.setString(7, area); 
			preparedStmt.setString(8, service_center); 
			preparedStmt.setString(9, solar_panel); 

			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newApplication = readApplication();
			
			System.out.println(newApplication);

			output = "{\"status\":\"success\", \"data\": \"" + newApplication + "\"}";

		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Application.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output;
		
	}
	
	public String readApplication() {
		
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect();

			if (con1 == null) 
			{return "Error while connecting to the database for reading."; } 

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>ID</th>"+
					"<th>Name</th>" +
					"<th>NIC</th>" + 
					"<th>Address</th>" +
					"<th>Phone</th>" +
					"<th>E-mail</th>" + 
					"<th>Area</th>" +
					"<th>Service Center</th>" +
					"<th>Solar Panel</th>" + 
					"<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from personal"; 
			Statement stmt = con1.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 

			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String id = Integer.toString(rs.getInt("id")); 
				String name = rs.getString("name"); 
				String nic = rs.getString("nic"); 
				String address = rs.getString("address"); 
				String phone = rs.getString("phone"); 
				String email = rs.getString("email"); 
				String area = rs.getString("area"); 
				String service_center = rs.getString("service_center"); 
				String solar_panel = rs.getString("solar_panel"); 

				// Add into the html table
				output += "<tr><td><input type='hidden' name='hidApplicationIDUpdate' id='hidApplicationIDUpdate' value='"+ id +"'>" + name + "</td>"; 
				output += "<td>" + nic + "</td>"; 
				output += "<td>" + address + "</td>"; 
				output += "<td>" + phone + "</td>"; 
				output += "<td>" + email + "</td>"; 
				output += "<td>" + area + "</td>"; 
				output += "<td>" + service_center + "</td>"; 
				output += "<td>" + solar_panel + "</td>"; 

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-inqid='"+id+"'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-secondary' data-inqid='"+id+"'></td></tr>"; 
			} 
			con1.close(); 

			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the Applications."; 
			System.err.println(e.getMessage()); 
		} 
		return output;
		
	}
	
	public String updateInquiry(String id, String name, String nic, String address, String phone, String email, String area, String service_center, String solar_panel) {
		
		System.out.println("came here as well");
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect();
			
			if (con1 == null) 
			{return "Error while connecting to the database for updating."; } 

			// create a prepared statement
			String query = "UPDATE personal SET name=?,nic=?,address=?,phone=?, email=?, area=?,service_center=?, solar_panel=? WHERE id=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 

			// binding values
			preparedStmt.setString(1, name); 
			preparedStmt.setString(2, nic); 
			preparedStmt.setString(3, address); 
			preparedStmt.setString(4, phone); 
			preparedStmt.setString(5, email); 
			preparedStmt.setString(6, area); 
			preparedStmt.setString(7, service_center); 
			preparedStmt.setString(8, solar_panel); 
			preparedStmt.setString(9, id);

			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newApplication = readApplication();

			output = "{\"status\":\"success\", \"data\": \"" + newApplication + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Application.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output;
		
	}
	
	public String deleteApplication(String id) {
		
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect();
			
			if (con1 == null) 
			{return "Error while connecting to the database for deleting."; } 

			// create a prepared statement
			String query = "delete from personal where id=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 

			// binding values
			preparedStmt.setString(1, id); 

			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newApplication = readApplication();

			output = "{\"status\":\"success\", \"data\": \"" + newApplication + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while deleting the Application.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output;
		
	}

}

