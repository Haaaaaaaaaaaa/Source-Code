package cn.edu.ujn.application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import cn.edu.ujn.database.IUserTable;
import cn.edu.ujn.database.User;
import cn.edu.ujn.database.UserTableImpl;

public class UserListImpl implements IUserList {
	// ���췽��
	public UserListImpl() {
	}

	public static void main(String args[]) {
		try {
			// ����ʵ���ṩ����Ķ���
			UserListImpl obj = new UserListImpl();
			// ʹ�������˿ڵ���Զ�̶��� �Ա��ܹ����մ���ĵ��á�
			IUserList stub = (IUserList) UnicastRemoteObject.exportObject(obj, 0);
			// ��ñ���ע������Ĭ�϶˿�1099�������Զ���˿�
			// �����Ͷ˿ڶ��ǿ�ѡ�ģ����ʡ����������Ĭ�������ڱ��أ�����˿�Ҳʡ�ԣ���Ĭ�϶˿���1099��
			Registry registry = LocateRegistry.getRegistry();
			// ע��Զ�̶���stub������ΪaddUser
			registry.bind("userList", stub);
			System.err.println("UserListImpl Ready...");
		} catch (Exception e) {
			System.err.println("UserListImpl exception: " + e.toString());
			e.printStackTrace();
		}

		// UserListImpl obj = new UserListImpl();
		// Vector a = new Vector();
		// try {
		// a=obj.findAllUser();
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	// ʵ����ӹ���
	public void addUser(User user) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			IUserTable stub = (IUserTable) registry.lookup("userTable");
			stub.addUser(user);
		} catch (Exception e) {
			System.err.println("UserListImpl exception: " + e.toString());
			e.printStackTrace();
		}
	}

	// ʵ��ɾ������
	public void deleteUser(String workid) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			IUserTable stub = (IUserTable) registry.lookup("userTable");
			stub.deleteUser(workid);
		} catch (Exception e) {
			System.err.println("UserListImpl exception: " + e.toString());
			e.printStackTrace();
		}
	}

	// ʵ���޸Ĺ���
	public void updateUser(User user) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			IUserTable stub = (IUserTable) registry.lookup("userTable");
			stub.updateUser(user);
		} catch (Exception e) {
			System.err.println("UserListImpl exception: " + e.toString());
			e.printStackTrace();
		}
	}

	// ʵ�ֲ�ѯ����
	public List<User> findAllUser() throws RemoteException {
		List<User> lists = new ArrayList<User>();
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			IUserTable stub = (IUserTable) registry.lookup("userTable");
			lists = stub.findAllUser();
		} catch (Exception e) {
			System.err.println("UserListImpl exception: " + e.toString());
			e.printStackTrace();
		}
		return lists;

	}

}
