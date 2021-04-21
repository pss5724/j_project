package main_gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MemberSelectUI {
	
		//Field
		MainUI main;
		String[] colNames = {"ȸ����ȣ","���̵�","��й�ȣ","�̸�","�ڵ���","����"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		Object[] row = new Object[8];	
		JTable table = new JTable(model);
		
		
		
		public MemberSelectUI(MainUI main) {
			this.main = main;
			init();
		}
		
		public void init() {
			main.switch_panel(MainUI.MEMBER);
			main.member_panel.removeAll();
			main.member_panel.setLayout(new BorderLayout());
			
			//table�� row data �߰�
			createJtableData();			
			model.setColumnIdentifiers(colNames);
			table.setModel(model);
			table.setRowHeight(20);
			table.setAutoCreateRowSorter(false);
			
			
			table.getColumn("ȸ����ȣ").setPreferredWidth(5);  
			table.getColumn("���̵�").setPreferredWidth(5);
			table.getColumn("��й�ȣ").setPreferredWidth(5);
			table.getColumn("�̸�").setPreferredWidth(5);
			table.getColumn("�ڵ���").setPreferredWidth(5);
			table.getColumn("����").setPreferredWidth(5);
		
			
			table.setModel(model);
			JScrollPane pane = new JScrollPane(table);
			
			
			main.member_panel.add(BorderLayout.CENTER,pane);
			main.content_panel.add(main.member_panel);
			main.jf.setVisible(true);		
		}
		
		
//		table�� ��µǴ� ������ ����
		public void createJtableData() {
			//int no = 1;
			model.setNumRows(0);
			for(int i = 0;i<main.system.getMemberList().size();i++) {
				
				row[0] = main.system.getMemberList().get(i).getMemberNum();
				row[1] = main.system.getMemberList().get(i).getId();
				row[2] = main.system.getMemberList().get(i).getPass();
				row[3] = main.system.getMemberList().get(i).getName();
				row[4] = main.system.getMemberList().get(i).getHp();
				row[5] = main.system.getMemberList().get(i).getLocation();
				
				model.addRow(row);			
				//no++;
			}		
			
			model.fireTableDataChanged();
		}
		
		
	}

