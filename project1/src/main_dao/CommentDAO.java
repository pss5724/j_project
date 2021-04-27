package main_dao;

import java.util.ArrayList;

import main_vo.BoardVO;
import main_vo.CommentVO;
import main_vo.MemberVO;

public class  CommentDAO extends DBConn {

	/**´ñ±Û Á¶È¸**/
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
	
	public ArrayList<CommentVO> selectReply(MemberVO member){
		ArrayList<CommentVO> commentlist = new ArrayList<CommentVO>();
		String sql = " SELECT * FROM REPLY WHERE id = ? ORDER BY COMMENTDATE ASC ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, member.getId());
			 
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
	
	/** ´ñ±Û µî·Ï **/
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
}
