package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main_vo.BoardVO;

public class MyContentSelectOne {	//게시물 눌렀을때 특정게시물
	MainUI main;
	JLabel title_l, writer_l, category_l, date_l;
	int rownum;
	JPanel mycontentselect_panel, title_panel, center_panel,
		content_panel, button_panel, top_panel;
	JButton update_btn, delete_btn;
	JTextArea content_ta;
	
	public MyContentSelectOne(MainUI main, int rownum) {
		this.main = main;	
		this.rownum = rownum;
		init();
	
	}
	
	public void init() {
		main.switch_panel(MainUI.MYCONTENT);
		main.mycontent_panel.setLayout(new BorderLayout());
		
		BoardVO board = main.system.select_one(rownum);
		title_l = new JLabel(board.getTitle());
		writer_l = new JLabel(board.getId());
		category_l = new JLabel(board.getCategory());
		date_l = new JLabel(board.getDate());
		content_ta = new JTextArea(4,30);
		content_ta.setText(board.getContent());
		update_btn = new JButton("수정");
		delete_btn = new JButton("삭제");
		
		mycontentselect_panel = new JPanel();
		top_panel = new JPanel(new GridLayout(2,1));
		title_panel = new JPanel();
		center_panel = new JPanel(new GridLayout(1,3));
		content_panel = new JPanel();
		button_panel = new JPanel(new GridLayout(1,2));
		
		title_panel.add(title_l);
		center_panel.add(writer_l);
		center_panel.add(category_l);
		center_panel.add(date_l);
		content_panel.add(content_ta);
		button_panel.add(update_btn);
		button_panel.add(delete_btn);
		top_panel.add(title_panel);
		top_panel.add(center_panel);
		
		main.mycontent_panel.add(BorderLayout.NORTH, top_panel);
		main.mycontent_panel.add(BorderLayout.CENTER, content_panel);
		main.mycontent_panel.add(BorderLayout.SOUTH, button_panel);
		main.content_panel.add(main.mycontent_panel);
		
		main.jf.setVisible(true);
	}
	
}
