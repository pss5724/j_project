package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

//�Խ���


public class CategoryUI {
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
	
	
	public CategoryUI(MainUI main) {
		this.main = main;
		init();
	}
	
	public void init() {
		main.switch_panel(MainUI.BOARD);
		main.board_panel.setLayout(new GridLayout(3,2,20,20));
		btnlist = new ArrayList<JButton>();
		
		all_food = new JButton("��ü");
		china_food = new JButton("�߽�");
		west_food = new JButton("���");
		japan_food = new JButton("�Ͻ�");
		boonsik = new JButton("�н�");
		korea_food = new JButton("�ѽ�");
		
		btnlist.add(all_food);
		btnlist.add(china_food);
		btnlist.add(west_food);
		btnlist.add(japan_food);
		btnlist.add(boonsik);
		btnlist.add(korea_food);
		
		for(JButton btn: btnlist) {
			btn.setSize(300, 300);
			main.board_panel.add(btn);
			btn.addActionListener(new ctgAction());
		}
		
		main.content_panel.add(main.board_panel);
		main.jf.setVisible(true);
	}
	
	
	class ctgAction implements ActionListener{	//�Խ��ǿ��� �� ī�װ��� ������ ��

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if(obj == btnlist.get(CategoryUI.ALL_FOOD)) {
				new BoardUI(main, CategoryUI.ALL_FOOD);
			}else if(obj == btnlist.get(CategoryUI.CHINA_FOOD)) {
				new BoardUI(main, CategoryUI.CHINA_FOOD);
			}else if(obj == btnlist.get(CategoryUI.WEST_FOOD)) {
				new BoardUI(main, CategoryUI.WEST_FOOD);
			}else if(obj == btnlist.get(CategoryUI.JAPAN_FOOD)) {
				new BoardUI(main, CategoryUI.JAPAN_FOOD);
			}else if(obj == btnlist.get(CategoryUI.BOONSIK)) {
				new BoardUI(main, CategoryUI.BOONSIK);
			}else if(obj == btnlist.get(CategoryUI.KOREA_FOOD)) {
				new BoardUI(main, CategoryUI.KOREA_FOOD);
			}
		}
	}
}