package main_system;

import main_dao.MemberDAO;
import main_vo.MemberVO;

public class MainSystem {
	MemberDAO mdao = new MemberDAO();	
	
	
	//login 결과
		public static boolean LOGIN_RESULT = false;
		
		//Constructor
		public MainSystem() {		
			
		}
		
		
		/** 로그인 **/
		public int loginCheck(String id, String pass) {
			return mdao.getLoginResult(id, pass);
		}
		
		/** 회원가입 **/
		public boolean join(MemberVO member) {
			return mdao.getLoginResult(member);
		}
	
		/** 종료 **/
		public void close() {
			mdao.close();
			System.out.println("-------- 데이터베이스 연결 종료 ---------");
		}
	
	
}
