package main_system;

import java.util.ArrayList;

import main_dao.BoardDAO;
import main_dao.MemberDAO; 
import main_vo.BoardVO;
import main_vo.CommentVO;
import main_vo.MemberVO;

public class MainSystem {
	MemberDAO mdao = new MemberDAO();	
	BoardDAO dao = new BoardDAO();
	
	//login ���
		public static boolean LOGIN_RESULT = false;
		
		//Constructor
		public MainSystem() {		
			
		}
		
		//���� �ۼ� �κ�
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
	
		/** �ߺ�üũ **/
		public ArrayList<MemberVO> getMemberList() {
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			list = mdao.getMemberResult();
			return list;
		}
		
		/** �ߺ�üũ **/
		public boolean memberUpdate(MemberVO member, String id) {
			
			return mdao.getUpdateResult(member, id);
		}
		
		/** �ߺ�üũ **/
		public boolean memberDelete(String id) {
			
			return mdao.getDeleteResult(id);
		}
		
		/** ȸ�� ��ȸ **/
		public MemberVO memberSearch(String id) {
			
			return mdao.getSearchResult(id);
		}
		
		/** ȸ�� ��ȸ **/
		public MemberVO getMemberInfo(String id) {
			
			return mdao.getMemberInfo(id);
		}
		
		/** ���� **/
		public void close() {
			mdao.close();
			System.out.println("-------- �����ͺ��̽� ���� ���� ---------");
		}
	
		//���� �ۼ� �κ�
		/** �� ī�װ��� �Խ��� ��ȸ **/
		public ArrayList<BoardVO> select_ctg(MemberVO vo, int food_num) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.SelectResult(vo, food_num);
			return boardlist;
		}
		
//		/** Ư�� �Խù� Ŭ���� �Խñ� ��ȸ **/
//		public BoardVO select_one(int food_num, int rownum) {
//			BoardVO vo = new BoardVO();
//			vo = dao.SelectOneResult(food_num, rownum);
//			return vo;
//		}
		
		/** Ư�� �Խù� Ŭ���� ��� ��ȸ **/
		public ArrayList<CommentVO> select_reply(BoardVO contents_vo){
			ArrayList<CommentVO> commentlist = new ArrayList<CommentVO>();
			commentlist = dao.selectReply(contents_vo);
			return commentlist;
		}
		
		/** ��� �ۼ� **/
		public int insert_reply(BoardVO b_vo, MemberVO m_vo, String comment) {
			int result = dao.insertReply(b_vo, m_vo, comment);
			return result;
		}
		
		/** �Խù� �ۼ� **/
		public boolean writeArticle(MemberVO member, String ctg, String title, String contents) {
			boolean check = false;
			if(dao.writeArticle(member, ctg,title,contents)!=0) {
				check = true;
			}
			return check;
		}
		
		//���� �ۼ� �κ�
		/** �˻� **/
		public ArrayList<BoardVO> search(String category, String title) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.getSelectResult(category, title);
			return boardlist;
		}
		
		/** ���� �˻� **/
		public ArrayList<BoardVO> mycontent_search(String id) {
			ArrayList<BoardVO> boardlist = new ArrayList<BoardVO>();
			boardlist = dao.getmySelectResult(id);
			return boardlist;
		}
		
		/** Ư�� �Խù� Ŭ���� ��ȸ -������ **/
		public BoardVO select_one(int rownum) {
			BoardVO vo = new BoardVO();
			vo = dao.SelectOneResult(rownum);
			return vo;
		}
		
		/** ���� ���� **/
		public void mycontent_update(String title, String ctg, String content, int rownum) {
			dao.mycontent_update(title, ctg, content, rownum);
		}
		
		/** ���� ���� **/
		public void mycontent_delete(int rownum) {
			dao.mycontent_delete(rownum);
		}
		
		/** ī�װ����� **/
		public int category_re(String category) {
			int foodnum=0;
			
			if(category.equals("�߽�")) {
				foodnum = 0;
			}else if(category.equals("���")) {
				foodnum = 1;
			}else if(category.equals("�Ͻ�")) {
				foodnum = 2;
			}else if(category.equals("�н�")) {
				foodnum = 3;
			}else if(category.equals("�ѽ�"))
				foodnum = 4;
			
			return foodnum;
		}
}
