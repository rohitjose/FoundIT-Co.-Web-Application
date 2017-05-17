package au.edu.unsw.soacourse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import au.edu.unsw.soacourse.dto.EntityMapping;

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

	public ArrayList<String> getUserAppIds(int user_id) {
		String sql = "SELECT application_list FROM entity_Mapping WHERE application_list LIKE '%" + user_id + ":%'";
		ArrayList<String> appIds = new ArrayList<String>();

		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			String appList = new String();

			while (rs.next()) {
				appList = rs.getString("application_list");
				int begin = appList.indexOf(user_id + ":");
				int end = begin;
				while (appList.charAt(end) != '|') {
					end += 1;
				}
				appList = appList.substring(begin, end);
				appIds.add(appList);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return appIds;
	}

	public void assignTeam(String appId, String reviewers) {
		String sql = "SELECT id FROM entity_Mapping WHERE application_list LIKE '%:" + appId + "|%";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			int entity_id = 0;

			while (rs.next()) {
				entity_id = rs.getInt("id");
			}

			String update_sql = "UPDATE entity_Mapping SET review_team='" + reviewers + "' WHERE id=" + entity_id;

			executeSQL(update_sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() throws SQLException {
		c.close();
	}

	public void scheduleInterview(String polls, String jobId) {
		String sql = "SELECT id FROM entity_Mapping WHERE jobId=" + jobId;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			int entity_id = 0;

			while (rs.next()) {
				entity_id = rs.getInt("id");
			}

			String update_sql = "UPDATE entity_Mapping SET poll_list='" + polls + "' WHERE id=" + entity_id;

			executeSQL(update_sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<EntityMapping> getEntityMappings(int manager_id) {
		ArrayList<EntityMapping> entities = new ArrayList<EntityMapping>();
		String sql = "SELECT * FROM entity_Mapping WHERE manager_id=" + manager_id;

		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			int entity_id = 0;

			while (rs.next()) {
				EntityMapping entity = new EntityMapping();
				entity.setJobId(rs.getInt("jobId"));
				entity.setManager_id(rs.getInt("manager_id"));
				entity.setApplications(rs.getString("application_list"));
				entity.setPolls(rs.getString("poll_list"));
				entity.setReview_team(rs.getString("review_team"));
				entities.add(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entities;
	}

	public ArrayList<String> getManagerJobIds(int manager_id) {
		String sql = "SELECT jobId FROM entity_Mapping WHERE manager_id=" + manager_id;
		ArrayList<String> jobIds = new ArrayList<String>();

		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			int entity_id = 0;

			while (rs.next()) {
				entity_id = rs.getInt("jobId");
				jobIds.add(Integer.toString(entity_id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jobIds;
	}

	public void applyJob(String appId, String jobId, String userId) {
		String sql = "SELECT application_list FROM entity_Mapping WHERE jobId=" + jobId;
		String update_value = new String();

		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			String applications = new String();

			while (rs.next()) {
				applications = rs.getString("application_list");
			}

			if (applications == null || applications.isEmpty()) {
				update_value = userId + ":" + appId + "|";
			} else {
				update_value = applications + userId + ":" + appId + "|";
			}
			String update_sql = "UPDATE entity_Mapping SET application_list='" + update_value + "' WHERE JobId="
					+ jobId;

			executeSQL(update_sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
