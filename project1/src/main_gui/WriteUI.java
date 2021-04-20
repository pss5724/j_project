package main_gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main_vo.MemberVO;

public class WriteUI {

	
	//Field
		MainUI main;
		JLabel category_l, title_l, content_l;
		JPanel label_panel, tf_panel_1, tf_panel_2, ta_panel, btn_panel, contents_panel;
		JButton write_btn,cancel_btn;
		JTextField title_tf; //, select_tf;
		TextArea contents_ta;
		JComboBox jcb;
		String[] category_list = {"�߽�","���","�Ͻ�","�н�","�ѽ�"};
		int food_num;
		MemberVO member = new MemberVO();
		
		//Constructor
		public WriteUI(MainUI main, int food_num) {
			this.main = main;
			this.food_num = food_num;
			this.member = main.member;
			
			init();
		}
		
		//Method
		public void init() {
			main.switch_panel(MainUI.BOARD);
			
			main.board_panel.setLayout(new BorderLayout());
			
			
			label_panel = new JPanel(new GridLayout(4,1,0,50));
			contents_panel = new JPanel(new GridLayout(3,1));
			
			ta_panel = new JPanel();
			tf_panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			tf_panel_2 = new JPanel();
			btn_panel = new JPanel();
			
			category_l= new JLabel("ī�װ�");
			title_l = new JLabel("����");
			content_l = new JLabel("����");
			
			label_panel.add(category_l);
			label_panel.add(title_l);
			label_panel.add(content_l);
			
//			select_tf = new JTextField(20);
			jcb = new JComboBox(category_list);
			jcb.setSelectedIndex(food_num-1);
			title_tf = new JTextField(20);
			contents_ta = new TextArea(3,30);

			write_btn = new JButton("�۾���");
			cancel_btn = new JButton("���");
			
			btn_panel.add(write_btn);
			btn_panel.add(cancel_btn);
			
			tf_panel_1.add(jcb);
			tf_panel_2.add(title_tf);
			ta_panel.add(contents_ta);
			
			
			contents_panel.add(tf_panel_1);
			contents_panel.add(tf_panel_2);
			contents_panel.add(ta_panel);
			

			
			main.board_panel.add(BorderLayout.WEST,label_panel);
			main.board_panel.add(BorderLayout.CENTER,contents_panel);
			main.board_panel.add(BorderLayout.SOUTH,btn_panel);
			main.content_panel.add(main.board_panel);
			
			main.jf.setVisible(true);
			
			write_btn.addActionListener(new Write_Action());
			cancel_btn.addActionListener(new Write_Action());
			
		}
		
		
		

	class Write_Action implements ActionListener {

		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj==write_btn) {	//�۾��� ��ư ��������
				String write_ctg = (String)jcb.getSelectedItem();
				String write_title = title_tf.getText();
				String write_content = contents_ta.getText();
				
				if(write_title.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���.");
				}else if(write_content.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���.");
				}else {
					if(main.system.writeArticle(member,write_ctg,write_title,write_content)){
						JOptionPane.showMessageDialog(null, "�Խñ��� ��ϵǾ����ϴ�.");
						new BoardUI(main,food_num);
					}
				}
			}else if(obj==cancel_btn) {		//��� ��ư ��������
				new CategoryUI(main);
			}
		}
		
		
		
	}
}
