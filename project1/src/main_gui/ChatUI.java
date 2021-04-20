package main_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main_vo.MemberVO;

public class ChatUI {
	JFrame jf;
	JPanel up_panel, center_panel, text_panel, title_panel,
	info_panel, user_panel;
	JLabel title, conn_info, user_text;
	JTextField send_jtf;
	JList userlist;
	JTextArea contents_ta = new JTextArea(50,30);
	String server = "127.0.0.1";
	String id = "";
	ChatClient chatclient;
	int usercount;
	
	public ChatUI(MemberVO vo) {
		id = vo.getId();
		init();
	}
	
	public void init() {
		jf = new JFrame("채팅");
		
		up_panel = new JPanel(new GridLayout(2,1));
		center_panel = new JPanel();
		text_panel = new JPanel(new BorderLayout());
		title_panel = new JPanel();
		user_panel = new JPanel(new BorderLayout());
		info_panel = new JPanel();
		title = new JLabel("호로록 채팅 서버");
		conn_info = new JLabel("["+server+"]에 접속. UserID : "+id);
		info_panel.add(conn_info);
		title_panel.add(title);
		title_panel.setBackground(Color.lightGray);
		
		
		up_panel.add(info_panel);
		up_panel.add(title_panel);
		
		userlist = new JList();
		user_text = new JLabel("접속 인원 : " + usercount + "명");
		user_panel.add(BorderLayout.SOUTH, user_text);
		user_panel.add(BorderLayout.CENTER, userlist);
		
		
		text_panel.add(BorderLayout.CENTER, contents_ta);
		text_panel.add(new JScrollPane(contents_ta));
		
		center_panel.setLayout(new BorderLayout());
		center_panel.add(BorderLayout.WEST,user_panel);
		center_panel.add(BorderLayout.CENTER,text_panel);
		
		send_jtf = new JTextField();
		
		jf.getContentPane().add("North",up_panel);
		jf.getContentPane().add("South",send_jtf);
		jf.getContentPane().add("Center",center_panel);
		
		
		jf.setSize(500,500);
		jf.setVisible(true);
		
		//클라이언트 접속
		chatclient = new ChatClient(this);
		
		send_jtf.addActionListener(chatclient);
		
		//windowlistener
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				chatclient.exit();
			}
		});
		
		
	} //init()
	
	
}// class
