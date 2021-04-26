package main_dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import main_gui.CategoryUI;
import main_vo.BoardVO;
import main_vo.CommentVO;
import main_vo.MemberVO;

public class BoardDAO extends DBConn{
	
	//지원 작성 부분
	/** 검색 **/
	public ArrayList<BoardVO> getSelectResult(String category, String title) {
		ArrayList<BoardVO> content = new ArrayList<BoardVO>();
		if(category.equals("제목")) {
			try {
				String sql = " select * "
						+ " from content "
						+ " where title like ?"
						+ " order by content_date ";
				getPreparedStatement(sql);
				pstmt.setString(1, '%'+title+'%');	//?에 title 집어넣기
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVO board = new BoardVO();
					board.setContentnum(rs.getInt(1));
					board.setCategory(rs.getString(2));
					board.setId(rs.getString(3));
					board.setTitle(rs.getString(4));
					board.setContent(rs.getString(5));
					board.setDate(rs.getString(6));
					board.setLocation(rs.getString(7));
					
					content.add(board);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			return content;
		}else if(category.equals("작성자")) {
			try {
				String sql = " select * "
						+ " from content "
						+ " where id like ?"
						+ " order by content_date ";
				getPreparedStatement(sql);
				pstmt.setString(1, '%'+title+'%');	//?에 title 집어넣기
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVO board = new BoardVO();
					board.setContentnum(rs.getInt(1));
					board.setCategory(rs.getString(2));
					board.setId(rs.getString(3));
					board.setTitle(rs.getString(4));
					board.setContent(rs.getString(5));
					board.setDate(rs.getString(6));
					board.setLocation(rs.getString(7));
					
					content.add(board);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			return content;
		}else if(category.equals("내용")) {
			try {
				String sql = " select * "
						+ " from content "
						+ " where content like ?"
						+ " order by content_date ";
				getPreparedStatement(sql);
				pstmt.setString(1, '%'+title+'%');	//?에 title 집어넣기
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVO board = new BoardVO();
					board.setContentnum(rs.getInt(1));
					board.setCategory(rs.getString(2));
					board.setId(rs.getString(3));
					board.setTitle(rs.getString(4));
					board.setContent(rs.getString(5));
					board.setDate(rs.getString(6));
					board.setLocation(rs.getString(7));
					
					content.add(board);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			return content;
		}
//		try {
//			String sql = " select * "
//					+ " from content "
//					+ " where title like ?"
//					+ " order by content_date ";
//			getPreparedStatement(sql);
//			pstmt.setString(1, '%'+title+'%');	//?에 title 집어넣기
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				BoardVO board = new BoardVO();
//				board.setContentnum(rs.getInt(1));
//				board.setCategory(rs.getString(2));
//				board.setId(rs.getString(3));
//				board.setTitle(rs.getString(4));
//				board.setContent(rs.getString(5));
//				board.setDate(rs.getString(6));
//				board.setLocation(rs.getString(7));
//				
//				content.add(board);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
		
		return content;
	}
	
	/** 내글 검색 **/
	public ArrayList<BoardVO> getmySelectResult(String id){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		MemberVO memeber = new MemberVO();
		
		try {
			String sql = " select contentNum, category, id, title, content_date "
					+ " from content "
					+ " where id = ? "
					+ " order by content_date ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				
				board.setContentnum(rs.getInt(1));
				board.setCategory(rs.getString(2));
				board.setId(rs.getString(3));
				board.setTitle(rs.getString(4));
				board.setDate(rs.getString(5));
				
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/** 내게시물 하나 조회 **/
	public BoardVO SelectOneResult(int rownum) {
		BoardVO board = new BoardVO();
		
		try {
			String sql = " select title, id, category, content_date, content "
					+ " from content "
					+ " where contentnum = ? "
					+ " order by content_date ";
			getPreparedStatement(sql);
			pstmt.setInt(1, rownum);
			
			rs = pstmt.executeQuery();
			rs.next();
			board.setTitle(rs.getString(1));
			board.setId(rs.getString(2));
			board.setCategory(rs.getString(3));
			board.setDate(rs.getString(4));
			board.setContent(rs.getString(5));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
		
	}
	
	/** 수정 **/
	public void mycontent_update(String title, String category, String content, int rownum) {
		
		try {
			String sql = " update content set title = ?, "
					+ " category = ?, "
					+ " content = ? "
					+ " where contentnum = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, category);
			pstmt.setString(3, content);
			pstmt.setInt(4, rownum);
			
			rs = pstmt.executeQuery();
			rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** 삭제 **/
	public void mycontent_delete(int rownum) {
		try {
			String sql = " delete from content where contentnum = ? ";
			getPreparedStatement(sql);
			pstmt.setInt(1, rownum);
			
			rs = pstmt.executeQuery();
			rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	// 세희 작성 부분 
	/** 전체 조회 **/
	public ArrayList<BoardVO> SelectResult(MemberVO m_vo, int food_num) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String location = m_vo.getLocation();
		if(food_num==CategoryUI.ALL_FOOD) {	//카테고리가 전체일 경우
			String sql = " SELECT * FROM CONTENT " + 
					" WHERE CATEGORY IN ('중식','양식','일식','분식','한식') " + 
					" AND LOCATION = ? ORDER BY CONTENT_DATE DESC ";
			getPreparedStatement(sql);
			
		}else {	//카테고리가 전체가 아닐 경우
		
			String sql = " SELECT * FROM CONTENT " + 
					" WHERE LOCATION = ? AND CATEGORY = ? " + 
					" ORDER BY CONTENT_DATE DESC ";
			getPreparedStatement(sql);
			
			//각 카테고리별 sql 작성
			try {
			switch(food_num) {
			case CategoryUI.CHINA_FOOD:
				pstmt.setString(2,"중식");
				break;
			case CategoryUI.WEST_FOOD:
				pstmt.setString(2,"양식");
				break;
			case CategoryUI.JAPAN_FOOD:
				pstmt.setString(2,"일식");
				break;
			case CategoryUI.BOONSIK:
				pstmt.setString(2,"분식");
				break;
			case CategoryUI.KOREA_FOOD:
				pstmt.setString(2,"한식");
				break;
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//location 맵핑, sql 적용, 데이터 가져오기
		try {
			pstmt.setString(1, location);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardVO vo = new BoardVO();
				vo.setContentnum(rs.getInt(1));
				vo.setCategory(rs.getString(2));
				vo.setId(rs.getString(3));
				vo.setTitle(rs.getString(4));
				vo.setContent(rs.getString(5));
				vo.setDate(rs.getString(6));
				vo.setLocation(rs.getString(7));

				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	/** 게시글 작성 **/
	public int writeArticle(MemberVO member, String ctg, String title, String contents) {
		//글쓰기 sql. member, 카테고리, 제목, 내용 받아옴
		int result = 0;
		String sql = " INSERT INTO CONTENT " + 
				"VALUES(SEQU_CONTENT_contentNum.NEXTVAL, ?, ?, ?, " + 
				"?, SYSDATE, ?) ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, ctg);
			pstmt.setString(2, member.getId());
			pstmt.setString(3, title);
			pstmt.setString(4, contents);
			pstmt.setString(5, member.getLocation());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**댓글 조회**/
	public ArrayList<CommentVO> selectReply(BoardVO contents_vo){
		ArrayList<CommentVO> commentlist = new ArrayList<CommentVO>();
		String sql = " SELECT * FROM REPLY WHERE CONTENTNUM = ? ORDER BY COMMENTDATE ASC ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setInt(1, contents_vo.getContentnum());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentVO vo = new CommentVO();
				vo.setCommentnum(rs.getInt(1));
				vo.setContentnum(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.setComment(rs.getString(4));
				vo.setDate(rs.getString(5));
				
				commentlist.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return commentlist;
	}
	
	/** 댓글 등록 **/
	public int insertReply(BoardVO b_vo, MemberVO m_vo, String comment) {
		int result = 0;
		String sql = " INSERT INTO REPLY " + 
				" VALUES(SEQU_REPLY_COMMENTNUM.NEXTVAL, ?, ?, ?, SYSDATE ) ";
		getPreparedStatement(sql);
		try {
			pstmt.setInt(1, b_vo.getContentnum());
			pstmt.setString(2, m_vo.getId());
			pstmt.setString(3, comment);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**종료**/
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(pstmt!=null) pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

