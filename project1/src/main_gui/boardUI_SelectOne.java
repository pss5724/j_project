package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main_vo.BoardVO;

public class boardUI_SelectOne {	//게시물 눌렀을때 특정게시물
	MainUI main;
	JLabel category_l, title_l, writer_l, date_l;
	int food_num, rownum;
	JPanel title_panel, writer_panel, gridbag_panel,
	category_panel, date_panel, contents_panel, button_panel,
	above_panel;
	JButton update_btn,delete_btn;
	JTextArea contents_ta;
	
	public boardUI_SelectOne(MainUI main, int food_num, int rownum) {
		this.main = main;
		this.rownum = rownum;
		this.food_num = food_num;
		init();
	}

	public void init() {
		main.switch_panel(MainUI.BOARD);
		main.board_panel.setLayout(new BorderLayout());
		
		BoardVO vo = main.system.select_one(food_num, rownum);
		
		title_l = new JLabel(vo.getTitle());
		category_l = new JLabel(vo.getCategory());
		writer_l = new JLabel(vo.getId());
		date_l = new JLabel(vo.getDate());
		contents_ta = new JTextArea(4,30);
		contents_ta.setText(vo.getContent());
		update_btn = new JButton("수정");
		delete_btn = new JButton("삭제");
		
		title_panel = new JPanel();
		category_panel = new JPanel();
		writer_panel = new JPanel();
		date_panel = new JPanel();
		contents_panel = new JPanel(new GridLayout(2,1));
		above_panel = new JPanel();
		button_panel = new JPanel();
		
		title_panel.add(title_l);
		category_panel.add(category_l);
		writer_panel.add(writer_l);
		date_panel.add(date_l);
		button_panel.add(update_btn);
		button_panel.add(delete_btn);
		
		above_panel.add(writer_panel);
		above_panel.add(category_panel);
		above_panel.add(date_panel);
		
		contents_panel.add(above_panel);
		contents_panel.add(contents_ta);
		
		
		main.board_panel.add(BorderLayout.NORTH,title_panel);
		main.board_panel.add(BorderLayout.CENTER,contents_panel);
		main.board_panel.add(BorderLayout.SOUTH,button_panel);
		main.content_panel.add(main.board_panel);
		main.jf.setVisible(true);
	}
	
}
