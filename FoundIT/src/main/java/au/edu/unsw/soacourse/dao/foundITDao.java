package au.edu.unsw.soacourse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import au.edu.unsw.soacourse.dto.User;

public class foundITDao {
	static Connection c;
	String sql_connector = "','";

	// Initialize the connection
	public foundITDao() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + this.getClass().getResource("/").getPath() + "foundIT");
			c.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	/* INSERT OPERATIONS FOR THE TABLES */

	public void executeSQL(String sql) throws SQLException {
		Statement stmt = c.createStatement();

		System.out.println(sql);

		stmt.executeUpdate(sql);
		stmt.close();
		c.commit();
	}

	/* SELECT OPERATIONS FOR THE TABLES */

	public int retrieveValue(String query) {

		int count = 0;

		try {

			// STEP 4: Execute a query
			Statement stmt = c.createStatement();

			String sql = query;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				count = rs.getInt("value");
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		return count;

	}

	public String getValue(HashMap<String, String> map, String key) {
		if (map.containsKey(key)) {
			return map.get(key);
		} else {
			return "";
		}
	}

	/* USER TABLE */
	public void signUpUser(HashMap<String, String> queryParams) {
		// Insert into the user table
		String user_sql = "INSERT INTO user (username,email,password,role)  VALUES('" + queryParams.get("username")
				+ sql_connector + queryParams.get("email") + sql_connector + queryParams.get("password") + sql_connector
				+ queryParams.get("role") + "')";
		String user_id_sql = "SELECT id AS value FROM user ORDER BY id DESC LIMIT 1";
		int user_id = 0;

		try {
			executeSQL(user_sql);
			user_id = retrieveValue(user_id_sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String profile_sql = "INSERT INTO profile(user_id,first_name,last_name,phone,site,street_address,city,zip,state,headline,position,company,summary,business_domain,experience,education,skills) VALUES("
				+ user_id + ", '" + getValue(queryParams, "first_name") + sql_connector
				+ getValue(queryParams, "last_name") + sql_connector + getValue(queryParams, "phone") + sql_connector
				+ getValue(queryParams, "site") + sql_connector + getValue(queryParams, "street_address")
				+ sql_connector + getValue(queryParams, "city") + sql_connector + getValue(queryParams, "zip")
				+ sql_connector + getValue(queryParams, "state") + sql_connector + getValue(queryParams, "headline")
				+ sql_connector + getValue(queryParams, "position") + sql_connector + getValue(queryParams, "company")
				+ sql_connector + getValue(queryParams, "business_domain") + sql_connector
				+ getValue(queryParams, "experience") + sql_connector + getValue(queryParams, "education")
				+ sql_connector + getValue(queryParams, "skills") + "')";

		try {
			executeSQL(profile_sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Login Support function
	public User login(String username, String password) throws SQLException {
		String sql = "SELECT * FROM user WHERE username='" + username + "'";
		User user = new User();
		String auth_pass = new String();

		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			user.setUser_id(rs.getInt("id"));
			user.setEmail(rs.getString("username"));
			auth_pass = rs.getString("password");
			user.setRole(rs.getString("role"));
		}
		rs.close();
		stmt.close();

		if (auth_pass.equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	// Insert hiring team
	public void insertHiringTeam(String teamList, int manager_id) throws SQLException {
		String sql = "INSERT INTO hiring_team(team_member_list, manager_id) VALUES('" + teamList + "'," + manager_id
				+ ")";
		
		executeSQL(sql);
	}
	
	// Select hiring team
	public String getHiringTeam(int manager_id) throws SQLException{
		String sql = "SELECT * FROM hiring_team WHERE manager_id="+manager_id;
		String team = new String();
		
		Statement stmt = c.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			team = rs.getString("team_member_list");
		}
		
		return team;
		
		
	}
	
	public void updateHiringTeam(String teamList, int manager_id) throws SQLException{
		String sql = "UPDATE hiring_team SET team_member_list='"+teamList+"' WHERE manager_id="+manager_id;
		
		executeSQL(sql);
	}
	
	public void close() throws SQLException{
		c.close();
	}

}
