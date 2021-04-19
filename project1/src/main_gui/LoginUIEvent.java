package main_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import main_system.MainSystem;


public class LoginUIEvent extends WindowAdapter implements ActionListener{
		//Field
		MainSystem system;
		LoginUI ui;
		
		//Constructor

		public LoginUIEvent(LoginUI ui, MainSystem system) {
			this.ui = ui;
			this.system = system;
		}
		
		
			
		@Override
		
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if(obj == ui.login) {
				login_proc();
			}else if(obj == ui.join) {
				new JoinUI(system);
			}
		}
		 

		public void login_proc() {
			if(ui.id_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, 
						"���̵� �Է����ּ���");
				ui.id_tf.requestFocus();
			}else if(ui.pass_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, 
						"�н����带 �Է����ּ���");
				ui.pass_tf.requestFocus();
			}else {
				
				int result = system.loginCheck(ui.id_tf.getText(), ui.pass_tf.getText());
				if(result != 0) {
					JOptionPane.showMessageDialog(null, 
							"�α��� ����");
					new MainUI(result, system);
					ui.jf.setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, 
							"�α��� ����");
				}
			}
			
		}
		
		/** ������ �̺�Ʈ ó�� **/
		public void	windowClosing(WindowEvent e) {
			System.out.println("���α׷� ����");
			System.exit(0);
		}
}
