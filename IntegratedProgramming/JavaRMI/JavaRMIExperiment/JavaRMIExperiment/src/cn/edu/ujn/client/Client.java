package cn.edu.ujn.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import cn.edu.ujn.application.IUserList;
import cn.edu.ujn.database.User;

public class Client {
	public static void main(String args[]) {
		// ����
		// User user = new User();
		// user.setWorkid("006");
		// user.setName("ct");
		// user.setSex("��");
		// user.setPhone("17860630030");
		// try {
		// Registry registry = LocateRegistry.getRegistry(null);
		// IUserList stub = (IUserList) registry.lookup("userList");
		// stub.addUser(user);
		// // System.out.println("response: " + response);
		// } catch (Exception e) {
		// System.err.println("Client exception: " + e.toString());
		// e.printStackTrace();
		// }

		// ɾ��
		// String workid ="006";
		// try {
		// Registry registry = LocateRegistry.getRegistry(null);
		// IUserList stub = (IUserList) registry.lookup("userList");
		// stub.deleteUser(workid);
		// // System.out.println("response: " + response);
		// } catch (Exception e) {
		// System.err.println("Client exception: " + e.toString());
		// e.printStackTrace();
		// }
		// }

		// �޸�
		// User user = new User();
		// user.setWorkid("002");
		// user.setName("����2");
		// user.setSex("��");
		// user.setPhone("17860630030");
		// try {
		// Registry registry = LocateRegistry.getRegistry(null);
		// IUserList stub = (IUserList) registry.lookup("userList");
		// stub.updateUser(user);
		// // System.out.println("response: " + response);
		// } catch (Exception e) {
		// System.err.println("Client exception: " + e.toString());
		// e.printStackTrace();
		// }

		// ��ѯ
		try {
			List<User> lists = new ArrayList<User>();
			User user = new User();
			Registry registry = LocateRegistry.getRegistry(null);
			IUserList stub = (IUserList) registry.lookup("userList");
			// stub.findAllUser();
			user = (User) stub.findAllUser();
			System.out.println(user);
			// System.out.println("response: " + response);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
