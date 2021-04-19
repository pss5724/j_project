package main_gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import main_vo.BoardVO;

public class SearchUI implements ActionListener{
	//Field
	MainUI main;
	JTextField tf_search;
	JButton btn_search;
	String[] colNames = {"no","카테고리","작성자","제목","등록일"};
	Object[] row = new Object[5];	//table의 행을 구성하는 배열
	DefaultTableModel model;	
	JTable table;
	Panel search_content_panel;

	
	//Consturctor
	public SearchUI(MainUI main) {
		this.main = main;
		init();
	}
	
	//Method
	public void init() {
		main.switch_panel(MainUI.SEARCH);
		
		main.search_panel.setLayout(new BorderLayout());
		
		//검색창
		search_content_panel = new Panel(new BorderLayout());
		Panel search_top = new Panel();
		tf_search = new JTextField(10);
		btn_search = new JButton("검색");
		tf_search.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_top.add(tf_search);
		search_top.add(btn_search);
		
		search_content_panel.add(BorderLayout.NORTH, search_top);
		
		main.search_panel.add(BorderLayout.CENTER, search_content_panel);
		main.content_panel.add(main.search_panel);
		main.jf.setVisible(true);
		
		tf_search.addActionListener(this);
		btn_search.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == tf_search || obj == btn_search) {
			search_proc();
		}
	}
	
	//table의 데이터
	public void createJtableData(BoardVO content) {
		model = new DefaultTableModel(colNames, 0);
		table = new JTable(model);
		
		model.setNumRows(0);
		row[0] = content.getContentnum();
		row[1] = content.getCategory();
		row[2] = content.getId();
		row[3] = content.getTitle();
		row[4] = content.getDate();
		
		model.addRow(row);
		model.fireTableDataChanged();
		table.setModel(model);
		
	}
	
	public void search_proc() {
		if(tf_search.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					Commons.getMsg("검색할 내용을 입력해주세요"));
			tf_search.requestFocus();
			
		}else {
			//검색
			BoardVO content = main.system.search(tf_search.getText());
			
			if(content.getContentnum() != 0) {
				createJtableData(content);
				model.setColumnIdentifiers(colNames);
				table.setRowHeight(20);
				table.setAutoCreateRowSorter(false);
				
				TableColumnModel tcm = table.getColumnModel();
				table.getColumn("no").setPreferredWidth(3);
				table.getColumn("카테고리").setPreferredWidth(5);
				table.getColumn("작성자").setPreferredWidth(5);
				table.getColumn("제목").setPreferredWidth(10);
				table.getColumn("등록일").setPreferredWidth(5);
				
				JScrollPane pane = new JScrollPane(table);
				search_content_panel.add(BorderLayout.CENTER, pane);
				main.jf.setVisible(true);
				
			}else {
				JOptionPane.showMessageDialog(null,
						Commons.getMsg("검색된 데이터가 존재하지 않습니다."));
				
			}
		}
	}//proc
}














