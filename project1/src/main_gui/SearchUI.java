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
	String[] colNames = {"no","ī�װ�","�ۼ���","����","�����"};
	Object[] row = new Object[5];	//table�� ���� �����ϴ� �迭
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
					Commons.getMsg("�˻��� ������ �Է����ּ���"));
			tf_search.requestFocus();
			
		}else {
			//�˻�
			BoardVO content = main.system.search(tf_search.getText());
			
			if(content.getContentnum() != 0) {
				createJtableData(content);
				model.setColumnIdentifiers(colNames);
				table.setRowHeight(20);
				table.setAutoCreateRowSorter(false);
				
				TableColumnModel tcm = table.getColumnModel();
				table.getColumn("no").setPreferredWidth(3);
				table.getColumn("ī�װ�").setPreferredWidth(5);
				table.getColumn("�ۼ���").setPreferredWidth(5);
				table.getColumn("����").setPreferredWidth(10);
				table.getColumn("�����").setPreferredWidth(5);
				
				JScrollPane pane = new JScrollPane(table);
				search_content_panel.add(BorderLayout.CENTER, pane);
				main.jf.setVisible(true);
				
			}else {
				JOptionPane.showMessageDialog(null,
						Commons.getMsg("�˻��� �����Ͱ� �������� �ʽ��ϴ�."));
				
			}
		}
	}//proc
}














