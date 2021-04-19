package main_system;

import java.util.ArrayList;

import main_dao.BoardDAO;
import main_dao.MemberDAO;
import main_vo.BoardVO;
import main_vo.MemberVO;

public class MainSystem {
	MemberDAO mdao = new MemberDAO();	
	BoardDAO dao = new BoardDAO();
	
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
			return mdao.getJoinResult(member);
		}
		
		/** 중복체크 **/
		public boolean check(String str) {
			return mdao.getCheckResult(str);
		}
	
		/** 종료 **/
		public void close() {
			mdao.close();
			System.out.println("-------- 데이터베이스 연결 종료 ---------");
		}
	
		/** 각 카테고리별 게시판 조회 **/
		public ArrayList<BoardVO> select(int food_num) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.SelectResult(food_num);
			return boardlist;
		}
		
		/** 특정 게시물 클릭시 조회 **/
		public BoardVO select_one(int food_num, int rownum) {
			BoardVO vo = new BoardVO();
			vo = dao.SelectOneResult(food_num, rownum);
			return vo;
		}
		
		/** 게시물 작성 **/
		public void writeArticle(MemberVO member, String ctg, String title, String contents) {
			dao.writeArticle(member, ctg,title,contents);
		}
		
}
