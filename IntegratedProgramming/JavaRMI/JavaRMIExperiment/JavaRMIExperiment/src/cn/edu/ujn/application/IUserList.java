package cn.edu.ujn.application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import cn.edu.ujn.database.User;

public interface IUserList extends Remote {
	// Ìí¼Ó
	void addUser(User user) throws RemoteException;

	// É¾³ý
	void deleteUser(String workid) throws RemoteException;

	// ÐÞ¸Ä
	void updateUser(User user) throws RemoteException;

	// ²éÑ¯
	List<User> findAllUser() throws RemoteException;
}
