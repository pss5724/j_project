package main_system;

import main_dao.MemberDAO;
import main_vo.MemberVO;

public class MainSystem {
	MemberDAO mdao = new MemberDAO();	
	
	
	//login ���
		public static boolean LOGIN_RESULT = false;
		
		//Constructor
		public MainSystem() {		
			
		}
		
		
		/** �α��� **/
		public int loginCheck(String id, String pass) {
			return mdao.getLoginResult(id, pass);
		}
		
		/** ȸ������ **/
		public boolean join(MemberVO member) {
			return mdao.getLoginResult(member);
		}
	
		/** ���� **/
		public void close() {
			mdao.close();
			System.out.println("-------- �����ͺ��̽� ���� ���� ---------");
		}
	
	
}
