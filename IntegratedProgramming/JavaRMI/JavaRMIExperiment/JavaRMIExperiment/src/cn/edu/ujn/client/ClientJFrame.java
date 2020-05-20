package cn.edu.ujn.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cn.edu.ujn.application.IUserList;
import cn.edu.ujn.database.User;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.ScrollPane;

public class ClientJFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton findAllUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientJFrame frame = new ClientJFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); // 窗体居中显示
					frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientJFrame() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		setTitle("\u5BA2\u6237\u7AEF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 371);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5BA2\u6237\u7AEF\uFF01");
		lblNewLabel.setBounds(5, 5, 690, 29);
		lblNewLabel.setForeground(new Color(51, 0, 0));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		findAllUser = new JButton("\u67E5\u8BE2\u4FE1\u606F");
		findAllUser.addActionListener(new ActionListener() {
			// 查询信息的事件
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				try {
					List<User> lists = new ArrayList<User>();
					Registry registry = LocateRegistry.getRegistry(null);
					IUserList stub = (IUserList) registry.lookup("userList");
					lists = stub.findAllUser();
					for (User user : lists) {
						Vector a = new Vector();
						a.add(user.getWorkid());
						a.add(user.getName());
						a.add(user.getSex());
						a.add(user.getPhone());
						System.out.println(a);
						dtm.addRow(a);
						System.out.println(lists);
						// System.out.println("response: " + response);
					}
				} catch (Exception e1) {
					System.err.println("Client exception: " + e1.toString());
					e1.printStackTrace();
				}
			}
		});

		table = new JTable();
		table.setRowHeight(25);// 设置表格行宽
		table.setBounds(5, 47, 690, 160);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));// 表格外边框
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, },
						new String[] { "工号", "姓名", "性别", "电话" }));
		contentPane.add(table);

		findAllUser.setFont(new Font("宋体", Font.PLAIN, 20));
		findAllUser.setBounds(378, 245, 135, 50);
		contentPane.add(findAllUser);

		JButton updateUser = new JButton("\u4FEE\u6539\u4FE1\u606F");
		updateUser.addActionListener(new ActionListener() {
			// 修改信息
			public void actionPerformed(ActionEvent e) {
				// 打开指定的窗口
				UpdateUserJDialog updateUser = new UpdateUserJDialog();
				updateUser.show();
				updateUser.setLocationRelativeTo(null); // 窗体居中显示
			}
		});
		updateUser.setFont(new Font("宋体", Font.PLAIN, 20));
		updateUser.setBounds(201, 245, 135, 50);
		contentPane.add(updateUser);

		JButton addUser = new JButton("\u6DFB\u52A0\u7528\u6237");
		// 添加信息按钮
		addUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 打开指定的窗口
				AddUserJDialog addUser = new AddUserJDialog();
				addUser.show();
				addUser.setLocationRelativeTo(null); // 窗体居中显示
			}
		});
		addUser.setFont(new Font("宋体", Font.PLAIN, 20));
		addUser.setBounds(25, 245, 135, 50);
		contentPane.add(addUser);

		JButton deleteUser = new JButton("\u5220\u9664\u7528\u6237");
		deleteUser.addActionListener(new ActionListener() {
			// 删除用户
			public void actionPerformed(ActionEvent e) {
				DeleteUserJDialog deleteUser = new DeleteUserJDialog();
				deleteUser.show();
				deleteUser.setLocationRelativeTo(null); // 窗体居中显示
			}
		});
		deleteUser.setFont(new Font("宋体", Font.PLAIN, 20));
		deleteUser.setBounds(551, 245, 135, 50);
		contentPane.add(deleteUser);
	}

	private void exit() {
		int opt = JOptionPane.showConfirmDialog(this, "确认要退出系统吗？");
		if (opt == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
