package main_gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import main_vo.MemberVO;

public class WriteUpdateUI {

	
	//Field
		MainUI main;
		JLabel category_l, title_l, content_l;
		JPanel label_panel_1, label_panel_2, tf_panel_1, tf_panel_2, ta_panel, btn_panel, contents_panel;
		JButton write_btn,cancel_btn;
		JTextField title_tf;
		JTextArea contents_ta;
		JComboBox jcb;
		String[] category_list = {"중식","양식","일식","분식","한식"};
		int food_num, status;
		MemberVO member = new MemberVO();
		BoardVO update_vo = new BoardVO();
		public static final int WRITE_INSERT = 1;
		public static final int WRITE_UPDATE = 2;
		
		//Constructor
		public WriteUpdateUI(MainUI main, int food_num, int status) {
			this.main = main;
			this.food_num = food_num;
			this.member = main.member;
			this.status = status;
			
			init();
		}
		public WriteUpdateUI(MainUI main, BoardVO vo, int status) {
			this.main = main;
			this.member = main.member;
			this.update_vo = vo;
			this.status = status;
			init();
		}
		
		//Method
		public void init() {
			main.switch_panel(MainUI.BOARD);
			
			main.board_panel.setLayout(new BorderLayout());
			
			
			label_panel_1 = new JPanel(new GridLayout(4,1));
			contents_panel = new JPanel(new GridLayout(2,1));
			label_panel_2 = new JPanel(new GridLayout(2,1));
			
			ta_panel = new JPanel(new BorderLayout());
			tf_panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			tf_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			btn_panel = new JPanel();
			
			category_l= new JLabel("카테고리");
			title_l = new JLabel("제목");
			content_l = new JLabel("내용");
			
			label_panel_2.add(category_l);
			label_panel_2.add(title_l);
			label_panel_1.add(label_panel_2);
			label_panel_1.add(content_l);
			
//			select_tf = new JTextField(20);
			jcb = new JComboBox(category_list);
			jcb.setSelectedIndex(food_num-1);
			title_tf = new JTextField(25);
			contents_ta = new JTextArea(9,25);
			contents_ta.setLineWrap(true);
			
			if(status==WriteUpdateUI.WRITE_UPDATE) {
				jcb.setSelectedItem(update_vo.getCategory());
				title_tf.setText(update_vo.getTitle());
				contents_ta.setText(update_vo.getContent());
			}
			
			
			write_btn = new JButton("글쓰기");
			cancel_btn = new JButton("취소");
			
			btn_panel.add(write_btn);
			btn_panel.add(cancel_btn);
			
			tf_panel_1.add(jcb);
			tf_panel_2.add(title_tf);
			
			
			contents_panel.add(tf_panel_1);
			contents_panel.add(tf_panel_2);
			
			ta_panel.add(BorderLayout.CENTER, contents_ta);
			ta_panel.add(BorderLayout.NORTH, contents_panel);
			

			
			main.board_panel.add(BorderLayout.WEST,label_panel_1);
			main.board_panel.add(BorderLayout.CENTER,ta_panel);
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
			if(obj==write_btn) {	//글쓰기 버튼 눌렀을때
				String write_ctg = (String)jcb.getSelectedItem();
				String write_title = title_tf.getText();
				String write_content = contents_ta.getText();
				
				if(write_title.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "제목을 입력해주세요.");
				}else if(write_content.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "내용을 입력해주세요.");
				}else {
					if(status==WriteUpdateUI.WRITE_INSERT) {	//글 등록일 경우
						if(main.system.writeArticle(member,write_ctg,write_title,write_content)){
							JOptionPane.showMessageDialog(null, "게시글이 등록되었습니다.");
							new BoardUI(main,food_num);
						}
					}else if(status==WriteUpdateUI.WRITE_UPDATE) {	//글 수정일 경우
						if(main.system.updateArticle(write_ctg,write_title,write_content,update_vo)) {
							JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
							update_vo.setCategory(write_ctg);
							update_vo.setTitle(write_title);
							update_vo.setContent(write_content);
							new ContentUI(main, update_vo);
						}else {
							JOptionPane.showMessageDialog(null, "수정이 실패하였습니다.");
						}
					}
				}
			}else if(obj==cancel_btn) {		//취소 버튼 눌렀을때
				new CategoryUI(main);
			}
		}
		
		
		
	}
}
