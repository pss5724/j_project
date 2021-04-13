package main_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main_system.HororogMgmSystem;

public class MainUIEvent implements ActionListener{
	MainUI main;
	
	public MainUIEvent(MainUI main) {
		this.main = main;
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String name = e.getActionCommand().trim();
//		
//		if(obj == main.btnlist.get(0)) {	//등록
//			if(HororogMgmSystem.LOGIN_RESULT){
//				new InsertUI(main);				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("로그인이 필요한 기능입니다"));
//			}
//		}else if(obj == main.menulist.get(1)) {	//조회
//			if(HororogMgmSystem.LOGIN_RESULT){
//				new SelectUI(main);				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("로그인이 필요한 기능입니다"));
//			}
//		}else 
			if(obj == main.btnlist.get(3)) {	//검색
				new SearchUI(main);	
			}
//			if(true){
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("로그인이 필요한 기능입니다"));
//			}
//		}else if(obj == main.menulist.get(3)) {  //수정
//			if(HororogMgmSystem.LOGIN_RESULT){
//				new UpdateUI(main);				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("로그인이 필요한 기능입니다"));
//			}
//		}else if(obj == main.menulist.get(4)) {
//			if(HororogMgmSystem.LOGIN_RESULT){
//				new DeleteUI(main);				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("로그인이 필요한 기능입니다"));
//			}
//		}else if(obj == main.menulist.get(5)) {	//종료
//			int result = JOptionPane.showConfirmDialog(null,
//					Commons.getMsg("정말로 종료하시겠습니까?"));
//			if(result ==0) {
//				main.system.close();
//				System.exit(0);
//			}
//		}else if(name.equals("로그인")) {
//			new LoginUI(main);
//		}else if(name.equals("로그아웃")) {
//			int result = JOptionPane.showConfirmDialog(null,
//					Commons.getMsg("정말로 로그아웃 하시겠습니까?"));
//			if(result==0) {
//				new LoginUI(main);
//				main.btn_login.setText("     로그인      ");
//				HororogMgmSystem.LOGIN_RESULT = false;
//			}
//		}else if(obj == main.btn_join) {
//			new JoinUI(main);
//		}
//		
	}
	
}














