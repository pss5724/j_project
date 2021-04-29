package main_gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main_system.MainSystem;


public class JoinUI {
	//Field
	MainSystem system;
	JFrame f;	
	Panel label_panel, tf_panel, btn_panel;
	String[] namelist = {"아이디","비밀번호","이름","핸드폰","지역"};
	String[] namelist2 = {"아이디","비밀번호","이름",
			"핸드폰1","핸드폰2","핸드폰3","지역"};
	JButton join, reset,check;
	ArrayList<Object> list = new ArrayList<Object>();
	ButtonGroup bg = new ButtonGroup();
	
	JRadioButton rb1 = new JRadioButton("서울");
	JRadioButton rb2 = new JRadioButton("경기");
	JRadioButton rb3 = new JRadioButton("충청");
	JRadioButton rb4 = new JRadioButton("전라");
	JRadioButton rb5 = new JRadioButton("경상");
	JRadioButton rb6 = new JRadioButton("강원");
	JRadioButton rb7 = new JRadioButton("제주");
	
	//Constructor
	public JoinUI(MainSystem system) {
		this.system = system;
		init();
	}
	
	
	//Method
	public void init() {
		f = new JFrame("회원가입");
		label_panel = new Panel(new GridLayout(5,1));
		tf_panel = new Panel(new GridLayout(5,1));
		btn_panel = new Panel();
		join = new JButton("회원가입");
		reset = new JButton("초기화");
		check = new JButton("중복체크");
		btn_panel.add(join);	btn_panel.add(reset);
		
		
		
		for(String name:namelist) {
			Panel l_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
			Label label = new Label(name);
			l_panel.add(label);
			label_panel.add(l_panel);
			
			Panel t_panel = new Panel(new FlowLayout(FlowLayout.LEFT));
			
			if(name.equals("핸드폰")) {
				JTextField hp1 = new JTextField(5);
				JTextField hp2 = new JTextField(5);
				JTextField hp3 = new JTextField(5);
				
				t_panel.add(hp1);	t_panel.add(new Label("-"));				
				t_panel.add(hp2);	t_panel.add(new Label("-"));				
				t_panel.add(hp3);					
				tf_panel.add(t_panel);
				list.add(hp1);
				list.add(hp2);
				list.add(hp3);
				

			}else if(name.equals("아이디")) {
				JTextField tf = new JTextField(20);
				t_panel.add(tf);
				t_panel.add(check);
				tf_panel.add(t_panel);
				list.add(tf);		
			}else if(name.equals("지역")) {
				
				
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
				list.add(tf);				
			}
			
		}//for

	
	
		f.add(BorderLayout.WEST, label_panel);
		f.add(BorderLayout.CENTER, tf_panel);
		f.add(BorderLayout.SOUTH, btn_panel);
		
		f.setSize(500,300);
		f.setLocation(100,100);
		f.setVisible(true);
		
		JoinUIEvent eventObj = new JoinUIEvent(this);
		f.addWindowListener(eventObj);
		join.addActionListener(eventObj);
		reset.addActionListener(eventObj);
		check.addActionListener(eventObj);
		
	}
}
