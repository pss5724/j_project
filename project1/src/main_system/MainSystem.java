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
		public boolean writeArticle(MemberVO member, String ctg, String title, String contents) {
			boolean check = false;
			if(dao.writeArticle(member, ctg,title,contents)!=0) {
				check = true;
			}
			return check;
		}
		
		/** �˻� **/
		public ArrayList<BoardVO> search(String title) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.getSelectResult(title);
			return boardlist;
		}
		
		/** ���� �˻� **/
		public ArrayList<BoardVO> mycontent_search(String id) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.getmySelectResult();
			return boardlist;
		}
		
		/** Ư�� �Խù� Ŭ���� ��ȸ -������**/
		public BoardVO select_one(int rownum) {
			BoardVO vo = new BoardVO();
			vo = dao.SelectOneResult(rownum);
			return vo;
		}
}
