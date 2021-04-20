package main_gui;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import main_vo.ChatVO;

public class ChatServer {
	//Field
	ServerSocket server;
	ArrayList<ObjectOutputStream> oos_list = new ArrayList<ObjectOutputStream>();
	Vector<String> idlist = new Vector<String>();
	
	//Constructor
	public ChatServer() {
		try {
			server = new ServerSocket(9000);
			new ClientrunThread().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method
	
	//Thread
	class ClientrunThread extends Thread{
		public void run() {
			try {
				while(true) {
					Socket s = server.accept();
					new ChatServerThread(s).start();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class ChatServerThread extends Thread {
		//Field
		Socket s;
		ObjectInputStream ois;
		ObjectOutputStream oos;
		
		public ChatServerThread(Socket s) {
			this.s=s;
			try {
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				oos_list.add(oos);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			try {
				while(true) {
					broadcast(ois);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public void broadcast(ObjectInputStream ois) {
			try {
				ChatVO vo = (ChatVO)ois.readObject();
					if(vo.getStatus()==ChatVO.CONNECT) { //Ã³À½ ¿¬°á
						vo.setMsg(vo.getUser()+"´ÔÀÌ ÀÔÀåÇÏ¼Ì½À´Ï´Ù.");
						idlist.add(vo.getUser());
						vo.setUserlist(idlist);
						
					}else if(vo.getStatus()==ChatVO.TALK) {	//Ã¤ÆÃ ´ëÈ­ º¸³¿
						String msg = vo.getMsg();
						vo.setMsg(vo.getUser() + " > " + msg);
						vo.setUserlist(idlist);
						
					}else if(vo.getStatus()==ChatVO.EXIT) {	//ÅðÀå
						vo.setMsg(vo.getUser()+"´ÔÀÌ ÅðÀåÇÏ¼Ì½À´Ï´Ù.");
						int idx = idlist.indexOf(vo.getUser());
						Iterator<ObjectOutputStream> ie = oos_list.iterator();
						while(ie.hasNext()) {
							ObjectOutputStream os = ie.next();
							if(os == oos_list.get(idx)) {
								ie.remove();
							}
						}
						
						idlist.remove(vo.getUser());
						vo.setUserlist(idlist);
					}
				Vector<String> idlist2 = (Vector<String>)idlist.clone();
				vo.setUserlist(idlist2);
				for(ObjectOutputStream os : oos_list) {
					os.writeObject(vo);
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}
}
