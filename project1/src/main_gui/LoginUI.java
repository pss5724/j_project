package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main_system.MainSystem;




public class LoginUI{
	//Field
	MainSystem system = new MainSystem();
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
				
		JLabel id_label = new JLabel("아이디");
		JLabel pass_label = new JLabel("패스워드");
		id_tf = new JTextField(10);
		pass_tf = new JPasswordField(10);
		
		
		label_panel.add(id_label);	label_panel.add(pass_label);
		tf_panel.add(id_tf);	tf_panel.add(pass_tf);
		
		login = new JButton("로그인");
		join = new JButton("회원가입");
		btn_panel.add(login);	btn_panel.add(join);
		
		jf.add(BorderLayout.NORTH, img_label);
		jf.add(BorderLayout.WEST, label_panel);
		jf.add(BorderLayout.CENTER, tf_panel);
		jf.add(BorderLayout.SOUTH, btn_panel);
		
		jf.setSize(350,200);
		jf.setVisible(true);
		
		LoginUIEvent eventObj = new LoginUIEvent(this, system);
		login.addActionListener(eventObj);
		join.addActionListener(eventObj);
		jf.addWindowListener(eventObj);

	}

	//LoginEvent로 넘길 예정

	

}
