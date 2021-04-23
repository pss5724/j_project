package main_gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main_vo.BoardVO;
import main_vo.CommentVO;

public class ContentUI {	//게시물 눌렀을때 특정게시물
	MainUI main;
	JLabel category_l, title_l, writer_l, date_l;
	int food_num, rownum;
	JPanel title_panel, writer_panel, gridbag_panel,
	category_panel, date_panel, contents_panel, button_panel,
	above_panel, comment_panel;
	JButton update_btn,delete_btn;
	JTextArea contents_ta;
	BoardVO contents_vo;
	CommentUI commentUI;
	
	public ContentUI(MainUI main, int food_num, int rownum) {
		this.main = main;
		this.rownum = rownum;
		this.food_num = food_num;
		init();
	}
	public ContentUI(MainUI main, BoardVO contents_vo) {
		this.main = main;
		this.contents_vo = contents_vo;
		commentUI = new CommentUI(this);
		init();
	}

	public void init() {
		main.switch_panel(MainUI.BOARD);
		main.board_panel.setLayout(new BorderLayout());
		
		BoardVO vo = contents_vo;
		
		
		title_l = new JLabel(vo.getTitle());
		title_l.setFont(Commons.getTitleFont());
		category_l = new JLabel(vo.getCategory());
		category_l.setFont(Commons.getContentFont());
		writer_l = new JLabel(vo.getId());
		writer_l.setFont(Commons.getContentFont());
		date_l = new JLabel(vo.getDate());
		date_l.setFont(Commons.getContentFont());
		contents_ta = new JTextArea(10,30);
		contents_ta.setText(vo.getContent());
		contents_ta.setFont(Commons.getContentFont());
		update_btn = new JButton("수정");
		delete_btn = new JButton("삭제");
		
		title_panel = new JPanel();
		category_panel = new JPanel();
		writer_panel = new JPanel();
		date_panel = new JPanel();
		contents_panel = new JPanel(new BorderLayout());
		above_panel = new JPanel();
		button_panel = new JPanel();
		
		title_panel.add(title_l);
		category_panel.add(category_l);
		writer_panel.add(writer_l);
		date_panel.add(date_l);
		
		button_panel.add(update_btn);
		button_panel.add(delete_btn);
		button_panel.setVisible(false);
		
		if(vo.getId().equals(main.member.getId()) || main.member.getId().equals("admin")) {
			button_panel.setVisible(true);
		}
		
		above_panel.add(writer_panel);
		above_panel.add(category_panel);
		above_panel.add(date_panel);
		
		contents_panel.add(BorderLayout.NORTH,above_panel);
		contents_panel.add(BorderLayout.CENTER,contents_ta);
		contents_panel.add(BorderLayout.SOUTH,button_panel);
		
		comment_panel = commentUI.init(); 
		
		main.board_panel.add(BorderLayout.NORTH,title_panel);
		main.board_panel.add(BorderLayout.CENTER,contents_panel);
		main.board_panel.add(BorderLayout.SOUTH,comment_panel);
		
		main.content_panel.add(main.board_panel);
		main.jf.setVisible(true);
		
		update_btn.addActionListener(new ContentAction());	
		delete_btn.addActionListener(new ContentAction());
	}

	class ContentAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj==update_btn) {
				//수정 버튼 눌렀을 때
			}else if(obj==delete_btn) {
				//삭제 버튼 눌렀을 때
			}
		}
		
	}
}
