package cn.edu.ujn.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import cn.edu.ujn.application.IUserList;
import cn.edu.ujn.database.User;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUserJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField workId;
	private JTextField name;
	private JTextField sex;
	private JTextField phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddUserJDialog dialog = new AddUserJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null); // 窗体居中显示
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUserJDialog() {
		setTitle("\u6DFB\u52A0\u4FE1\u606F");
		setBounds(100, 100, 607, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528\uFF01\u6DFB\u52A0\u7528\u6237");
			lblNewLabel.setForeground(new Color(51, 0, 0));
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(14, 10, 561, 41);
			contentPanel.add(lblNewLabel);
		}

		JLabel lblNewLabel_1 = new JLabel("\u5DE5\u53F7\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(215, 64, 81, 35);
		contentPanel.add(lblNewLabel_1);

		workId = new JTextField();
		workId.setBounds(292, 71, 86, 24);
		contentPanel.add(workId);
		workId.setColumns(10);

		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(215, 112, 81, 35);
		contentPanel.add(label);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(292, 119, 86, 24);
		contentPanel.add(name);

		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(215, 160, 81, 35);
		contentPanel.add(label_1);

		sex = new JTextField();
		sex.setColumns(10);
		sex.setBounds(292, 167, 86, 24);
		contentPanel.add(sex);

		JLabel label_2 = new JLabel("\u7535\u8BDD\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(215, 208, 81, 35);
		contentPanel.add(label_2);

		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(292, 215, 86, 24);
		contentPanel.add(phone);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					// 点击OK进行添加
					public void actionPerformed(ActionEvent e) {
						String workid = workId.getText();
						String Name = name.getText();
						String Sex = sex.getText();
						String Phone = phone.getText();

						User user = new User();
						user.setWorkid(workid);
						user.setName(Name);
						user.setSex(Sex);
						user.setPhone(Phone);
						try {
							Registry registry = LocateRegistry.getRegistry(null);
							IUserList stub = (IUserList) registry.lookup("userList");
							stub.addUser(user);
						} catch (Exception e1) {
							System.err.println("Client exception: " + e1.toString());
							e1.printStackTrace();
						}
						// 显示添加成功
						JDialog jdialog = new JDialog();
						jdialog.setVisible(true);
						jdialog.setSize(400, 200);
						jdialog.setLocationRelativeTo(null); // 窗体居中显示

						JLabel jl = new JLabel();// 注意类名别写错了。
						jdialog.getContentPane().add(jl);
						jl.setText("添加成功");
						jl.setFont(new Font("宋体", Font.PLAIN, 20));
						jl.setVerticalAlignment(JLabel.CENTER);
						jl.setHorizontalAlignment(JLabel.CENTER);

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// 隐藏添加信息对话框
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
