package cn.edu.ujn.application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import cn.edu.ujn.database.User;

public interface IUserList extends Remote {
	// ���
	void addUser(User user) throws RemoteException;

	// ɾ��
	void deleteUser(String workid) throws RemoteException;

	// �޸�
	void updateUser(User user) throws RemoteException;

	// ��ѯ
	List<User> findAllUser() throws RemoteException;
}
