package main_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main_system.MainSystem;
import main_vo.MemberVO;


public class JoinUIEvent extends WindowAdapter implements ActionListener{
	//Field
	JoinUI ui;
	MainSystem system;
	
	//������
	public JoinUIEvent() {}
	public JoinUIEvent(JoinUI ui) {
		this.ui = ui;
	}
	public JoinUIEvent(JoinUI ui, MainSystem system) {
		this.ui = ui;
		this.system = system;
	}
	
	
	public void	windowClosing(WindowEvent e) {
		System.out.println("ȸ������ ���α׷� ����");
	}
	

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == ui.join) {
			
			if(form_check()) {
				ArrayList<JTextField> jlist = new ArrayList<JTextField>();
				for(Object tf : ui.list) {
					JTextField jtf = (JTextField)tf;
					jlist.add(jtf);
				}
				
				MemberVO member = new MemberVO();
				member.setId(jlist.get(0).getText());
				member.setPass(jlist.get(1).getText());
				member.setName(jlist.get(2).getText());
				member.setHp(jlist.get(3).getText());
				member.setLocation(jlist.get(4).getText());
				
				//boolean result = main.system.getMemberList().add(member);
				boolean result = system.join(member);
				
				if(result) {
					JOptionPane.showMessageDialog(null, 
							Commons.getMsg("ȸ������ ����"));
					
					ui.f.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, 
							Commons.getMsg("ȸ������ ����"));
				}
			}
			
		}else {		
			for(Object obj2 : ui.list) {
				JTextField tf = (JTextField)obj2;
				tf.setText("");
			}

		}
	}
	
	/** �� üũ **/
	public boolean form_check() {
		boolean result = false;

		for(int i=0;i<ui.list.size();i++) {
			JTextField tf = (JTextField)ui.list.get(i);
			
			if(tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, 
							Commons.getMsg(ui.namelist2[i]+"�� �Է����ּ���"));
				tf.requestFocus();
				i=ui.list.size();
			}else if(i == ui.list.size()-1) {
				result = true;
			}
			
		}
		
		return result;
	}
	
	
}
