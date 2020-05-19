package cn.edu.ujn.database;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IUserTable extends Remote {
	// ���
	void addUser(User user) throws RemoteException;

	// ɾ��
	void deleteUser(String workid) throws RemoteException;

	// �޸�
	void updateUser(User user) throws RemoteException;

	// ��ѯ
	// List<User> findAllUser() throws RemoteException;
	void findAllUser() throws RemoteException;
}
