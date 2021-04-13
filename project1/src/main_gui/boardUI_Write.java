package main_gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class boardUI_Write {

	
	//Field
		MainUI main;
		JLabel category_l, title_l, content_l;
		JPanel label_panel, tf_panel_1, tf_panel_2, ta_panel, btn_panel, contents_panel;
		JButton write_btn,cancel_btn;
		JTextField title_tf; //, select_tf;
		TextArea contents_ta;
		JComboBox jcb;
		String[] category_list = {"중식","양식","일식","분식","한식"};
		
		//Constructor
		public boardUI_Write(MainUI main) {
			this.main = main;
			init();
		}
		//
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
			
			category_l= new JLabel("카테고리");
			title_l = new JLabel("제목");
			content_l = new JLabel("내용");
			
			label_panel.add(category_l);
			label_panel.add(title_l);
			label_panel.add(content_l);
			
//			select_tf = new JTextField(20);
			jcb = new JComboBox(category_list);
			title_tf = new JTextField(20);
			contents_ta = new TextArea(3,30);

			write_btn = new JButton("글쓰기");
			cancel_btn = new JButton("취소");
			
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
			
//			write_btn.addActionListener(new MainUIEvent(this));
//			cancel_btn.addActionListener(new MainUIEvent(this));
		}
		
}
