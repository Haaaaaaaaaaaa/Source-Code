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
	// 构造方法
	public UserListImpl() {
	}

	public static void main(String args[]) {
		try {
			// 创建实际提供服务的对象
			UserListImpl obj = new UserListImpl();
			// 使用匿名端口导出远程对象， 以便能够接收传入的调用。
			IUserList stub = (IUserList) UnicastRemoteObject.exportObject(obj, 0);
			// 获得本地注册存根，默认端口1099，可以自定义端口
			// 主机和端口都是可选的，如果省略主机，则默认运行在本地；如果端口也省略，则默认端口是1099；
			Registry registry = LocateRegistry.getRegistry();
			// 注册远程对象stub的名字为addUser
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

	// 实现添加功能
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

	// 实现删除功能
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

	// 实现修改功能
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

	// 实现查询功能
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
