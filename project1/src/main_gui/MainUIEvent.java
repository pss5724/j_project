package main_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



public class MainUIEvent implements ActionListener{
	MainUI main;
	
	public MainUIEvent(MainUI main) {
		this.main = main;
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String name = e.getActionCommand().trim();
			
		if(obj == main.btnlist.get(3)) {	//검색
				new SearchUI(main);	
		}else if(obj==main.btnlist.get(1)) {	//게시판 버튼 누르기
				new CategoryUI(main);
		}else if(obj==main.btnlist.get(0)) {	//채팅
			new ChatUI(main.member);
		}else if(obj==main.logout) {	//로그아웃
			if(JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?")==JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
				main.jf.dispose();
				new LoginUI();
			}
		}else if(obj == main.btnlist.get(5)) {	//종료
			int result = JOptionPane.showConfirmDialog(null,
					Commons.getMsg("정말로 종료하시겠습니까?"));
			if(result ==0) {
				main.system.close();
				System.exit(0);
			}
		}else if(obj == main.btnlist.get(4)) {
			if(main.btnlist.get(4).getText().equals("회원관리")) {
				if(main.member.getId().equals("admin")){
					new MemberManagementUI(main);				
				}else {
					JOptionPane.showMessageDialog(null,
							Commons.getMsg("관리자만 접근 가능합니다"));
				}
			}else {
				new MyUI(main);
			}
				
		}else if(obj==main.btnlist.get(2)) {	//내글관리
			new MyPageUI(main);
		}
	}
	
}














