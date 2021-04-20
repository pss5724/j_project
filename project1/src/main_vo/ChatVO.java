package main_vo;

import java.io.Serializable;
import java.util.Vector;

public class ChatVO implements Serializable{
	public static final int CONNECT = 0;
	public static final int TALK = 1;
	public static final int EXIT = -1;
	
	String msg, user;
	int status;
	Vector<String> userlist;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Vector<String> getUserlist() {
		return userlist;
	}
	public void setUserlist(Vector<String> userlist) {
		this.userlist = userlist;
	}
	
	
	
}
