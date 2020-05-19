package cn.edu.ujn.server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.ujn.hello.Hello;

public class Server implements Hello {

	public Server() {
	}

	public String sayHello() {
		return "Hello, world!";
	}

	public static void main(String args[]) {
		// ≤‚ ‘ ˝æ›ø‚
//    	try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		String url = "jdbc:mysql://localhost:3306/javarmi?user=root&password=&characterEncoding=gb2312";
//		String url = "jdbc:mysql://localhost:3306/javarmi?useSSL=false";
//		String user = "root";
//		String password = "";
//    	try {
//			Connection conn=DriverManager.getConnection(url,user,password);
//			PreparedStatement pre1;
//			pre1 = conn.prepareStatement("insert into user(workid,name,sex,phone) VALUES(?,?,?,?)");
//			pre1.setString(1, "001");
//			pre1.setString(2, "ct");
//			pre1.setString(3, "nan");
//			pre1.setString(4, "123456");
//			pre1.executeUpdate();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try {
			Server obj = new Server();
			Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Hello", stub);

			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}