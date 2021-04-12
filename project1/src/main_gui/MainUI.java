package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainUI {
	//Field
	JPanel read_panel,write_panel,category_panel,content_panel,title_panel,btn_panel,top_panel,menu_panel, chat_panel, board_panel, mycontent_panel,
	search_panel, exit_panel, member_panel;
	JFrame jf;
	JLabel title;
	ArrayList<JButton> btnlist = new ArrayList<JButton>();
	
	
	
	public static final int BOARD = 1;
	public static final int READ = 2;
	public static final int WRITE = 3;
	public static final int MYCONTENT = 4;
	public static final int SEARCH = 5;
	public static final int MEMBER = 6;
	
	
	//Constructor
	public MainUI() {
		init();
	}
	
	//Method
	public void init() {
		jf = new JFrame("호로록");
		title = new JLabel("호로로록");
		
		top_panel = new JPanel(new GridLayout(2,1));
		content_panel = new JPanel();
		category_panel = new JPanel();
		title_panel = new JPanel();
		btn_panel = new JPanel();
		menu_panel = new JPanel();
		chat_panel = new JPanel();
		board_panel = new JPanel();
		mycontent_panel = new JPanel();
		search_panel = new JPanel();
		exit_panel = new JPanel();
		member_panel = new JPanel();
		String[] menulist = {"채팅","게시판","내글","검색","종료","회원관리"};
		
		for(String name : menulist) {
			JButton btn = new JButton(name);
			
			btnlist.add(btn);
		}
		
		for(JButton btn : btnlist) {
			btn_panel.add(btn);
			
		}
		
		menu_panel.add(btn_panel);
		title_panel.add(title);
		
		top_panel.add(BorderLayout.NORTH, title_panel);
		top_panel.add(BorderLayout.NORTH, menu_panel);
		
		content_panel.add(category_panel);
		
		jf.add(BorderLayout.NORTH, top_panel);
		jf.add(BorderLayout.CENTER, content_panel);
		
		jf.setSize(500,500);
		jf.setVisible(true);
		
		
		
	}
	
	public void switch_panel(int num) {
		content_panel.removeAll();
		board_panel.setVisible(false);
		read_panel.setVisible(false);
		write_panel.setVisible(false);
		mycontent_panel.setVisible(false);
		member_panel.setVisible(false);
		
		
		
		
		switch(num){
		case BOARD :
				board_panel.removeAll();
				board_panel.setVisible(true);
			break;
		case READ :
			read_panel.removeAll();
			read_panel.setVisible(true);
			break;
		case WRITE :
			write_panel.removeAll();
			write_panel.setVisible(true);
			break;
		case MYCONTENT :
			mycontent_panel.removeAll();
			mycontent_panel.setVisible(true);
			break;
		case SEARCH :
			search_panel.removeAll();
			search_panel.setVisible(true);
			break;
		case MEMBER :
			member_panel.removeAll();
			member_panel.setVisible(true);
			break;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	

}
