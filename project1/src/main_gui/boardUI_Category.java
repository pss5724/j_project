package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

//게시판


public class boardUI_Category {
	MainUI main;
	JButton all_food,china_food,west_food,japan_food,
	korea_food,boonsik;
	JPanel button_panel;
	ArrayList<JButton> btnlist;
	public static final int ALL_FOOD = 0;
	public static final int CHINA_FOOD=1;
	public static final int WEST_FOOD=2;
	public static final int JAPAN_FOOD=3;
	public static final int BOONSIK=4;
	public static final int KOREA_FOOD=5;
	
	
	public boardUI_Category(MainUI main) {
		this.main = main;
		init();
	}
	
	public void init() {
		main.switch_panel(MainUI.BOARD);
		main.board_panel.setLayout(new GridLayout(3,2,20,20));
		btnlist = new ArrayList<JButton>();
		
		all_food = new JButton("전체");
		china_food = new JButton("중식");
		west_food = new JButton("양식");
		japan_food = new JButton("일식");
		boonsik = new JButton("분식");
		korea_food = new JButton("한식");
		
		btnlist.add(all_food);
		btnlist.add(china_food);
		btnlist.add(west_food);
		btnlist.add(japan_food);
		btnlist.add(boonsik);
		btnlist.add(korea_food);
		
		for(JButton btn: btnlist) {
			btn.setSize(300, 300);
//			button_panel = new JPanel();
//			button_panel.add(btn);
//			main.board_panel.add(button_panel);
			main.board_panel.add(btn);
			btn.addActionListener(new MainUIEvent(main, this));
		}
		
			
		main.content_panel.add(main.board_panel);
		main.jf.setVisible(true);
	}
	
}
