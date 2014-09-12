package com.market.mode;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Jdbc {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123123");
			Statement st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM test");
			System.out.println("id	name");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println(id + "	" + name);
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
