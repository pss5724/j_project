package main_gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import main_system.MainSystem;


public class JoinUI {
	//Field
	MainSystem system;
	JFrame f;	
	Panel label_panel, tf_panel, btn_panel;
	String[] namelist = {"���̵�","��й�ȣ","�̸�","�ڵ���","����"};
	String[] namelist2 = {"���̵�","��й�ȣ","�̸�",
			"�ڵ���1","�ڵ���2","�ڵ���3","����"};
	Button join, reset;
	ArrayList<Object> list = new ArrayList<Object>();
	MainUI main;
	
	//Constructor
	public JoinUI() {
		init();
	}
	
	public JoinUI(MainUI main, MainSystem system) {
		this.main = main;
		this.system = system;
		init();
	}
	
	//Method
	public void init() {
		f = new JFrame("ȸ������");
		label_panel = new Panel(new GridLayout(5,1));
		tf_panel = new Panel(new GridLayout(5,1));
		btn_panel = new Panel();
		join = new Button("ȸ������");
		reset = new Button("����ϱ�");
		btn_panel.add(join);	btn_panel.add(reset);
		
		for(String name:namelist) {
			Panel l_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
			Label label = new Label(name);
			l_panel.add(label);
			label_panel.add(l_panel);
			
			Panel t_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
			
			if(name.equals("�ڵ���")) {
				JTextField hp1 = new JTextField(5);
				JTextField hp2 = new JTextField(5);
				JTextField hp3 = new JTextField(5);
				t_panel.add(hp1);	t_panel.add(new Label("-"));				
				t_panel.add(hp2);	t_panel.add(new Label("-"));				
				t_panel.add(hp3);					
				tf_panel.add(t_panel);	

			}
				else {
				JTextField tf = new JTextField(20);
				t_panel.add(tf);
				tf_panel.add(t_panel);
				list.add(tf);				
			}
			
		}//for
		
		f.add(BorderLayout.WEST, label_panel);
		f.add(BorderLayout.CENTER, tf_panel);
		f.add(BorderLayout.SOUTH, btn_panel);
		
		f.setSize(400,300);
		f.setLocation(100,100);
		f.setVisible(true);
		
		JoinUIEvent eventObj = new JoinUIEvent(this, system);
		f.addWindowListener(eventObj);
		join.addActionListener(eventObj);
		reset.addActionListener(eventObj);
		
	}
}
