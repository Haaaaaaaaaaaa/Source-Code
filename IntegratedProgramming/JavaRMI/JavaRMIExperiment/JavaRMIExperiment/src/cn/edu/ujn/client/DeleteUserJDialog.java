package cn.edu.ujn.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.ujn.application.IUserList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteUserJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField workId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DeleteUserJDialog dialog = new DeleteUserJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null); // 窗体居中显示(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DeleteUserJDialog() {
		setTitle("\u5220\u9664\u7528\u6237");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\uFF01\u5220\u9664\u7528\u6237");
			label.setBounds(14, 10, 404, 29);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setForeground(new Color(51, 0, 0));
			label.setFont(new Font("宋体", Font.PLAIN, 24));
			contentPanel.add(label);
		}
		{
			JLabel lblNewLabel = new JLabel(
					"\u8BF7\u8F93\u5165\u9700\u8981\u5220\u9664\u7684\u7528\u6237\u7684\u5DE5\u53F7\uFF1A");
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			lblNewLabel.setBounds(48, 110, 280, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			workId = new JTextField();
			workId.setBounds(320, 109, 86, 24);
			contentPanel.add(workId);
			workId.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					// 点击OK，删除用户
					public void actionPerformed(ActionEvent e) {
						String workid = workId.getText();
						try {
							Registry registry = LocateRegistry.getRegistry(null);
							IUserList stub = (IUserList) registry.lookup("userList");
							stub.deleteUser(workid);
						} catch (Exception e1) {
							System.err.println("Client exception: " + e1.toString());
							e1.printStackTrace();
						}
						// 显示删除成功
						JDialog jdialog = new JDialog();
						jdialog.setVisible(true);
						jdialog.setSize(400, 200);
						jdialog.setLocationRelativeTo(null); // 窗体居中显示

						JLabel jl = new JLabel();// 注意类名别写错了。
						jdialog.getContentPane().add(jl);
						jl.setText("删除成功");
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
