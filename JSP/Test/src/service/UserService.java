package service;

import java.sql.*;

import db.DBConn;
import model.User;

public class UserService {
//	****************************************登录service**********************************************
	public boolean checkLogin(User bean) {
//		从JavaBean中获得属性值
		String username = bean.getUsername();
		String password = bean.getPassword();
//		创建连接
		try {
			DBConn db = new DBConn();
//			传统连接方式
			Connection conn = db.getConnection();
//			不使用预处理语句
//			Statement stat = conn.createStatement();
//			String sql="select * from user where username='"+username+"' and password='" + password+"'";
//			ResultSet rs=stat.executeQuery(sql);
//			使用预处理语句
			PreparedStatement pre = conn.prepareStatement(
					"select * from user where username='" + username + "' and password='" + password + "'");
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				pre.close();
				rs.close();
				return true;
			} else
				return false;
		} catch (SQLException e) {
			System.out.println("数据库连接出错！");
			return false;
		}
	}

//	****************************************注册和查询service**********************************************
	public boolean add(User bean) {
		String username = bean.getUsername();
		String password = bean.getPassword();
//		检查值
		System.out.println(username);
		System.out.println(password);
		System.out.println("addservice获取成功");
		try {
			DBConn db = new DBConn();
//			传统连接方式，不使用预处理语句
//			Connection conn = db.getConnection();
//			Statement stat = conn.createStatement();
//			String sql1="select * from user where username='"+username+"' and password='" + password+"'";
//			String sql="insert into user(username,password)VALUES('"+username+"','"+password+"')";
//			ResultSet rs=stat.executeQuery(sql1);			
//			使用预处理语句
			Connection conn = db.getConnection();
			PreparedStatement pre = conn.prepareStatement("select * from user where username=? and password=?");
			pre.setString(1, username);
			pre.setString(2, password);
			ResultSet rs = pre.executeQuery();
			if (!rs.next()) {
				PreparedStatement pre1 = conn.prepareStatement("insert into user(username,password) VALUES(?,?)");
				pre1.setString(1, username);
				pre1.setString(2, password);
				pre1.executeUpdate();
//			      不使用预处理语句
//				stat.executeUpdate(sql);
				PreparedStatement pre2 = conn.prepareStatement("select * from user order by id");
				ResultSet rs1 = pre2.executeQuery();
				ResultSetMetaData metaData = pre2.getMetaData();
//			       得到结果集的列数
				int columnCount = metaData.getColumnCount();
				String[] columnName = new String[columnCount];
				for (int i = 0; i < columnName.length; i++) {
//			   	得到列名
					columnName[i] = metaData.getColumnName(i + 1);
				}
//			      更新JavaBean数据模型
				bean.setColumnName(columnName);
//			      将游标移动到结果集的最后一行
				rs1.last();
//			      得到记录行号
				int rowNumber = rs1.getRow();
				String[][] tableRecord = bean.getTableRecord();
				tableRecord = new String[rowNumber][columnCount];
//			       将游标移动到结果集初始位置，第一行之前
				rs1.beforeFirst();
				int i = 0;
				while (rs1.next()) {
					for (int k = 0; k < columnCount; k++) {
						tableRecord[i][k] = rs1.getString(k + 1);
					}
					i++;
				}
//			       更新JavaBean数据模型
				bean.setTableRecord(tableRecord);
				conn.close();
				pre.close();
				pre1.close();
				pre2.close();
				rs1.close();
				return true;
			} else
				return false;
		} catch (SQLException e) {
			System.out.println("数据库连接出错！");
			return false;
		}
	}

//	****************************************找到IDservice**********************************************
	public User Search(int id) throws SQLException {
		User user = new User();
		DBConn dbConn = new DBConn();
		if (dbConn.getConnection() != null) {
			Connection conn = dbConn.getConnection();
			PreparedStatement pre = conn.prepareStatement("select * from user where id=?");
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				conn.close();
				pre.close();
				rs.close();
			}
			return user;
		} else {
			return user;
		}
	}

//	****************************************修改service**********************************************
	public boolean Edit(User user) throws SQLException {
		DBConn dbConn = new DBConn();
		String username = user.getUsername();
		String password = user.getPassword();
		int id = user.getId();
		if (dbConn.getConnection() != null) {
			Connection conn = dbConn.getConnection();
			PreparedStatement pre = conn.prepareStatement("update user set username = ? , password = ? where id = ?");
			pre.setString(1, username);
			pre.setString(2, password);
			pre.setInt(3, id);
			pre.executeUpdate();

			PreparedStatement pre2 = conn.prepareStatement("select * from user order by id");
			ResultSet rs1 = pre2.executeQuery();
			ResultSetMetaData metaData = pre2.getMetaData();
//		       得到结果集的列数
			int columnCount = metaData.getColumnCount();
			String[] columnName = new String[columnCount];
			for (int i = 0; i < columnName.length; i++) {
//		       得到列名
				columnName[i] = metaData.getColumnName(i + 1);
			}
//		      更新JavaBean数据模型
			user.setColumnName(columnName);
//		      将游标移动到结果集的最后一行
			rs1.last();
//		      得到记录行号
			int rowNumber = rs1.getRow();
			String[][] tableRecord = user.getTableRecord();
			tableRecord = new String[rowNumber][columnCount];
//		    S将游标移动到结果集初始位置，第一行之前
			rs1.beforeFirst();
			int i = 0;
			while (rs1.next()) {
				for (int k = 0; k < columnCount; k++) {
					tableRecord[i][k] = rs1.getString(k + 1);
				}
				i++;
			}
//		       更新JavaBean数据模型
			user.setTableRecord(tableRecord);
			conn.close();
			pre.close();
			pre2.close();
			rs1.close();
			return true;
		} else {
			return false;
		}
	}

//	****************************************删除service**********************************************
	public boolean Delete(User user) throws SQLException {
		DBConn dbConn = new DBConn();
		int id = user.getId();
		if (dbConn.getConnection() != null) {
			Connection conn = dbConn.getConnection();
			PreparedStatement pre = conn.prepareStatement("delete from user where id = ?");
			pre.setInt(1, id);
			pre.executeUpdate();

			PreparedStatement pre2 = conn.prepareStatement("select * from user order by id");
			ResultSet rs1 = pre2.executeQuery();
			ResultSetMetaData metaData = pre2.getMetaData();
//		       得到结果集的列数
			int columnCount = metaData.getColumnCount();
			String[] columnName = new String[columnCount];
			for (int i = 0; i < columnName.length; i++) {
//		       得到列名
				columnName[i] = metaData.getColumnName(i + 1);
			}
//		      更新JavaBean数据模型
			user.setColumnName(columnName);
//		      将游标移动到结果集的最后一行
			rs1.last();
//		      得到记录行号
			int rowNumber = rs1.getRow();
			String[][] tableRecord = user.getTableRecord();
			tableRecord = new String[rowNumber][columnCount];
//		       将游标移动到结果集初始位置，第一行之前
			rs1.beforeFirst();
			int i = 0;
			while (rs1.next()) {
				for (int k = 0; k < columnCount; k++) {
					tableRecord[i][k] = rs1.getString(k + 1);
				}
				i++;
			}
//		       更新JavaBean数据模型
			user.setTableRecord(tableRecord);
			conn.close();
			pre.close();
			pre2.close();
			rs1.close();
			return true;
		} else {
			return false;
		}
	}
}
