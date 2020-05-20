package cn.edu.ujn.database;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UserTableImpl implements IUserTable {
	// ���췽��
	public UserTableImpl() {
	}

	// ʵ�����ӹ���
	@Override
	public void addUser(User user) throws RemoteException {
		String workid = user.getWorkid();
		String name = user.getName();
		String sex = user.getSex();
		String phone = user.getPhone();
		// ���ݿ�����
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		// Ԥ�������
		PreparedStatement pre1;
		try {
			pre1 = conn.prepareStatement("insert into user(workid,name,sex,phone) VALUES(?,?,?,?)");
			pre1.setString(1, workid);
			pre1.setString(2, name);
			pre1.setString(3, sex);
			pre1.setString(4, phone);
			int result = pre1.executeUpdate();

			// �ж��Ƿ���ӳɹ�
			if (result > 0) {
				System.out.println("���ӳɹ���");
			} else {
				System.out.println("����ʧ�ܡ�");
			}
			pre1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ʵ��ɾ������
	public void deleteUser(String workid) throws RemoteException {
		// ���ݿ�����
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		// Ԥ�������
		PreparedStatement pre1;
		try {
			pre1 = conn.prepareStatement("delete from user where workid=?");
			pre1.setString(1, workid);
			int result = pre1.executeUpdate();

			// �ж��Ƿ���ӳɹ�
			if (result > 0) {
				System.out.println("ɾ���ɹ���");
			} else {
				System.out.println("ɾ��ʧ�ܡ�");
			}
			pre1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ʵ���޸Ĺ���
	public void updateUser(User user) throws RemoteException {
		String workid = user.getWorkid();
		String name = user.getName();
		String sex = user.getSex();
		String phone = user.getPhone();
		// ���ݿ�����
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		// Ԥ�������
		PreparedStatement pre1;
		try {
			pre1 = conn.prepareStatement("update user set name = ? , sex = ? ,phone=? where workid = ?");
			pre1.setString(1, name);
			pre1.setString(2, sex);
			pre1.setString(3, phone);
			pre1.setString(4, workid);
			int result = pre1.executeUpdate();

			// �ж��Ƿ���ӳɹ�
			if (result > 0) {
				System.out.println("�޸ĳɹ���");
			} else {
				System.out.println("�޸�ʧ�ܡ�");
			}
			pre1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ʵ�ֲ�ѯ����
	public List<User> findAllUser() throws RemoteException {
		List<User> lists = new ArrayList<User>();
		try {
			// ���ݿ�����
			DBConn db = new DBConn();
			Connection conn = db.getConnection();
			// Ԥ�������
			PreparedStatement pre1;
			pre1 = conn.prepareStatement("select * from user");
			ResultSet rs = pre1.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setWorkid(rs.getString("workid"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setPhone(rs.getString("phone"));
				lists.add(user);
			}
			conn.close();
			pre1.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}

	public static void main(String args[]) {
		try {
			// ����ʵ���ṩ����Ķ���
			UserTableImpl obj = new UserTableImpl();
			// ʹ�������˿ڵ���Զ�̶��� �Ա��ܹ����մ���ĵ��á�
			IUserTable stub = (IUserTable) UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			// ��ñ���ע������Ĭ�϶˿�1099�������Զ���˿�
			Registry registry = LocateRegistry.getRegistry();
			// ע��Զ�̶���stub������ΪaddUser
			registry.bind("userTable", stub);
			// Naming.rebind("userTable", stub);
			System.err.println("UserTableImpl Ready...");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}

		// ����
		UserTableImpl obj = new UserTableImpl();
		List<User> lists = new ArrayList<User>();
		try {
			lists = obj.findAllUser();
			for (User user : lists) {
				Vector a = new Vector();
				a.add(user.getWorkid());
				a.add(user.getName());
				a.add(user.getSex());
				a.add(user.getPhone());
				System.out.println(a);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
