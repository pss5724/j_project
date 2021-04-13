package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginUI{
	//Field
	MainUI main;
	JFrame jf;
	JButton login, join;
	JTextField id_tf;
	JPasswordField pass_tf;
		
	//Constructor
	public LoginUI() {
		init();
	}
	 
	//Method
	public void init() {
		
		
		ImageIcon icon = new ImageIcon("images/title.png");
		JLabel img_label = new JLabel(icon);
		
		jf = new JFrame();
		Panel label_panel = new Panel(new GridLayout(2,1,0,5));
		Panel tf_panel = new Panel(new GridLayout(2,1,0,5));
		Panel btn_panel = new Panel();
				
		JLabel id_label = new JLabel("���̵�");
		JLabel pass_label = new JLabel("�н�����");
		id_tf = new JTextField(10);
		pass_tf = new JPasswordField(10);
		
		
		label_panel.add(id_label);	label_panel.add(pass_label);
		tf_panel.add(id_tf);	tf_panel.add(pass_tf);
		
		login = new JButton("LOGIN");
		join = new JButton("JOIN");
		btn_panel.add(login);	btn_panel.add(join);
		
		jf.add(BorderLayout.NORTH, img_label);
		jf.add(BorderLayout.WEST, label_panel);
		jf.add(BorderLayout.CENTER, tf_panel);
		jf.add(BorderLayout.SOUTH, btn_panel);
		
		jf.setSize(350,200);
		jf.setVisible(true);
		
//		login.addActionListener(this);
//		join.addActionListener(this);
	}

	//LoginEvent�� �ѱ� ����
	//@Override
	
//	public void actionPerformed(ActionEvent e) {
//		Object obj = e.getSource();
//		
//		if(obj == login) {
//			login_proc();
//		}else if(obj == join) {
//			//join_proc();
//		}
//	}
	

//	public void login_proc() {
//		if(id_tf.getText().equals("")) {
//			JOptionPane.showMessageDialog(null, 
//					"���̵� �Է����ּ���");
//			id_tf.requestFocus();
//		}else if(pass_tf.getText().equals("")) {
//			JOptionPane.showMessageDialog(null, 
//					"�н����带 �Է����ּ���");
//			pass_tf.requestFocus();
//		}else {
			//�α���üũ : system.loginCheck(���̵�, �н�����); 
//			boolean result = loginCheck(id_tf.getText(), pass_tf.getText());
//			if(result) {
//				JOptionPane.showMessageDialog(null, 
//						"�α��� ����");
//				new MainUI();
//				login.setText("    �α׾ƿ�    ");
				
//			}else {
//				JOptionPane.showMessageDialog(null, 
//						"�α��� ����");
//			}
//		}
		
//	}
	

}
