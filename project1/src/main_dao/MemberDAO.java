package main_dao;

import main_vo.MemberVO;

public class MemberDAO extends DBConn{
	/** 로그인 처리 **/
	public int getLoginResult(String id, String pass) {
		int result = 0;
		
		try {
			String sql = " SELECT memberNum FROM MEMBER " + 
					" WHERE id=? AND pass=?";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
				
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public MemberVO getMemberInfo(int memberNum) {
		MemberVO mem = new MemberVO();
		
		try {
			String sql = " SELECT * FROM MEMBER " + 
					" WHERE memberNum=?";
			getPreparedStatement(sql);
			
			pstmt.setInt(1, memberNum);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemberNum(rs.getInt(1));
				member.setId(rs.getString(2));
				member.setPass(rs.getString(3));
				member.setName(rs.getString(4));
				member.setHp(rs.getString(5));
				member.setLocation(rs.getString(6));
				
				
				mem = member;
			}				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mem;
	}
	
	public boolean getJoinResult(MemberVO member) {
		
		
		try {
			String sql = " insert into ";
			getPreparedStatement(sql);
			
			pstmt.setInt(1, memberNum);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemberNum(rs.getInt(1));
				member.setId(rs.getString(2));
				member.setPass(rs.getString(3));
				member.setName(rs.getString(4));
				member.setHp(rs.getString(5));
				member.setLocation(rs.getString(6));
				
				
				mem = member;
			}				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mem;
	}
	
	
	
}
