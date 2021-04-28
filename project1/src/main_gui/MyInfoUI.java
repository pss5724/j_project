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
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main_vo.MemberVO;

public class MyInfoUI implements ActionListener{
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
		public MyInfoUI(MainUI main) {
			this.main = main;
			init();
		}
		
		//Method
		public void init() {
			System.out.println("Ȯ��2");
			main.switch_panel(MainUI.MY);
			main.my_panel.removeAll();
			main.my_panel.setLayout(new BorderLayout());
			
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
			
			tf_list.get(0).setText(main.system.getMemberInfo(main.member.getId()).getPass());
			tf_list.get(1).setText(main.system.getMemberInfo(main.member.getId()).getName());
			tf_list.get(2).setText(main.system.getMemberInfo(main.member.getId()).getHp());
			
			update_form_panel.setBackground(Color.lightGray);
			update_form_panel.add(BorderLayout.WEST, label_panel);
			update_form_panel.add(BorderLayout.CENTER, tf_panel);
			update_form_panel.add(BorderLayout.SOUTH, btn_panel);
			
			main.my_panel.add(update_form_panel);
			main.content_panel.add(main.my_panel);
			
			main.jf.setVisible(true);
			
			btn_update.addActionListener(this);
			btn_reset.addActionListener(this);
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if(obj == btn_update) {
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
			
			boolean result = main.system.memberUpdate(member,main.member.getId());
			if(result != false) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("�����Ϸ�"));
				new MyInfoUI(main);
			}else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("��������"));
			}
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
