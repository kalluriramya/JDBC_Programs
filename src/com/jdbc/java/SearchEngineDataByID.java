package com.jdbc.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchEngineDataByID 
{
	public static Connection conn; 
	public static PreparedStatement stmt;
	public static ResultSet rset;
	
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
			 String query = "select * from SEARCH_ENGINE where ID=?";
			 stmt = conn.prepareStatement(query);
			
			// Step4: Execute the select query to retrieve the data 
					Scanner in = new Scanner(System.in); 
					System.out.println("Enter Search Engine id: "); 
					int id = in.nextInt();
					stmt.setInt(1, id); 
					rset = stmt.executeQuery(); 
					in.close();

			// Step5: Read the data from the result set 
			if(rset.next())
			{ 
				System.out.println( "Id: " + rset.getString(1) + "\t Name: " + rset.getString(2) + "\t Url: " + rset.getString(3));
			}
			else {
				System.out.println("No row exists for the ID: " + id);			
			}
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	finally 
	{ 
		// Step6: Close all the objects 
		try { 
			rset.close(); 
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

