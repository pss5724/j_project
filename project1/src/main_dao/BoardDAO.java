package main_dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import main_gui.boardUI_Category;
import main_vo.BoardVO;
import main_vo.MemberVO;

public class BoardDAO extends DBConn{
	
	//지원 작성 부분
	/** 검색 **/
	public BoardVO getSelectResult(String title) {
		BoardVO content = new BoardVO();
		try {
			String sql = " select contentNum, category, id, title, content_date "
					+ " from content "
					+ " where title like ?";
			getPreparedStatement(sql);
			pstmt.setString(1, '%'+title+'%');	//?에 title 집어넣기
//			pstmt = conn.prepareStatement(sql + "'%" + title + "%'");	//'안의 것을 갖는 행만 읽기'
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				content.setContentnum(rs.getInt(1));
				content.setCategory(rs.getString(2	));
				content.setId(rs.getString(3));
				content.setTitle(rs.getString(4));
				content.setDate(rs.getString(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return content;
	}
	
	/** 내글 검색 **/
	public BoardDAO getmySelectResult(String name) {
		BoardDAO hororog = new BoardDAO();
		try {
			String sql = " select foodno, foodcategory, writter, title, hiredate "
					+ " from hororog "
					+ " where title=?";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
//				hororog.setNo(rs.getInt(1));
//				hororog.setCategory(rs.getString(2));
//				hororog.setWrittenby(rs.getString(3));
//				hororog.setTitle(rs.getString(4));
//				hororog.setHiredate(rs.getDate(5));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return hororog;
	}
	
	/** 수정 **/
	
	
	
	
	// 세희 작성 부분 
	/** 전체 조회 **/
	public ArrayList<BoardVO> SelectResult(int food_num) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = " SELECT CATEGORY,ID,TITLE,CONTENT,CONTENT_DATE FROM CONTENT " + 
				" WHERE CATEGORY = ? " + 
				" ORDER BY CONTENT_DATE DESC ";
		getPreparedStatement(sql);
		
		//각 카테고리별 sql 작성
		try {
		switch(food_num) {
		case boardUI_Category.ALL_FOOD:
			pstmt.setString(1,"전체");
			break;
		case boardUI_Category.CHINA_FOOD:
			pstmt.setString(1,"중식");
			break;
		case boardUI_Category.WEST_FOOD:
			pstmt.setString(1,"양식");
			break;
		case boardUI_Category.JAPAN_FOOD:
			pstmt.setString(1,"일식");
			break;
		case boardUI_Category.BOONSIK:
			pstmt.setString(1,"분식");
			break;
		case boardUI_Category.KOREA_FOOD:
			pstmt.setString(1,"한식");
			break;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//sql 적용, 데이터 가져오기
		try {
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardVO vo = new BoardVO();
				vo.setCategory(rs.getString(1));
				vo.setId(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setDate(rs.getString(5));

				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/** 게시물 하나 조회 **/
	public BoardVO SelectOneResult(int food_num, int rownum) {
		BoardVO vo = new BoardVO();
		String sql = " SELECT CATEGORY, ID, TITLE, CONTENT, CONTENT_DATE FROM " + 
				" (SELECT ROWNUM NO,CATEGORY,ID,TITLE,CONTENT,CONTENT_DATE FROM " + 
				" (SELECT CATEGORY,ID,TITLE,CONTENT,CONTENT_DATE FROM CONTENT " + 
				" WHERE CATEGORY=? " + 
				" ORDER BY CONTENT_DATE DESC)) " +
				" WHERE NO = ? ";
		getPreparedStatement(sql);
		
		//각 카테고리별 sql 작성
				try {
				switch(food_num) {
				case boardUI_Category.ALL_FOOD:
					pstmt.setString(1,"전체");
					break;
				case boardUI_Category.CHINA_FOOD:
					pstmt.setString(1,"중식");
					break;
				case boardUI_Category.WEST_FOOD:
					pstmt.setString(1,"양식");
					break;
				case boardUI_Category.JAPAN_FOOD:
					pstmt.setString(1,"일식");
					break;
				case boardUI_Category.BOONSIK:
					pstmt.setString(1,"분식");
					break;
				case boardUI_Category.KOREA_FOOD:
					pstmt.setString(1,"한식");
					break;
				}
				pstmt.setInt(2, rownum+1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//sql 적용, 데이터 가져오기
				try {
					rs = pstmt.executeQuery();
						rs.next();
						vo.setCategory(rs.getString(1));
						vo.setId(rs.getString(2));
						vo.setTitle(rs.getString(3));
						vo.setContent(rs.getString(4));
						vo.setDate(rs.getString(5));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		return vo;
	}
	
	
	/** 게시글 작성 **/
	public void writeArticle(MemberVO member, String ctg, String title, String contents) {
		//글쓰기 sql. member, 카테고리, 제목, 내용 받아옴
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
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

