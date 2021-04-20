package main_gui;

import java.awt.BorderLayout;
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


public class MyContentUI{
	//Field
	MainUI main;
	LoginUI login;
	String[] colNames = {"NO","카테고리","작성자","제목","등록일"};
	Object[] row = new Object[5];	//table의 행을 구성하는 배열
	DefaultTableModel model = new DefaultTableModel(colNames, 0);
	JTable table = new JTable(model);
	String id = login.id_tf.getText();
	ArrayList<BoardVO> boardlist;
	
	//Constructor
	public MyContentUI(MainUI main) {
		this.main = main;
		init();
	}
	
	//Method
	public void init() {
		main.switch_panel(MainUI.MYCONTENT);
		main.mycontent_panel.setLayout(new BorderLayout());
		
		model.setNumRows(0);
		boardlist = main.system.mycontent_search(id);
		for(int i=0; i<boardlist.size(); i++) {
			row = new Object[5];
			row[0] = boardlist.get(i).getContentnum();
			row[1] = boardlist.get(i).getCategory();
			row[2] = boardlist.get(i).getId();
			row[3] = boardlist.get(i).getTitle();
			row[4] = boardlist.get(i).getDate();
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
		model.setColumnIdentifiers(colNames);
		table.setModel(model);
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(false);
		
		table.getColumn("no").setPreferredWidth(20);
		table.getColumn("카테고리").setPreferredWidth(60);
		table.getColumn("작성자").setPreferredWidth(50);
		table.getColumn("제목").setPreferredWidth(200);
		table.getColumn("등록일").setPreferredWidth(100);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();	//셀 가운데 정렬
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i=0;i<5;i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		JScrollPane pane = new JScrollPane(table);
		main.content_panel.add(pane, BorderLayout.CENTER);
		main.content_panel.add(main.mycontent_panel);
		main.jf.setVisible(true);
		
		//ListSelectionListener
		Articles_clickon ac = new Articles_clickon(this);
		
		table.setCellSelectionEnabled(true);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(ac);
	}
	
	class Articles_clickon implements ListSelectionListener{
		//Field
		MyContentUI mcu;
		
		//Constructor
		public Articles_clickon(MyContentUI mcu) {
			this.mcu = mcu;
		}
		
		//Method
		public void valueChanged(ListSelectionEvent e) {
			int rownum = table.getSelectedRow();
//			new MyContentUIEvent(main, num, rownum).init();
		}
	}
		
	
	
}
