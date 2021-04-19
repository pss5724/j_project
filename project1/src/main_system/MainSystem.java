package main_system;

import java.util.ArrayList;

import main_dao.BoardDAO;
import main_dao.MemberDAO;
import main_vo.BoardVO;
import main_vo.MemberVO;

public class MainSystem {
	MemberDAO mdao = new MemberDAO();	
	BoardDAO dao = new BoardDAO();
	
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
			return mdao.getJoinResult(member);
		}
		
		/** �ߺ�üũ **/
		public boolean check(String str) {
			return mdao.getCheckResult(str);
		}
	
		/** ���� **/
		public void close() {
			mdao.close();
			System.out.println("-------- �����ͺ��̽� ���� ���� ---------");
		}
	
		/** �� ī�װ��� �Խ��� ��ȸ **/
		public ArrayList<BoardVO> select(int food_num) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.SelectResult(food_num);
			return boardlist;
		}
		
		/** Ư�� �Խù� Ŭ���� ��ȸ **/
		public BoardVO select_one(int food_num, int rownum) {
			BoardVO vo = new BoardVO();
			vo = dao.SelectOneResult(food_num, rownum);
			return vo;
		}
		
		/** �Խù� �ۼ� **/
		public void writeArticle(MemberVO member, String ctg, String title, String contents) {
			dao.writeArticle(member, ctg,title,contents);
		}
		
}
