package main_gui;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import main_vo.BoardVO;

public class MyContentListUI{
	//Field
	MainUI main;
	String[] colNames = {"no","카테고리","작성자","제목","등록일"};
	Object[] row = new Object[5];	//table의 행을 구성하는 배열
	DefaultTableModel model;	
	JTable table;
	Panel mycontent_content_panel;
	ArrayList<BoardVO> content;
	String id;

	
	//Constructor
	public MyContentListUI(MainUI main) {
		this.main = main;
		this.id = main.member.getId();
		init();
	}
	
	//Method
	public void init() {
		main.switch_panel(MainUI.MYCONTENT);
		
		main.mycontent_panel.setLayout(new BorderLayout());
		
		//검색창
		mycontent_content_panel = new Panel(new BorderLayout());
		
//		main.jf.setVisible(true);
		
		createJtableData(content);
		model.setColumnIdentifiers(colNames);
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(false);
		
		table.getColumn("no").setPreferredWidth(20);
		table.getColumn("카테고리").setPreferredWidth(60);
		table.getColumn("작성자").setPreferredWidth(70);
		table.getColumn("제목").setPreferredWidth(200);
		table.getColumn("등록일").setPreferredWidth(80);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();	//셀 가운데 정렬
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);		
		TableColumnModel tcm = table.getColumnModel();
		for(int i=0;i<5;i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		JScrollPane pane = new JScrollPane(table);
		mycontent_content_panel.add(BorderLayout.CENTER, pane);
		main.mycontent_panel.add(BorderLayout.CENTER, mycontent_content_panel);
		main.content_panel.add(BorderLayout.CENTER, main.mycontent_panel);
		main.jf.setVisible(true);
	}
	
	//table의 데이터
	public void createJtableData(ArrayList<BoardVO> content) {
		model = new DefaultTableModel(colNames, 0);
		table = new JTable(model);
		
		model.setNumRows(0);
		content = main.system.mycontent_search(id);
		this.content = content;
		for(int i=0; i<content.size(); i++) {
			row = new Object[5];
			
			row[0] = content.get(i).getContentnum();
			row[1] = content.get(i).getCategory();
			row[2] = content.get(i).getId();
			row[3] = content.get(i).getTitle();
			row[4] = content.get(i).getDate();
			model.addRow(row);
		}
		
		model.fireTableDataChanged();
		table.setModel(model);
		
		//리스트셀렉션 리스너
		
		Articles_clickon ac = new Articles_clickon(this);
		
		
		table.setCellSelectionEnabled(true);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(ac);
	}
	
		
	class Articles_clickon implements ListSelectionListener{
		//Field
		MyContentListUI mycontent;
		
		//Constructor
		public Articles_clickon(MyContentListUI mycontent) {
			this.mycontent = mycontent;
		}
		//Method
		public void	valueChanged(ListSelectionEvent e) {
//			int rownum = table.getSelectedRow();
//			new MyContentSelectOne(main, rownum);
			int i = table.getSelectedRow();
			new MyContentUI(main, content.get(i).getContentnum());
			
		}
	}
		
}



