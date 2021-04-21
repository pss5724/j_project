package main_dao;

import java.util.ArrayList;

import main_vo.MemberVO;

public class MemberDAO extends DBConn {
	/** 로그인 처리 **/
	public int getLoginResult(String id, String pass) {
		int result = 0;

		try {
			String sql = " SELECT memberNum FROM MEMBER " + " WHERE id=? AND pass=?";
			getPreparedStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pass);

			rs = pstmt.executeQuery();

			while (rs.next()) {
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
			String sql = " SELECT * FROM MEMBER " + " WHERE memberNum=?";
			getPreparedStatement(sql);

			pstmt.setInt(1, memberNum);

			rs = pstmt.executeQuery();
			while (rs.next()) {
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
	
	public MemberVO getMemberInfo(String id) {
		MemberVO mem = new MemberVO();

		try {
			String sql = " SELECT * FROM MEMBER " + " WHERE id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
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

	public ArrayList<MemberVO> getMemberResult() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();

		try {
			String sql = " select memberNum,id,pass,name,hp,location from member ";
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemberNum(rs.getInt(1));
				member.setId(rs.getString(2));
				member.setPass(rs.getString(3));
				member.setName(rs.getString(4));
				member.setHp(rs.getString(5));
				member.setLocation(rs.getString(6));
				list.add(member);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean getJoinResult(MemberVO member) {

		boolean result = false;
		try {
			String sql = "insert into member values (SEQU_MEMBER_memberNUM.nextval, ?, ?, ? ,?,?)";
			getPreparedStatement(sql);

			member.setHp(member.getHp1() + "-" + member.getHp2() + "-" + member.getHp3());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getHp());
			pstmt.setString(5, member.getLocation());

			int val = pstmt.executeUpdate();
			if (val != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean getDeleteResult(String id) {

		boolean result = false;
		try {
			String sql = " delete from member where id=?";
			getPreparedStatement(sql);

			
			pstmt.setString(1, id);
			

			int val = pstmt.executeUpdate();
			if (val != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean getCheckResult(String str) {

		boolean result = false;
		try {
			String sql = "select * from member where id=?";
			getPreparedStatement(sql);

			pstmt.setString(1, str);

			int val = pstmt.executeUpdate();
			if (val != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean getUpdateResult(MemberVO member, String id) {

		boolean result = false;
		try {
			String sql = " update member set pass=?, name=?, hp=?,location=? where id = ?";
			getPreparedStatement(sql);

			pstmt.setString(1, member.getPass());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getHp());
			pstmt.setString(4, member.getLocation());
			pstmt.setString(5, id);

			int val = pstmt.executeUpdate();
			if (val != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	public MemberVO getSearchResult(String id) {
		MemberVO mem = new MemberVO();
		
		try {
			String sql = " select pass,name,hp from member where id=?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setPass(rs.getString(1));
				member.setName(rs.getString(2));
				member.setHp(rs.getString(3));
				mem = member;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mem;
	}

}
