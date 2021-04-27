package main_dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import main_gui.CategoryUI;
import main_vo.BoardVO;
import main_vo.CommentVO;
import main_vo.MemberVO;

public class BoardDAO extends DBConn{
	
	//���� �ۼ� �κ�
	/** �˻� **/
	public ArrayList<BoardVO> getSelectResult(String category, String title) {
		ArrayList<BoardVO> content = new ArrayList<BoardVO>();
		if(category.equals("����")) {
			try {
				String sql = " select * "
						+ " from content "
						+ " where title like ?"
						+ " order by content_date ";
				getPreparedStatement(sql);
				pstmt.setString(1, '%'+title+'%');	//?�� title ����ֱ�
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
		}else if(category.equals("�ۼ���")) {
			try {
				String sql = " select * "
						+ " from content "
						+ " where id like ?"
						+ " order by content_date ";
				getPreparedStatement(sql);
				pstmt.setString(1, '%'+title+'%');	//?�� title ����ֱ�
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
		}else if(category.equals("����")) {
			try {
				String sql = " select * "
						+ " from content "
						+ " where content like ?"
						+ " order by content_date ";
				getPreparedStatement(sql);
				pstmt.setString(1, '%'+title+'%');	//?�� title ����ֱ�
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
//			pstmt.setString(1, '%'+title+'%');	//?�� title ����ֱ�
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
	
	/** ���� �˻� **/
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
	
	/** ���Խù� �ϳ� ��ȸ **/
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
	
	/** ���� **/
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
	
	/** ���� **/
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
	
	
	
	
	
	// ���� �ۼ� �κ� 
	/** ��ü ��ȸ **/
	public ArrayList<BoardVO> SelectResult(MemberVO m_vo, int food_num) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String location = m_vo.getLocation();
		if(food_num==CategoryUI.ALL_FOOD) {	//ī�װ��� ��ü�� ���
			String sql = " SELECT * FROM CONTENT " + 
					" WHERE CATEGORY IN ('�߽�','���','�Ͻ�','�н�','�ѽ�') " + 
					" AND LOCATION = ? ORDER BY CONTENT_DATE DESC ";
			getPreparedStatement(sql);
			
		}else {	//ī�װ��� ��ü�� �ƴ� ���
		
			String sql = " SELECT * FROM CONTENT " + 
					" WHERE LOCATION = ? AND CATEGORY = ? " + 
					" ORDER BY CONTENT_DATE DESC ";
			getPreparedStatement(sql);
			
			//�� ī�װ��� sql �ۼ�
			try {
			switch(food_num) {
			case CategoryUI.CHINA_FOOD:
				pstmt.setString(2,"�߽�");
				break;
			case CategoryUI.WEST_FOOD:
				pstmt.setString(2,"���");
				break;
			case CategoryUI.JAPAN_FOOD:
				pstmt.setString(2,"�Ͻ�");
				break;
			case CategoryUI.BOONSIK:
				pstmt.setString(2,"�н�");
				break;
			case CategoryUI.KOREA_FOOD:
				pstmt.setString(2,"�ѽ�");
				break;
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//location ����, sql ����, ������ ��������
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
	
	
	
	/** �Խñ� �ۼ� **/
	public int writeArticle(MemberVO member, String ctg, String title, String contents) {
		//�۾��� sql. member, ī�װ�, ����, ���� �޾ƿ�
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
	
	/** �Խù� ���� **/
	public int updateArticle(String ctg, String title, String contents, BoardVO update_vo) {
		int result = 0;
		int contentnum = update_vo.getContentnum();
		String sql = " UPDATE CONTENT SET CATEGORY = ?, TITLE = ?, CONTENT = ? WHERE CONTENTNUM = ? ";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, ctg);
			pstmt.setString(2, title);
			pstmt.setString(3, contents);
			pstmt.setInt(4, contentnum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** �Խù� ���� **/
	public int deleteArticle(BoardVO vo) {
		int result = 0;
		int contentnum = vo.getContentnum();
		String sql = " DELETE FROM CONTENT WHERE CONTENTNUM = ? ";
		getPreparedStatement(sql);
		try {
			pstmt.setInt(1, contentnum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**����**/
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

