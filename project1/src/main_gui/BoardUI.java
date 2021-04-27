package main_gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
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

public class BoardUI {	//ī�װ� �������� �Խù���
	MainUI main;
	JPanel inside_panel;
	JPanel buttonpanel;
	String[] colnames = {"NO","ī�װ�","�ۼ���","����","�����"};
	DefaultTableModel model= new DefaultTableModel(colnames,0);
	Object[] row;
	JTable table = new JTable(model);
	JButton write_btn;
	int food_num;
	ArrayList<BoardVO> boardlist;
	
	public BoardUI(MainUI main, int num) {
		this.main = main;
		food_num=num;
		init();
	}
	
	public void init() {
		main.switch_panel(MainUI.BOARD);
		inside_panel = new JPanel(new BorderLayout());
		
		model.setNumRows(0);
		boardlist = main.system.select_ctg(main.member, food_num);
		for(int i=0;i<boardlist.size();i++) {
			row = new Object[5];
			row[0] = i+1;
			row[1] = boardlist.get(i).getCategory();
			row[2] = boardlist.get(i).getId();
			row[3] = boardlist.get(i).getTitle();
			row[4] = boardlist.get(i).getDate();
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
		model.setColumnIdentifiers(colnames);
		table.setModel(model);
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(false);
		
		
		table.getColumn("NO").setPreferredWidth(20);
		table.getColumn("ī�װ�").setPreferredWidth(60);
		table.getColumn("�ۼ���").setPreferredWidth(50);
		table.getColumn("����").setPreferredWidth(200);
		table.getColumn("�����").setPreferredWidth(100);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();	//�� ��� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);		
		TableColumnModel tcm = table.getColumnModel();
		for(int i=0;i<5;i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		JScrollPane pane = new JScrollPane(table);
		
		write_btn = new JButton("�۾���");
		buttonpanel = new JPanel();
		buttonpanel.add(write_btn);
		
		inside_panel.add(pane, BorderLayout.CENTER);
		inside_panel.add(buttonpanel, BorderLayout.SOUTH);

		main.board_panel.add(inside_panel);
			
		main.content_panel.add(main.board_panel);
		main.jf.setVisible(true);
		
		write_btn.addActionListener(new writeAction());
		
		
		
		//����Ʈ������ ������
		
		Articles_clickon ac = new Articles_clickon();
		
		table.setCellSelectionEnabled(true);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(ac);
	}
	
	class Articles_clickon implements ListSelectionListener{
		//Method
		public void	valueChanged(ListSelectionEvent e) {
			int rownum = table.getSelectedRow();
			BoardVO s_vo = boardlist.get(rownum);
			new ContentUI(main, s_vo);
		}
	}
	
	class writeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj==write_btn) {
				new WriteUI(main, food_num, WriteUI.WRITE_INSERT);
			}
		}
		
	}
}
