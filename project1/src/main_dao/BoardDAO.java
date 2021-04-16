package main_dao;

import java.sql.PreparedStatement;

import main_vo.BoardVO;

public class BoardDAO extends DBConn{
	
	
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
	
	/** 검색 **/
	
}
