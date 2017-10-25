package org.mybaties.freemark.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UtilDB {
	private static Connection conn      = null;
	private static Statement st         = null;
	private static ResultSet rs         = null;
	private static DatabaseMetaData dmd = null;
	/**
	 * 链接数据库
	 * @return
	 */
	public static Connection connection() {
		try {
			Class.forName(Constants.DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 关闭链接
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void release(ResultSet rs, Statement st, Connection conn) {
		try {
			try {
				if (null != rs) {
					rs.close();
				}
			} catch (Exception e) {
				rs = null;
			}
			try {
				if (null != st) {
					st.close();
				}
			} catch (Exception e) {
				st = null;
			}
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (Exception e) {
				conn = null;
			}
		} finally {
			rs = null;
			st = null;
			conn = null;
		}
	}
	/**
	 * 根据表名查询表中列注释
	 * @param tableName 表名
	 * @return List<String>
	 */
	public static List<String> getColumnRemarksByTableNameWithList(String tableName) {
		List<String> columnComments = new LinkedList<String>();
		connection();
		try {
			dmd = conn.getMetaData();
			rs = dmd.getColumns(null, dmd.getUserName(), tableName.toUpperCase(), null);
			while (rs.next()) {
				columnComments.add(rs.getString("REMARKS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, st, conn);
		return columnComments;
	}
	/**
	 * 根据表名查询表中列名
	 * @param tableName 表名
	 * @return List<String>
	 */
	public static List<String> getColumnNameByTableNameWithList(String tableName) {
		List<String> columnComments = new LinkedList<String>();
		connection();
		try {
			dmd = conn.getMetaData();
			rs = dmd.getColumns(conn.getCatalog(), dmd.getUserName(), tableName.toUpperCase(), null);
			while (rs.next()) {
				columnComments.add(rs.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, st, conn);
		return columnComments;
	}

	/**
	 * 根据表名查询主键列名
	 * @param tableName 表名
	 * @return List<String>
	 */
	public static String getPrimaryKey(String tableName) {
		String primaryKey = "tableName"+"_ID";
		connection();
		try {
			dmd = conn.getMetaData();
			rs = dmd.getPrimaryKeys(conn.getCatalog(), dmd.getUserName(), tableName.toUpperCase());
			while (rs.next()) {
				primaryKey = rs.getString("COLUMN_NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, st, conn);
		return primaryKey;
	}

	/**
	 * 根据表名查询表中列类型
	 * @param tableName 表名
	 * @return List<String>
	 */
	public static List<String> getColumnTyBypeTableNameWithList(String tableName) {
		List<String> tableNames = new LinkedList<String>();
		connection();
		try {
			dmd = conn.getMetaData();
			rs = dmd.getColumns(null, dmd.getUserName(), tableName.toUpperCase(), null);
			while (rs.next()) {
				tableNames.add(rs.getString("TYPE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, st, conn);
		return tableNames;
	}
	/**
	 * 得到数据库中所有表名
	 * @return List<String>
	 */
	public static List<String> getTableNames() {
		List<String> tableNames = new LinkedList<String>();
		connection();
		try {
			dmd = conn.getMetaData();
			rs = dmd.getTables(null, dmd.getUserName(), null, null);
			while (rs.next()) {
				tableNames.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, st, conn);
		return tableNames;
	}
	/**
	 * 得到数据库表注释
	 * @param tableName 表名
	 * @return 表注释
	 */
	public static String getTableRemarksByTableName(String tableName) {
		String name  = "";
		connection();
		try {
			String sql = "SHOW TABLE STATUS WHERE NAME = '" + tableName + "'";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			boolean have = rs.next();
			if(have){
				name = rs.getString("Comment");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, st, conn);
		return name;
	}

	public static void main(String[] args) {
		String list = getTableRemarksByTableName("w");
		System.out.println(list);
	}
}
