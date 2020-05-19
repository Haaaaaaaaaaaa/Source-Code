package cn.edu.ujn.database;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IUserTable extends Remote {
	// Ìí¼Ó
	void addUser(User user) throws RemoteException;

	// É¾³ý
	void deleteUser(String workid) throws RemoteException;

	// ÐÞ¸Ä
	void updateUser(User user) throws RemoteException;

	// ²éÑ¯
	// List<User> findAllUser() throws RemoteException;
	void findAllUser() throws RemoteException;
}
