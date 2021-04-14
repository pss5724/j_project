package main_gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main_dao.MemberDAO;
import main_system.MainSystem;
import main_vo.MemberVO;

public class MainUI {
	//Field
	MemberVO member;
	MainSystem system;
	MemberDAO mdao = new MemberDAO();
	JFrame jf;
	JPanel info_panel,read_panel,write_panel,category_panel,content_panel,title_panel,btn_panel,top_panel,menu_panel, chat_panel, board_panel, mycontent_panel,
	search_panel, exit_panel, member_panel;
	JLabel title, info;
	ArrayList<JButton> btnlist = new ArrayList<JButton>();
	
	
	
	public static final int BOARD = 1;	//게시판
	public static final int READ = 2;	//게시글
	public static final int WRITE = 3;	//글쓰기
	public static final int MYCONTENT = 4;	//내글
	public static final int SEARCH = 5;	//검색
	public static final int MEMBER = 6;	//회원관리
	
	
	//Constructor
	public MainUI(int memberNum, MainSystem system) {
		this.member = mdao.getMemberInfo(memberNum);
		this.system = system;
		init();
	}
	
	//Method
	public void init() {
		jf = new JFrame("호로록");
		title = new JLabel("호로로록");
		info = new JLabel(member.getId()+"님 환영합니다. 지역은 "+member.getLocation());
		
		top_panel = new JPanel(new GridLayout(3,1));
		content_panel = new JPanel();
		category_panel = new JPanel();
		title_panel = new JPanel();
		info_panel = new JPanel();
		btn_panel = new JPanel();
		menu_panel = new JPanel();
		chat_panel = new JPanel();
		board_panel = new JPanel();
		mycontent_panel = new JPanel();
		search_panel = new JPanel();
		exit_panel = new JPanel();
		member_panel = new JPanel();
		search_panel = new JPanel();
		read_panel = new JPanel();
		write_panel = new JPanel();
		String[] menulist = {"채팅","게시판","내글","검색","종료","회원관리"};
		
		for(String name : menulist) {
			JButton btn = new JButton(name);
			
			btnlist.add(btn);
		}
		
		for(JButton btn : btnlist) {
			btn_panel.add(btn);
			btn.addActionListener(new MainUIEvent(this));
		}
		
		
		menu_panel.add(btn_panel);
		title_panel.add(title);
		info_panel.add(info);
		
		top_panel.add(BorderLayout.NORTH, info_panel);
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
		search_panel.setVisible(false);
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
			System.out.println("search 전");
			search_panel.removeAll();
			System.out.println("search 후");
			search_panel.setVisible(true);
			break;
		case MEMBER :
			member_panel.removeAll();
			member_panel.setVisible(true);
			break;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	

}
