package cn.edu.ujn.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.ujn.application.IUserList;
import cn.edu.ujn.database.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateUserJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField workId;
	private JTextField name;
	private JTextField sex;
	private JTextField phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateUserJDialog dialog = new UpdateUserJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateUserJDialog() {
		setTitle("\u4FEE\u6539\u4FE1\u606F");
		setBounds(100, 100, 640, 377);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528\uFF01\u4FEE\u6539\u7528\u6237\u4FE1\u606F");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
			lblNewLabel.setBounds(14, 23, 594, 37);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(349, 18, 1, 1);
			panel.setLayout(null);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\uFF01\u6DFB\u52A0\u7528\u6237");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setForeground(new Color(51, 0, 0));
				label.setFont(new Font("宋体", Font.PLAIN, 24));
				label.setBounds(14, 10, 561, 41);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("\u5DE5\u53F7\uFF1A");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("宋体", Font.PLAIN, 20));
				label.setBounds(215, 64, 81, 35);
				panel.add(label);
			}
			{
				textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(292, 71, 86, 24);
				panel.add(textField);
			}
			{
				JLabel label = new JLabel("\u59D3\u540D\uFF1A");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("宋体", Font.PLAIN, 20));
				label.setBounds(215, 112, 81, 35);
				panel.add(label);
			}
			{
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(292, 119, 86, 24);
				panel.add(textField_1);
			}
			{
				JLabel label = new JLabel("\u6027\u522B\uFF1A");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("宋体", Font.PLAIN, 20));
				label.setBounds(215, 160, 81, 35);
				panel.add(label);
			}
			{
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(292, 167, 86, 24);
				panel.add(textField_2);
			}
			{
				JLabel label = new JLabel("\u7535\u8BDD\uFF1A");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("宋体", Font.PLAIN, 20));
				label.setBounds(215, 208, 81, 35);
				panel.add(label);
			}
			{
				textField_3 = new JTextField();
				textField_3.setColumns(10);
				textField_3.setBounds(292, 215, 86, 24);
				panel.add(textField_3);
			}
		}
		{
			JLabel label = new JLabel("\u5DE5\u53F7\uFF1A");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(229, 73, 81, 35);
			contentPanel.add(label);
		}
		{
			workId = new JTextField();
			workId.setColumns(10);
			workId.setBounds(306, 80, 86, 24);
			contentPanel.add(workId);
		}
		{
			JLabel label = new JLabel("\u59D3\u540D\uFF1A");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(229, 121, 81, 35);
			contentPanel.add(label);
		}
		{
			name = new JTextField();
			name.setColumns(10);
			name.setBounds(306, 128, 86, 24);
			contentPanel.add(name);
		}
		{
			JLabel label = new JLabel("\u6027\u522B\uFF1A");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(229, 169, 81, 35);
			contentPanel.add(label);
		}
		{
			sex = new JTextField();
			sex.setColumns(10);
			sex.setBounds(306, 176, 86, 24);
			contentPanel.add(sex);
		}
		{
			JLabel label = new JLabel("\u7535\u8BDD\uFF1A");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(229, 217, 81, 35);
			contentPanel.add(label);
		}
		{
			phone = new JTextField();
			phone.setColumns(10);
			phone.setBounds(306, 224, 86, 24);
			contentPanel.add(phone);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					// 修改用户信息
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
							stub.updateUser(user);
						} catch (Exception e1) {
							System.err.println("Client exception: " + e1.toString());
							e1.printStackTrace();
						}
						// 显示修改成功
						JDialog jdialog = new JDialog();
						jdialog.setVisible(true);
						jdialog.setSize(400, 200);
						jdialog.setLocationRelativeTo(null); // 窗体居中显示

						JLabel jl = new JLabel();// 注意类名别写错了。
						jdialog.getContentPane().add(jl);
						jl.setText("修改成功");
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
