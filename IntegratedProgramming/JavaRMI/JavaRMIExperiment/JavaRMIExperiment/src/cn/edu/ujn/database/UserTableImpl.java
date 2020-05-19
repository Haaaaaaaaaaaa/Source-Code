package cn.edu.ujn.database;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserTableImpl implements IUserTable {
	// 构造方法
	public UserTableImpl() {
	}

	// 实现增加功能
	@Override
	public void addUser(User user) throws RemoteException {
		String workid = user.getWorkid();
		String name = user.getName();
		String sex = user.getSex();
		String phone = user.getPhone();
		// 数据库连接
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		// 预处理语句
		PreparedStatement pre1;
		try {
			pre1 = conn.prepareStatement("insert into user(workid,name,sex,phone) VALUES(?,?,?,?)");
			pre1.setString(1, workid);
			pre1.setString(2, name);
			pre1.setString(3, sex);
			pre1.setString(4, phone);
			int result = pre1.executeUpdate();

			// 判断是否添加成功
			if (result > 0) {
				System.out.println("增加成功。");
			} else {
				System.out.println("插入失败。");
			}
			pre1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 实现删除功能
	public void deleteUser(String workid) throws RemoteException {
		// 数据库连接
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		// 预处理语句
		PreparedStatement pre1;
		try {
			pre1 = conn.prepareStatement("delete from user where workid=?");
			pre1.setString(1, workid);
			int result = pre1.executeUpdate();

			// 判断是否添加成功
			if (result > 0) {
				System.out.println("删除成功。");
			} else {
				System.out.println("删除失败。");
			}
			pre1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 实现修改功能
	public void updateUser(User user) throws RemoteException {
		String workid = user.getWorkid();
		String name = user.getName();
		String sex = user.getSex();
		String phone = user.getPhone();
		// 数据库连接
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		// 预处理语句
		PreparedStatement pre1;
		try {
			pre1 = conn.prepareStatement("update user set name = ? , sex = ? ,phone=? where workid = ?");
			pre1.setString(1, name);
			pre1.setString(2, sex);
			pre1.setString(3, phone);
			pre1.setString(4, workid);
			int result = pre1.executeUpdate();

			// 判断是否添加成功
			if (result > 0) {
				System.out.println("修改成功。");
			} else {
				System.out.println("修改失败。");
			}
			pre1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 实现查询功能
	// public List<User> findAllUser() throws RemoteException {
	public void findAllUser() throws RemoteException {
		User user = new User();
		// 数据库连接
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		// 预处理语句
		PreparedStatement pre1;
		try {
			pre1 = conn.prepareStatement("select * from user");
			ResultSet rs = pre1.executeQuery();
			if (rs.next()) {

				 user.setWorkid(rs.getString("workid"));
				 user.setName(rs.getString("name"));
				 user.setSex(rs.getString("sex"));
				 user.setPhone(rs.getString("phone"));
//				String workid = rs.getString("workid");
//				String name = rs.getString("name");
//				String sex = rs.getString("sex");
//				String phnoe = rs.getString("phone");

				conn.close();
				pre1.close();
				rs.close();
				System.out.println(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void main(String args[]) {
		try {
			// 创建实际提供服务的对象
			UserTableImpl obj = new UserTableImpl();
			// 使用匿名端口导出远程对象， 以便能够接收传入的调用。
			IUserTable stub = (IUserTable) UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			// 获得本地注册存根，默认端口1099，可以自定义端口
			Registry registry = LocateRegistry.getRegistry();
			// 注册远程对象stub的名字为addUser
			registry.bind("userTable", stub);
			// Naming.rebind("userTable", stub);
			System.err.println("UserTableImpl Ready...");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
