package au.edu.unsw.soacourse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityMappingDao {

	static Connection c;
	String sql_connector = "','";

	// Initialize the connection
	public EntityMappingDao() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + this.getClass().getResource("/").getPath() + "foundIT.db");
			System.out.println("jdbc:sqlite:" + this.getClass().getResource("/").getPath() + "foundIT");
			System.out.println(getClass().getResource("/").getPath());
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

	public void insertMapping(int jobId, int manager_id, String application_list, String poll_list,
			String review_team) {
		String sql = "INSERT INTO entity_Mapping (jobId,manager_id,application_list,poll_list, review_team)  VALUES("
				+ jobId + ", " + manager_id + " , '" + application_list + sql_connector + poll_list + sql_connector
				+ review_team + "')";

		try {
			executeSQL(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
