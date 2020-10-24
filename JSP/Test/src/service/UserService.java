package service;

import java.sql.*;

import db.DBConn;
import model.User;

public class UserService {
//	****************************************��¼service**********************************************
	public boolean checkLogin(User bean) {
//		��JavaBean�л������ֵ
		String username = bean.getUsername();
		String password = bean.getPassword();
//		��������
		try {
			DBConn db = new DBConn();
//			��ͳ���ӷ�ʽ
			Connection conn = db.getConnection();
//			��ʹ��Ԥ�������
//			Statement stat = conn.createStatement();
//			String sql="select * from user where username='"+username+"' and password='" + password+"'";
//			ResultSet rs=stat.executeQuery(sql);
//			ʹ��Ԥ�������
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
			System.out.println("���ݿ����ӳ���");
			return false;
		}
	}

//	****************************************ע��Ͳ�ѯservice**********************************************
	public boolean add(User bean) {
		String username = bean.getUsername();
		String password = bean.getPassword();
//		���ֵ
		System.out.println(username);
		System.out.println(password);
		System.out.println("addservice��ȡ�ɹ�");
		try {
			DBConn db = new DBConn();
//			��ͳ���ӷ�ʽ����ʹ��Ԥ�������
//			Connection conn = db.getConnection();
//			Statement stat = conn.createStatement();
//			String sql1="select * from user where username='"+username+"' and password='" + password+"'";
//			String sql="insert into user(username,password)VALUES('"+username+"','"+password+"')";
//			ResultSet rs=stat.executeQuery(sql1);			
//			ʹ��Ԥ�������
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
//			      ��ʹ��Ԥ�������
//				stat.executeUpdate(sql);
				PreparedStatement pre2 = conn.prepareStatement("select * from user order by id");
				ResultSet rs1 = pre2.executeQuery();
				ResultSetMetaData metaData = pre2.getMetaData();
//			       �õ������������
				int columnCount = metaData.getColumnCount();
				String[] columnName = new String[columnCount];
				for (int i = 0; i < columnName.length; i++) {
//			   	�õ�����
					columnName[i] = metaData.getColumnName(i + 1);
				}
//			      ����JavaBean����ģ��
				bean.setColumnName(columnName);
//			      ���α��ƶ�������������һ��
				rs1.last();
//			      �õ���¼�к�
				int rowNumber = rs1.getRow();
				String[][] tableRecord = bean.getTableRecord();
				tableRecord = new String[rowNumber][columnCount];
//			       ���α��ƶ����������ʼλ�ã���һ��֮ǰ
				rs1.beforeFirst();
				int i = 0;
				while (rs1.next()) {
					for (int k = 0; k < columnCount; k++) {
						tableRecord[i][k] = rs1.getString(k + 1);
					}
					i++;
				}
//			       ����JavaBean����ģ��
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
			System.out.println("���ݿ����ӳ���");
			return false;
		}
	}

//	****************************************�ҵ�IDservice**********************************************
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

//	****************************************�޸�service**********************************************
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
//		       �õ������������
			int columnCount = metaData.getColumnCount();
			String[] columnName = new String[columnCount];
			for (int i = 0; i < columnName.length; i++) {
//		       �õ�����
				columnName[i] = metaData.getColumnName(i + 1);
			}
//		      ����JavaBean����ģ��
			user.setColumnName(columnName);
//		      ���α��ƶ�������������һ��
			rs1.last();
//		      �õ���¼�к�
			int rowNumber = rs1.getRow();
			String[][] tableRecord = user.getTableRecord();
			tableRecord = new String[rowNumber][columnCount];
//		    S���α��ƶ����������ʼλ�ã���һ��֮ǰ
			rs1.beforeFirst();
			int i = 0;
			while (rs1.next()) {
				for (int k = 0; k < columnCount; k++) {
					tableRecord[i][k] = rs1.getString(k + 1);
				}
				i++;
			}
//		       ����JavaBean����ģ��
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

//	****************************************ɾ��service**********************************************
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
//		       �õ������������
			int columnCount = metaData.getColumnCount();
			String[] columnName = new String[columnCount];
			for (int i = 0; i < columnName.length; i++) {
//		       �õ�����
				columnName[i] = metaData.getColumnName(i + 1);
			}
//		      ����JavaBean����ģ��
			user.setColumnName(columnName);
//		      ���α��ƶ�������������һ��
			rs1.last();
//		      �õ���¼�к�
			int rowNumber = rs1.getRow();
			String[][] tableRecord = user.getTableRecord();
			tableRecord = new String[rowNumber][columnCount];
//		       ���α��ƶ����������ʼλ�ã���һ��֮ǰ
			rs1.beforeFirst();
			int i = 0;
			while (rs1.next()) {
				for (int k = 0; k < columnCount; k++) {
					tableRecord[i][k] = rs1.getString(k + 1);
				}
				i++;
			}
//		       ����JavaBean����ģ��
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
