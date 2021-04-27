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

public class MyCommentUI {
   String[] colnames = {"ID","���","�����","���� ��ȸ"};
   DefaultTableModel model= new DefaultTableModel(colnames,0);
   Object[] row;
   JTable table = new JTable(model);
   ArrayList<CommentVO> commentlist;
   JTextField comment_jtf;
   JButton comment_insert_btn;
   BoardVO s_vo;
   JLabel comment_status_l, comment_write_l;
   JPanel mycomment_panel, comment_jtf_panel;
   MainUI main;
   
   public MyCommentUI(MainUI main) {
      this.main = main;
      init();
   }
   
   public void init() {
      //��� �߰�
      mycomment_panel = new JPanel(new BorderLayout());   
      comment_status_l = new JLabel("���");
 
      //��� �г�
      
      commentlist = main.system.select_reply(main.member); //�ý��� ���� ��� �ҷ�����.
      if(commentlist.size() ==0) {   //��� ���� ��
         JLabel nocomment = new JLabel("�ۼ��� ����� �����ϴ�");
         mycomment_panel.add(BorderLayout.CENTER,nocomment);
      }else {   //��� ���� ��
         model.setNumRows(0);
         for(int i=0;i<commentlist.size();i++) {
            row = new Object[4];
            row[0] = commentlist.get(i).getId();
            row[1] = commentlist.get(i).getComment();
            row[2] = commentlist.get(i).getDate();
            row[3] = " ";
                   
            model.addRow(row);
         }
         
         model.fireTableDataChanged();
         
         model.setColumnIdentifiers(colnames);
         table.setModel(model);
         table.setRowHeight(25);
         table.setAutoCreateRowSorter(false);
         
         
         table.getColumnModel().getColumn(3).setCellRenderer(new TableUpdateCell2("���� ��ȸ", this));
           table.getColumnModel().getColumn(3).setCellEditor(new TableUpdateCell2("���� ��ȸ", this));
         
           table.getColumnModel().getColumn(0).setResizable(false);
         table.getColumnModel().getColumn(0).setPreferredWidth(10);
         table.getColumnModel().getColumn(1).setResizable(false);
         table.getColumnModel().getColumn(1).setPreferredWidth(100);
         table.getColumnModel().getColumn(2).setResizable(false);
         table.getColumnModel().getColumn(2).setPreferredWidth(10);
         table.getColumnModel().getColumn(3).setResizable(false);
         table.getColumnModel().getColumn(3).setPreferredWidth(10);
         
         JScrollPane pane = new JScrollPane(table);
         
         //���̺� ������ ����
         Dimension table_size = new Dimension(140,25+25*commentlist.size());
         pane.setPreferredSize(table_size);
         
         
         mycomment_panel.add(pane);
         main.content_panel.removeAll();
         main.content_panel.add(mycomment_panel);
         main.jf.setVisible(true);
         

      }
         
      
      
      
      
   }
}
 
class TableUpdateCell2 extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
   
   JButton jb; //����, ���� ��ư
   MyCommentUI mcu;
   public TableUpdateCell2(String name, MyCommentUI mcu) {
      jb = new JButton(name);
      this.mcu = mcu;
      
      jb.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String name = e.getActionCommand();
            
            if(name.equals("���� ��ȸ")) {
            	new ContentUI(mcu.main, mcu.commentlist.get(mcu.table.getSelectedRow()).getContentnum());
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
