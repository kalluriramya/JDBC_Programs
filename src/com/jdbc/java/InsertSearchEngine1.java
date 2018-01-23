package com.jdbc.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertSearchEngine1 
{
	public static Connection conn; 
	public static PreparedStatement stmt;
	
	public static void main(String[] args) 
	{		
		try 
		{
			// Step1: Loading the PostgreSQL drivers 
			Class.forName("org.postgresql.Driver");
			
			// Step2: Establish the Database Connection 
			String dbURL = "jdbc:postgresql://localhost:5433/MyDB"; 
			String user = "postgres"; 
			String pass = "postgres"; 
			conn = DriverManager.getConnection(dbURL, user, pass);

			 //Step3: Defining a PreparedStatement object 
			 String query = "Insert into SEARCH_ENGINE(id, name, url) values (?, ?, ?)";
			 stmt = conn.prepareStatement(query);

			// Step4: Read the data to insert 
			Scanner in = new Scanner(System.in); 
			int id = 7; 
			System.out.println("Enter Search Engine Name: "); 
			String name = in.nextLine();
			System.out.println("Enter Search Engine Url: "); 
			String url = in.nextLine(); 
			in.close();
			
			stmt.setInt(1, id); 
			stmt.setString(2, name); 
			stmt.setString(3, url);

			// Step5: Run the above query to insert the data 
			int i = stmt.executeUpdate(); 
			System.out.println(i + " record is inserted into 'Search_Engine' successfully..");
		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	finally 
	{ 
		// Step6: Close all the objects 
		try { 
			stmt.close(); 
			conn.close(); 
			}
		catch (SQLException e) 
		{ 
			e.printStackTrace(); 
		} 
		
	}
}
}

