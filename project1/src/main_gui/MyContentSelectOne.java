package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main_vo.BoardVO;

public class MyContentSelectOne implements ActionListener{	//게시물 눌렀을때 특정게시물
	MainUI main;
	JLabel  writer_l, category_l, date_l;
	int rownum;
	JPanel mycontentselect_panel, title_panel, center_panel,
		content_panel, button_panel, top_panel;
	JButton update_btn, delete_btn;
	JTextArea content_ta;
	JTextField title_tf;
	JComboBox jcb;
	String[] categorylist = {"중식","양식","일식","분식","한식"};
	int food_num;
	String category;
	
	public MyContentSelectOne(MainUI main, int rownum) {
		this.main = main;	
		this.rownum = rownum;
		init();
	
	}
	
	public void init() {
		main.switch_panel(MainUI.MYCONTENT);
		main.mycontent_panel.setLayout(new BorderLayout());
		
		BoardVO board = main.system.select_one(rownum);
		writer_l = new JLabel(board.getId());
		//
//		category_l = new JLabel(board.getCategory());
		date_l = new JLabel(board.getDate());
		content_ta = new JTextArea(4,30);
		content_ta.setText(board.getContent());
		update_btn = new JButton("수정");
		delete_btn = new JButton("삭제");
		
		jcb = new JComboBox(categorylist);
		category = board.getCategory();
		food_num = main.system.category_re(category);
		jcb.setSelectedIndex(food_num);
		title_tf = new JTextField(board.getTitle(),20);
		
		mycontentselect_panel = new JPanel();
		top_panel = new JPanel(new GridLayout(2,1));
		title_panel = new JPanel();
		center_panel = new JPanel(new GridLayout(1,3));
		content_panel = new JPanel();
		button_panel = new JPanel(new GridLayout(1,2));
		
		title_panel.add(title_tf);
		center_panel.add(writer_l);
		center_panel.add(jcb);
		center_panel.add(date_l);
		content_panel.add(content_ta);
		button_panel.add(update_btn);
		button_panel.add(delete_btn);
		top_panel.add(title_panel);
		top_panel.add(center_panel);
		
		main.mycontent_panel.add(BorderLayout.NORTH, top_panel);
		main.mycontent_panel.add(BorderLayout.CENTER, content_panel);
		main.mycontent_panel.add(BorderLayout.SOUTH, button_panel);
		main.content_panel.add(main.mycontent_panel);
		
		main.jf.setVisible(true);
		update_btn.addActionListener(this);
		delete_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == update_btn) {
			update_result_proc();
			
		}else if(obj == delete_btn) {
			delete_proc();
			
			new MyContentUI(main);
		}
	}
	
	public void update_result_proc() {
		if(title_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, 
					Commons.getMsg("수정할 제목을 입력해주세요"));
			title_tf.requestFocus();
		}else if(content_ta.getText().equals("")) {
			JOptionPane.showMessageDialog(null, 
					Commons.getMsg("수정할 내용을 입력해주세요"));
			content_ta.requestFocus();
		}else {
			String update_ctg = (String)jcb.getSelectedItem();
			String update_title = title_tf.getText();
			String update_content = content_ta.getText();
			main.system.mycontent_update(update_title, update_ctg, update_content, rownum);
			new MyContentUI(main);

		}
	}
	
	public void delete_proc() {
		int result = JOptionPane.showConfirmDialog(null,
				Commons.getMsg("정말로 삭제하시겠습니까?"));
		if(result == 0) {
			main.system.mycontent_delete(rownum);
			JOptionPane.showMessageDialog(null, Commons.getMsg("삭제 완료"));
		}
	}
	
}
