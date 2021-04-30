package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

import main_vo.MemberVO;

public class MemberDeleteUI implements ActionListener{
	//Field
	MainUI main;
	JTextField tf_search;
	JButton btn_delete, btn_search;
	
	String[] colNames = {"ȸ����ȣ","���̵�","��й�ȣ","�̸�","�ڵ���","����"};
	DefaultTableModel model = new DefaultTableModel(colNames, 0);
	Object[] row = new Object[8];	
	JTable table = new JTable(model);
	
	Panel search_content_panel = new Panel(new BorderLayout());
	
	//Constructor
	public MemberDeleteUI(MainUI main) {
		this.main = main;
		init();
	}
	
	//Method
	public void init() {
		main.switch_panel(MainUI.MEMBER);
		main.member_panel.removeAll();
		main.member_panel.setLayout(new BorderLayout());
		
		
		Panel search_top = new Panel();
		JLabel label = new JLabel("���̵�");
		tf_search = new JTextField(10);
		btn_search = new JButton("�˻�");
		label.setFont(Commons.getFont());
		tf_search.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_top.add(label);
		search_top.add(tf_search);
		search_top.add(btn_search);
		search_content_panel.add(BorderLayout.NORTH,search_top);
		
		
		main.member_panel.add(BorderLayout.CENTER, search_content_panel);
		main.content_panel.add(main.member_panel);
		main.jf.setVisible(true);
		
		tf_search.addActionListener(this);
		btn_search.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == tf_search || obj == btn_search) {
			search_proc();
		}else if(obj == btn_delete) {
			int result = JOptionPane.showConfirmDialog(null,"������ �����Ͻðڽ��ϱ�?");
			if(result ==0) {
				System.out.println(tf_search.getText());
				if(main.system.memberDelete(tf_search.getText()) == true) {
					JOptionPane.showMessageDialog(null,"���� �Ϸ�");
					new MemberSelectUI(main);
				}else {
					JOptionPane.showMessageDialog(null,"���� ����");
				}
			}else {
				JOptionPane.showMessageDialog(null,"���� ���");
			}
		}	
		
	}
	
	public void search_proc() {
		if(tf_search.getText().equals("")) {
			JOptionPane.showMessageDialog(null, 
					Commons.getMsg("�˻��� ���̵� �Է����ּ���"));
			tf_search.requestFocus();
		}else {
			
			MemberVO member = main.system.getMemberInfo(tf_search.getText());					
			
			if(member != null) {
				search_content_panel.add(BorderLayout.CENTER, delete_form(member));
				
			}else {
				JOptionPane.showMessageDialog(null,
						Commons.getMsg("������ �����Ͱ� �������� �ʽ��ϴ�."));
			}	
			
		}
		
	}//update_proc
	
	public Panel delete_form(MemberVO member){
		
		Panel delete_form_panel = new Panel(new BorderLayout());
		Panel btn_panel = new Panel();
		btn_delete = new JButton("����");
		btn_panel.add(btn_delete);
		
		createJtableData(member);		
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
		
		
		main.jf.setVisible(true);		
		
		delete_form_panel.add(BorderLayout.CENTER, pane);
		main.member_panel.add(BorderLayout.SOUTH, btn_panel);
		
		btn_delete.addActionListener(this);
		
		return delete_form_panel;
		
	}
	
	
//	table�� ��µǴ� ������ ����
	public void createJtableData(MemberVO member) {
		//int no = 1;
		model.setNumRows(0);
		
			
			row[0] = member.getMemberNum();
			row[1] = member.getId();
			row[2] = member.getPass();
			row[3] = member.getName();
			row[4] = member.getHp();
			row[5] = member.getLocation();
			
			model.addRow(row);			
			//no++;
			
		
		model.fireTableDataChanged();
	}
	

}
