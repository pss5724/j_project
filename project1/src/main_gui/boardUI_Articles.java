package main_gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import main_vo.ArticleVO;

public class boardUI_Articles {	//카테고리 눌렀을때 게시물들
	MainUI main;
	JPanel inside_panel;
	JPanel buttonpanel;
	String[] colnames = {"NO","카테고리","작성자","제목","등록일"};
	DefaultTableModel model= new DefaultTableModel(colnames,0);
	Object[] row;
	JTable table = new JTable(model);
	JButton write_btn;
	int food_num;
	ArrayList<ArticleVO> articlelist;
	
	public boardUI_Articles(MainUI main, int num) {
		this.main = main;
		food_num=num;
		init();
	}
	//
	public void init() {
		main.switch_panel(MainUI.BOARD);
		inside_panel = new JPanel(new BorderLayout());
		
		model.setNumRows(0);
		articlelist = main.system.select(food_num);
		for(int i=0;i<articlelist.size();i++) {
			row = new Object[5];
			row[0] = articlelist.get(i).getRownum();
			row[1] = articlelist.get(i).getCategory();
			row[2] = articlelist.get(i).getWriter();
			row[3] = articlelist.get(i).getTitle();
			row[4] = articlelist.get(i).getDate();
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
		model.setColumnIdentifiers(colnames);
		table.setModel(model);
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(false);
		
		JScrollPane pane = new JScrollPane(table);
		
		write_btn = new JButton("글쓰기");
		buttonpanel = new JPanel();
		buttonpanel.add(write_btn);
		
		inside_panel.add(pane, BorderLayout.CENTER);
		inside_panel.add(buttonpanel, BorderLayout.SOUTH);

		main.board_panel.add(inside_panel);
			
		main.content_panel.add(main.board_panel);
		main.jf.setVisible(true);
		
		write_btn.addActionListener(new MainUIEvent(main, this));
		
		Articles_clickon ac = new Articles_clickon(this);
		
		table.setCellSelectionEnabled(true);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(ac);
	}
	
	class Articles_clickon implements ListSelectionListener{
		//Field
		boardUI_Articles ba;
		
		//Constructor
		public Articles_clickon(boardUI_Articles ba) {
			this.ba=ba;
		}
		//Method
		public void	valueChanged(ListSelectionEvent e) {
			int rownum = table.getSelectedRow();
			new boardUI_SelectOne(main, food_num, rownum).init();
			
		}
	}
}
