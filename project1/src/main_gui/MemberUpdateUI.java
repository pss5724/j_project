package main_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main_vo.MemberVO;


public class MemberUpdateUI implements ActionListener {
	//Field
	MainUI main;
	JTextField tf_search;
	JButton btn_search, btn_update, btn_reset;
	ArrayList<JTextField> tf_list;
	ArrayList<Object> list = new ArrayList<Object>();
	
	ButtonGroup bg = new ButtonGroup();
	
	JRadioButton rb1 = new JRadioButton("����");
	JRadioButton rb2 = new JRadioButton("���");
	JRadioButton rb3 = new JRadioButton("��û");
	JRadioButton rb4 = new JRadioButton("����");
	JRadioButton rb5 = new JRadioButton("���");
	JRadioButton rb6 = new JRadioButton("����");
	JRadioButton rb7 = new JRadioButton("����");
	
	//Constructor
	public MemberUpdateUI(MainUI main) {
		this.main = main;
		init();
	}
	
	//Method
	public void init() {
		main.switch_panel(MainUI.MEMBER);
		main.member_panel.removeAll();
		main.member_panel.setLayout(new BorderLayout());
		
		Panel search_content_panel = new Panel(new BorderLayout());
		Panel search_top = new Panel();
		JLabel label = new JLabel("���̵�");
		tf_search = new JTextField(10);
		btn_search = new JButton("�˻�");
		label.setFont(Commons.getFont());
		tf_search.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_top.add(label);
		search_top.add(tf_search);
		search_top.add(btn_search);
		search_content_panel.add(BorderLayout.NORTH,search_top);
		search_content_panel.add(BorderLayout.CENTER, update_form());
		
		main.member_panel.add(BorderLayout.CENTER, search_content_panel);
		main.content_panel.add(main.member_panel);
		main.jf.setVisible(true);
		
		tf_search.addActionListener(this);
		btn_search.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == tf_search || obj == btn_search) {
			update_proc();
		}else if(obj == btn_update) {
			update_result_proc();
		}else if(obj == btn_reset) {
			for(JTextField tf:tf_list) tf.setText("");
		}		
		
	}
	
	
	public void update_result_proc() {
		
		MemberVO member = new MemberVO();
		member.setPass(tf_list.get(0).getText());
		member.setName(tf_list.get(1).getText());
		member.setHp(tf_list.get(2).getText());
		member.setLocation(location_check());
		
		if(tf_list.get(0).getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("��й�ȣ�� �Է��ϼ���"));
			tf_list.get(0).requestFocus();
		}else if(tf_list.get(1).getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("�̸��� �Է��ϼ���"));
			tf_list.get(1).requestFocus();
		}else if(tf_list.get(2).getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("�ڵ����� �Է��ϼ���"));
			tf_list.get(1).requestFocus();
		}else if(location_check().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("������ �����ϼ���"));
		}else {
			boolean result = main.system.memberUpdate(member,main.member.getId());
				if(result != false) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("�����Ϸ�"));
					new MyInfoUI(main);
				}	else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("��������"));
				}
		}
	}
	
	
	public void update_proc() {
		if(tf_search.getText().equals("")) {
			JOptionPane.showMessageDialog(null, 
					Commons.getMsg("�˻��� ���̵� �Է����ּ���"));
			tf_search.requestFocus();
		}else {
			
			MemberVO member = main.system.memberSearch(tf_search.getText());					
			
			if(member != null) {
				tf_list.get(0).setText(member.getPass());
				tf_list.get(1).setText(member.getName());
				tf_list.get(2).setText(member.getHp());
				
			}else {
				JOptionPane.showMessageDialog(null,
						Commons.getMsg("������ �����Ͱ� �������� �ʽ��ϴ�."));
			}	
			
		}
		
	}//update_proc
	
	
	public Panel update_form() {
		tf_list = new ArrayList<JTextField>();
		Panel update_form_panel = new Panel(new BorderLayout());
		Panel label_panel = new Panel(new GridLayout(4,1));
		Panel tf_panel = new Panel(new GridLayout(4,1));
		Panel btn_panel = new Panel();
		String[] namelist = {"��й�ȣ","�̸�","�ڵ���","����"};
		btn_update = new JButton("����");
		btn_reset = new JButton("���");
		btn_update.setFont(Commons.getFont());
		btn_reset.setFont(Commons.getFont());
		btn_panel.add(btn_update);	btn_panel.add(btn_reset);		
		
		for(String name:namelist) {
			Panel l_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
			Label label = new Label(name);
			l_panel.add(label);
			label_panel.add(l_panel);
			
			Panel t_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
			
			if(name.equals("����")) {
				bg.add(rb1);
				bg.add(rb2);
				bg.add(rb3);
				bg.add(rb4);
				bg.add(rb5);
				bg.add(rb6);
				bg.add(rb7);

				t_panel.add(rb1); 
				t_panel.add(rb2); 
				t_panel.add(rb3);
				t_panel.add(rb4);
				t_panel.add(rb5);
				t_panel.add(rb6);
				t_panel.add(rb7);
				
				tf_panel.add(t_panel);
				
			}
				else {
				JTextField tf = new JTextField(20);
				t_panel.add(tf);
				tf_panel.add(t_panel);
				tf_list.add(tf);
			}
			
		}//for
		
		update_form_panel.setBackground(Color.lightGray);
		update_form_panel.add(BorderLayout.WEST, label_panel);
		update_form_panel.add(BorderLayout.CENTER, tf_panel);
		update_form_panel.add(BorderLayout.SOUTH, btn_panel);
		
		btn_update.addActionListener(this);
		btn_reset.addActionListener(this);
		
		return update_form_panel;
	}
	
	public String location_check() {
		String result = "";

		if (rb1.isSelected() == true) {
			result = "����";
		} else if (rb2.isSelected() == true) {
			result = "���";
		} else if (rb3.isSelected() == true) {
			result = "��û";
		} else if (rb4.isSelected() == true) {
			result = "����";
		} else if (rb5.isSelected() == true) {
			result = "���";
		} else if (rb6.isSelected() == true) {
			result = "����";
		} else if (rb7.isSelected() == true) {
			result = "����";
		}

		return result;
	}
	
}
