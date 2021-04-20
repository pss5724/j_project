package main_gui;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
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
	String[] colNames = {"no","ī�װ�","�ۼ���","����","�����"};
	Object[] row = new Object[5];	//table�� ���� �����ϴ� �迭
	DefaultTableModel model;	
	JTable table;
	Panel search_content_panel;
	ArrayList<BoardVO> content;
	String title;

	
	//Consturctor
	public SearchUI(MainUI main) {
		this.main = main;
		init();
	}
	
	//Method
	public void init() {
		main.switch_panel(MainUI.SEARCH);
		
		main.search_panel.setLayout(new BorderLayout());
		
		//�˻�â
		search_content_panel = new Panel(new BorderLayout());
		Panel search_top = new Panel();
		tf_search = new JTextField(10);
		btn_search = new JButton("�˻�");
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
	
	//table�� ������
	public void createJtableData(ArrayList<BoardVO> content) {
		model = new DefaultTableModel(colNames, 0);
		table = new JTable(model);
		
		model.setNumRows(0);
		title = tf_search.getText();
		content = main.system.search(title);
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
		
	}
	
	public void search_proc() {
		if(tf_search.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					Commons.getMsg("�˻��� ������ �Է����ּ���"));
			tf_search.requestFocus();
			
		}else {
			//�˻�
				
//			if(content[0].getContentnum() != 0) {
				createJtableData(content);
				model.setColumnIdentifiers(colNames);
				table.setRowHeight(20);
				table.setAutoCreateRowSorter(false);
				
				TableColumnModel tcm = table.getColumnModel();
				table.getColumn("no").setPreferredWidth(20);
				table.getColumn("ī�װ�").setPreferredWidth(60);
				table.getColumn("�ۼ���").setPreferredWidth(50);
				table.getColumn("����").setPreferredWidth(200);
				table.getColumn("�����").setPreferredWidth(100);
				
				JScrollPane pane = new JScrollPane(table);
				search_content_panel.add(BorderLayout.CENTER, pane);
				main.jf.setVisible(true);
				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("�˻��� �����Ͱ� �������� �ʽ��ϴ�."));
//				
//			}
		}
	}//proc
}














