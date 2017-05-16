package com.soacourse.ass2.foundIt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBManager {

	/**
	 * Connect to database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection connectToDB() throws ClassNotFoundException, SQLException {

		//String url = "jdbc:sqlite:" + this.getClass().getResource("/").getPath()+"/DBFoundIT.db";
		

		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + this.getClass().getResource("/").getPath()+"/DBFoundIT.db");
			if (conn != null) {
				System.out.println("\nDATABASE CONNECTED\n");
			}

		} finally {

		}
		return conn;
	}

	public Integer insert(String table, HashMap<String, Object> params) throws SQLException, ClassNotFoundException {
		if (params == null || params.size() == 0) {
			return null;
		}
		String columns = "";
		String values = "";

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String col = entry.getKey();
			Object val = entry.getValue();

			columns += col + ",";
			values += "'" + val + "',";
		}
		// Strip the last comma from the strings
		if (columns.length() > 0) {
			columns = columns.substring(0, columns.length() - 1);
			values = values.substring(0, values.length() - 1);
		}

		String sql = "INSERT INTO " + table + " (" + columns + ") VALUES(" + values + ")";

		// System.out.print("Insert Query:\n"+sql);
		Integer result = null;
		Connection conn = null;
		try {
			conn = this.connectToDB();
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int rowsInserted = pstmt.executeUpdate();

			if (rowsInserted == 0) {
				result = rowsInserted;
			} else {
				ResultSet insertedRow = pstmt.getGeneratedKeys();
				if (insertedRow.next()) {
					result = insertedRow.getInt(1);
				}
			}

		} finally {
			conn.close();
		}

		return result;
	}

	public ArrayList<HashMap<String, Object>> selectAll(String table, HashMap<String, Object> params)
			throws SQLException, ClassNotFoundException {

		String sql = "SELECT * FROM " + table;

		if (params != null && params.size() > 0) {

			sql += " WHERE ";

			for (Map.Entry<String, Object> entry : params.entrySet()) {

				String col = entry.getKey();
				Object val = entry.getValue();

				sql += col + " = '" + val + "' AND ";
			}
			// strip the last AND
			sql = sql.substring(0, sql.length() - 5);
		}

		Connection conn = null;
		try {
			conn = this.connectToDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return mapResultSet(rs);
		} finally {
			conn.close();
		}
	}

	public Integer delete(String table, HashMap<String, Object> params) throws SQLException, ClassNotFoundException {

		if (params == null || params.size() == 0) {
			return null;
		}

		String sql = "DELETE FROM " + table + " WHERE ";
		for (Map.Entry<String, Object> entry : params.entrySet()) {

			String col = entry.getKey();
			Object val = entry.getValue();

			sql += col + " = '" + val + "' AND ";
		}
		// strip the last AND
		sql = sql.substring(0, sql.length() - 5);

		Connection conn = null;
		try {
			conn = this.connectToDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			return pstmt.executeUpdate();
		} finally {

			conn.close();
		}
	}

	public Integer update(String table, HashMap<String, Object> params, HashMap<String, Object> filters)
			throws SQLException, ClassNotFoundException {

		if (params == null || params.size() == 0 || filters == null || filters.size() == 0) {
			return null;
		}
		String sql = "UPDATE " + table + " SET ";

		for (Map.Entry<String, Object> entry : params.entrySet()) {

			String col = entry.getKey();
			Object val = entry.getValue();

			sql += col + " = '" + val + "' AND ";
		}
		// strip the last AND
		sql = sql.substring(0, sql.length() - 5);

		sql += " WHERE ";

		for (Map.Entry<String, Object> entry : filters.entrySet()) {

			String col = entry.getKey();
			Object val = entry.getValue();

			sql += col + " = '" + val + "' AND ";
		}
		// strip the last AND
		sql = sql.substring(0, sql.length() - 5);
		System.out.print(sql);

		Connection conn = null;
		try {

			conn = this.connectToDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			Integer rs = pstmt.executeUpdate();

			return rs;
		} finally {
			conn.close();
		}
	}

	/* Result Set mapper */
	private ArrayList<HashMap<String, Object>> mapResultSet(ResultSet rs) {

		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();

			while (rs.next()) {
				HashMap<String, Object> hm = new HashMap<String, Object>();

				for (int i = 1; i <= columns; i++) {
					hm.put(meta.getColumnName(i), rs.getObject(i));
				}
				result.add(hm);
			}

			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
