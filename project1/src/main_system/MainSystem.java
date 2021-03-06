package main_system;

import java.util.ArrayList;

import main_dao.BoardDAO;
import main_dao.CommentDAO;
import main_dao.MemberDAO;
import main_vo.BoardVO;
import main_vo.CommentVO;
import main_vo.MemberVO;

public class MainSystem {
	MemberDAO mdao = new MemberDAO();	
	BoardDAO dao = new BoardDAO();
	CommentDAO cdao = new CommentDAO();
	
	//login 결과
		public static boolean LOGIN_RESULT = false;
		
		//Constructor
		public MainSystem() {		
			
		}
		
		//성수 작성 부분
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
	
		/** 중복체크 **/
		public ArrayList<MemberVO> getMemberList() {
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			list = mdao.getMemberResult();
			return list;
		}
		
		/** 중복체크 **/
		public boolean memberUpdate(MemberVO member, String id) {
			
			return mdao.getUpdateResult(member, id);
		}
		
		/** 중복체크 **/
		public boolean memberDelete(String id) {
			
			return mdao.getDeleteResult(id);
		}
		
		/** 회원 조회 **/
		public MemberVO memberSearch(String id) {
			
			return mdao.getSearchResult(id);
		}
		
		/** 회원 조회 **/
		public MemberVO getMemberInfo(String id) {
			
			return mdao.getMemberInfo(id);
		}
		
		/** 종료 **/
		public void close() {
			mdao.close();
			System.out.println("-------- 데이터베이스 연결 종료 ---------");
		}
	
		//세희 작성 부분
		/** 각 카테고리별 게시판 조회 **/
		public ArrayList<BoardVO> select_ctg(MemberVO vo, int food_num) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.SelectResult(vo, food_num);
			return boardlist;
		}
		
		/** 특정 게시물 클릭시 댓글 조회 **/
		public ArrayList<CommentVO> select_reply(BoardVO contents_vo){
			ArrayList<CommentVO> commentlist = new ArrayList<CommentVO>();
			commentlist = cdao.selectReply(contents_vo);
			return commentlist;
		}
		
		/** 특정 게시물 클릭시 댓글 조회 **/
		public ArrayList<CommentVO> select_reply(MemberVO member){
			ArrayList<CommentVO> commentlist = new ArrayList<CommentVO>();
			commentlist = cdao.selectReply(member);
			return commentlist;
		}
		
		/** 댓글 작성 **/
		public int insert_reply(BoardVO b_vo, MemberVO m_vo, String comment) {
			int result = cdao.insertReply(b_vo, m_vo, comment);
			return result;
		}
		
		/** 댓글 수정 **/
		public int update_reply(String value, CommentVO c_vo) {
			int result = cdao.updateReply(value,c_vo);
			return result;
		}
		
		/** 댓글 삭제 **/
		public int delete_reply(CommentVO c_vo) {
			int result = 0;
			result = cdao.deleteReply(c_vo);
			return result;
		}
		
		/** 게시물 작성 **/
		public boolean writeArticle(MemberVO member, String ctg, String title, String contents) {
			boolean check = false;
			if(dao.writeArticle(member, ctg,title,contents)!=0) {
				check = true;
			}
			return check;
		}
		
		/** 게시물 수정 **/
		public boolean updateArticle(String ctg, String title, String contents, BoardVO update_vo) {
			boolean check = false;
			if(dao.updateArticle(ctg, title, contents, update_vo)!=0) {
				check = true;
			}
			return check;
		}
		
		/** 게시물 삭제 **/
		public boolean deleteArticle(BoardVO contents_vo) {
			boolean check = false;
			if(dao.deleteArticle(contents_vo)!=0) {
				check = true;
			}
			return check;
		}
		
		/** contentnum으로 게시물 조회 **/
		public BoardVO selectBoard(int contentnum) {
			BoardVO vo = new BoardVO();
			vo = dao.selectBoard(contentnum);
			return vo;
		}
		
		//지원 작성 부분
		/** 검색 **/
		public ArrayList<BoardVO> search(String category, String title) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.getSelectResult(category, title);
			return boardlist;
		}
		
		/** 내글 검색 **/
		public ArrayList<BoardVO> mycontent_search(String id) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.getmySelectResult(id);
			return boardlist;
		}
		
		/** 특정 게시물 클릭시 조회 -지원씀 **/
		public BoardVO select_one(int rownum) {
			BoardVO vo = new BoardVO();
			vo = dao.SelectOneResult(rownum);
			return vo;
		}
		
		/** 내글 수정 **/
		public void mycontent_update(String title, String ctg, String content, int rownum) {
			dao.mycontent_update(title, ctg, content, rownum);
		}
		
		/** 내글 삭제 **/
		public void mycontent_delete(int rownum) {
			dao.mycontent_delete(rownum);
		}
		
		/** 카테고리리턴 **/
		public int category_re(String category) {
			int foodnum=0;
			
			if(category.equals("중식")) {
				foodnum = 0;
			}else if(category.equals("양식")) {
				foodnum = 1;
			}else if(category.equals("일식")) {
				foodnum = 2;
			}else if(category.equals("분식")) {
				foodnum = 3;
			}else if(category.equals("한식"))
				foodnum = 4;
			
			return foodnum;
		}
}
