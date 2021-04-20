package main_dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import main_gui.boardUI_Category;
import main_vo.BoardVO;
import main_vo.MemberVO;

public class BoardDAO extends DBConn{
	
	//���� �ۼ� �κ�
	/** �˻� **/
	public ArrayList<BoardVO> getSelectResult(String title) {
		ArrayList<BoardVO> content = new ArrayList<BoardVO>();
		try {
			String sql = " select contentNum, category, id, title, content_date "
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
				board.setDate(rs.getString(5));
				
				content.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return content;
	}
	
	/** ���� �˻� **/
	public ArrayList<BoardVO> getmySelectResult(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		MemberVO memeber = new MemberVO();
		
		try {
			String sql = " select contentNum, category, id, title, content_date "
					+ " from content "
					+ " where id = ? "
					+ " order by content_date ";
			getPreparedStatement(sql);
			//id �־����
//			pstmt.setString(1, id);
			String admin = "admin";	//test��
			pstmt.setString(1, admin);
			
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
	
	/** ���� **/
	
	/** ���� **/
	
	
	
	
	
	// ���� �ۼ� �κ� 
	/** ��ü ��ȸ **/
	public ArrayList<BoardVO> SelectResult(int food_num) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = " SELECT CATEGORY,ID,TITLE,CONTENT,CONTENT_DATE FROM CONTENT " + 
				" WHERE CATEGORY = ? " + 
				" ORDER BY CONTENT_DATE DESC ";
		getPreparedStatement(sql);
		
		//�� ī�װ��� sql �ۼ�
		try {
		switch(food_num) {
		case boardUI_Category.ALL_FOOD:
			pstmt.setString(1,"��ü");
			break;
		case boardUI_Category.CHINA_FOOD:
			pstmt.setString(1,"�߽�");
			break;
		case boardUI_Category.WEST_FOOD:
			pstmt.setString(1,"���");
			break;
		case boardUI_Category.JAPAN_FOOD:
			pstmt.setString(1,"�Ͻ�");
			break;
		case boardUI_Category.BOONSIK:
			pstmt.setString(1,"�н�");
			break;
		case boardUI_Category.KOREA_FOOD:
			pstmt.setString(1,"�ѽ�");
			break;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//sql ����, ������ ��������
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
	
	/** �Խù� �ϳ� ��ȸ **/
	public BoardVO SelectOneResult(int food_num, int rownum) {
		BoardVO vo = new BoardVO();
		String sql = " SELECT CATEGORY, ID, TITLE, CONTENT, CONTENT_DATE FROM " + 
				" (SELECT ROWNUM NO,CATEGORY,ID,TITLE,CONTENT,CONTENT_DATE FROM " + 
				" (SELECT CATEGORY,ID,TITLE,CONTENT,CONTENT_DATE FROM CONTENT " + 
				" WHERE CATEGORY=? " + 
				" ORDER BY CONTENT_DATE DESC)) " +
				" WHERE NO = ? ";
		getPreparedStatement(sql);
		
		//�� ī�װ��� sql �ۼ�
				try {
				switch(food_num) {
				case boardUI_Category.ALL_FOOD:
					pstmt.setString(1,"��ü");
					break;
				case boardUI_Category.CHINA_FOOD:
					pstmt.setString(1,"�߽�");
					break;
				case boardUI_Category.WEST_FOOD:
					pstmt.setString(1,"���");
					break;
				case boardUI_Category.JAPAN_FOOD:
					pstmt.setString(1,"�Ͻ�");
					break;
				case boardUI_Category.BOONSIK:
					pstmt.setString(1,"�н�");
					break;
				case boardUI_Category.KOREA_FOOD:
					pstmt.setString(1,"�ѽ�");
					break;
				}
				pstmt.setInt(2, rownum+1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//sql ����, ������ ��������
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
	
	
	/** �Խñ� �ۼ� **/
	public void writeArticle(MemberVO member, String ctg, String title, String contents) {
		//�۾��� sql. member, ī�װ�, ����, ���� �޾ƿ�
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

