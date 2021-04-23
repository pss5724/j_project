package main_gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import main_vo.BoardVO;
import main_vo.CommentVO;

public class CommentUI {
	String[] colnames = {"ID","���","�����","����","����"};
	DefaultTableModel model= new DefaultTableModel(colnames,0);
	Object[] row;
	JTable table = new JTable(model);
	ArrayList<CommentVO> commentlist;
	JTextField comment_jtf;
	JButton comment_insert_btn;
	BoardVO s_vo;
	JLabel comment_status_l, comment_write_l;
	JPanel comment_panel, comment_jtf_panel;
	ContentUI contentUI;
	
	public CommentUI(ContentUI contentUI) {
		this.contentUI = contentUI;
		init();
	}
	
	public JPanel init() {
		//��� �߰�
		comment_panel = new JPanel(new BorderLayout());	
		comment_status_l = new JLabel("���");

		//��� �г�
		
		commentlist = contentUI.main.system.select_reply(contentUI.contents_vo); //�ý��� ���� ��� �ҷ�����.
		if(commentlist.size() ==0) {	//��� ���� ��
			JLabel nocomment = new JLabel("���� ����� �����ϴ�. ����� �޾��ּ���!");
			comment_panel.add(BorderLayout.CENTER,nocomment);
		}else {	//��� ���� ��
			model.setNumRows(0);
			for(int i=0;i<commentlist.size();i++) {
				row = new Object[5];
				row[0] = commentlist.get(i).getId();
				row[1] = commentlist.get(i).getComment();
				row[2] = commentlist.get(i).getDate();
				row[3] = " ";
				row[4] = " ";
						
				model.addRow(row);
			}
			
			model.fireTableDataChanged();
			
			model.setColumnIdentifiers(colnames);
			table.setModel(model);
			table.setRowHeight(25);
			table.setAutoCreateRowSorter(false);
			
			ImageIcon delete_icon = new ImageIcon("images/delete.png");
			Image delete_image = delete_icon.getImage();
			Image changeDelete = delete_image.getScaledInstance(20, 15, Image.SCALE_DEFAULT);
			ImageIcon DeleteIcon = new ImageIcon(changeDelete);
	
			ImageIcon edit_icon = new ImageIcon("images/edit-button.png");
			Image edit_image = edit_icon.getImage();
			Image changeEdit = edit_image.getScaledInstance(20, 15, Image.SCALE_DEFAULT);
			ImageIcon EditIcon = new ImageIcon(changeEdit);
			
			table.getColumnModel().getColumn(3).setCellRenderer(new TableUpdateCell("����", EditIcon, this, contentUI));
	        table.getColumnModel().getColumn(3).setCellEditor(new TableUpdateCell("����", EditIcon, this, contentUI));
	        table.getColumnModel().getColumn(4).setCellRenderer(new TableUpdateCell("����", DeleteIcon, this, contentUI));
	        table.getColumnModel().getColumn(4).setCellEditor(new TableUpdateCell("����", DeleteIcon, this, contentUI));
			
	        table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(2).setPreferredWidth(10);
			table.getColumnModel().getColumn(3).setResizable(false);
			table.getColumnModel().getColumn(3).setPreferredWidth(10);
			table.getColumnModel().getColumn(4).setResizable(false);
			table.getColumnModel().getColumn(4).setPreferredWidth(10);
			
			JScrollPane pane = new JScrollPane(table);
			
			//���̺� ������ ����
			Dimension table_size = new Dimension(140,25+25*commentlist.size());
			pane.setPreferredSize(table_size);
	
			comment_panel.add(BorderLayout.CENTER,pane);

		}
		
		//��� ���� �κ�
		comment_write_l = new JLabel("��� �ۼ�");
		comment_jtf = new JTextField(15);
		comment_insert_btn = new JButton("���");
		comment_jtf_panel = new JPanel();
		comment_jtf_panel.add(comment_write_l);
		comment_jtf_panel.add(comment_jtf);
		comment_jtf_panel.add(comment_insert_btn);
		
		
		
		//����гο� �ֱ�
		comment_panel.add(BorderLayout.NORTH,comment_status_l);
		comment_panel.add(BorderLayout.SOUTH,comment_jtf_panel);
		
		comment_insert_btn.addActionListener(new ActionListener() {	//��� ��� ��ư ������ ��
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				
				if(obj==comment_insert_btn) {
					if(JOptionPane.showConfirmDialog(null, "����� ����Ͻðڽ��ϱ�?")==JOptionPane.OK_OPTION) {
						if(comment_jtf.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "��� ������ �Է����ּ���.");
						}else {
							int result = contentUI.main.system.insert_reply(contentUI.contents_vo,
									contentUI.main.member, comment_jtf.getText().trim());
							if(result!=0) {
								JOptionPane.showMessageDialog(null, "����� �Ϸ�Ǿ����ϴ�.");
								new ContentUI(contentUI.main, contentUI.contents_vo);
							}
						}
					}
				}
			}
		});
			
		
		
		return comment_panel;
		
	}
}

class TableUpdateCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
	
	JButton jb; //����, ���� ��ư
	ContentUI contentUI;
	
	public TableUpdateCell(String name, ImageIcon icon, CommentUI commentui, ContentUI contentUI) {
		this.contentUI = contentUI;
		jb = new JButton(icon);
		
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = e.getActionCommand();
				int result = 0;
				
				if(name.equals("����")) {
//					new BoardUpdate(commentui,option);
				}else if(name.equals("����")) {
					int confirm = JOptionPane.showConfirmDialog(null, Commons.getMsg("������ �����Ͻðڽ��ϱ�?"));
					if(confirm==JOptionPane.OK_OPTION) {
						//result = ���� ������ �Ѵ�..
					}else {
						
					}
					if(result!=0) commentui.init(); 
				}
			}
		});
	}
	
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return jb;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return jb;
	}
	
}//TableUpdateCell class