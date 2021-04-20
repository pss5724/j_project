package main_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import main_vo.ChatVO;

public class ChatClient implements ActionListener{
	//Field
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket s;
	String ip = "127.0.0.1";
	int port = 9000; 
	ChatUI ui;
	
	//Constructor
	public ChatClient(ChatUI ui) {
		try {
			this.ui = ui;
			s = new Socket(ip,port);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			ChatVO vo = new ChatVO();
			vo.setStatus(ChatVO.CONNECT);
			vo.setUser(ui.id);
			oos.writeObject(vo);
			new ChatClientThread().start();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	//Method
	public void exit() {
		ChatVO vo = new ChatVO();
		vo.setStatus(ChatVO.EXIT);
		vo.setUser(ui.id);
		try {
			oos.writeObject(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Thread
	class ChatClientThread extends Thread {	//수신
		
		public void run() {
			try {
			while(true) {
					ChatVO vo = (ChatVO)ois.readObject();
					ui.userlist.setListData(vo.getUserlist());
					ui.contents_ta.append(vo.getMsg()+"\n");
					ui.user_text.setText("접속 인원 : " + vo.getUserlist().size() + "명");
					}
				} catch (Exception e) {
				}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==ui.send_jtf) {
			//채팅전송
			try {
				ChatVO vo = new ChatVO();
				vo.setMsg(ui.send_jtf.getText());
				vo.setStatus(ChatVO.TALK);
				vo.setUser(ui.id);
				
				oos.writeObject(vo);
				ui.send_jtf.setText("");
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			}
		}

	
}//class
