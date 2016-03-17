package com.ktds.smahn.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.smahn.Const;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.vo.MemberSearchVO;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.xml.XML;

/**
 * 
 * @author sm ahn
 *
 */
public class MemberDAO {

	/**
	 * 맴버 정보 얻어오기 (Id & PWD)
	 * 
	 * @param member
	 * @return
	 */
	public MemberVO getMemberByIdAndPassword(MemberVO member) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			// 아이디, 패스워드를 테이블에서 꺼내온다.
			String query = XML.getNodeString("//query/member/getMemberByIdAndPassword/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getPassword());

			rs = stmt.executeQuery();

			MemberVO validMember = null;

			// 한건만 가지고 올 것이므로 if문을 쓴다.
			// 세션은 노출될 위험은 없지만, 도난당할 위험은 있다. 누구도 session에 있는 정보를 꺼내볼 수 없다.
			if (rs.next()) {
				validMember = new MemberVO();
				validMember.setMemberId(rs.getString("MEMBER_ID"));
				validMember.setNickName(rs.getString("NICK_NAME"));
				validMember.setPassword(rs.getString("PASSWORD"));
				validMember.setEmail(rs.getString("EMAIL"));
				validMember.setIsAdmin(rs.getString("IS_ADMIN"));
			}

			return validMember;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			closeDB(conn, stmt, rs);
		}
	}

	/**
	 * DB에 사용자 등록하기
	 * 
	 * @param newMemberInfo
	 */
	public void addNewMember(MemberVO newMemberInfo) {
		
		int insertCount = 0;
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/addNewMember/text()");
			stmt = conn.prepareStatement(query);
			System.out.println(query);

			stmt.setString(1, newMemberInfo.getMemberId());
			stmt.setString(2, newMemberInfo.getNickName());
			stmt.setString(3, newMemberInfo.getPassword());
			stmt.setString(4, newMemberInfo.getEmail());

			insertCount = stmt.executeUpdate();

			if (insertCount > 0) {
				stmt.close();
				System.out.println("가입 성공");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}

	
	/**
	 * 전체 회원 수를 가져오는 메소드 
	 * @return
	 */

	public int getAllMemberCount() {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getAllMemberCount/text()");
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			int memberCount = 0;
			rs.next();
			memberCount = rs.getInt(1);
			
			return memberCount;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	/**
	 * DB에서 전체 회원 리스트 조회
	 * @param searchVO
	 * @return
	 */
	public List<MemberVO> getAllMembers(MemberSearchVO searchVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<MemberVO> members = new ArrayList<MemberVO>();

		try {

			MemberVO member = null;

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			// article을 꺼내온다.
			String query = XML.getNodeString("//query/member/getAllMembers/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, searchVO.getEndIndex());
			stmt.setInt(2, searchVO.getStartIndex());
					
			rs = stmt.executeQuery();

			while (rs.next()) {
				member = new MemberVO();
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setNickName(rs.getString("NICK_NAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setIsAdmin(rs.getString("IS_ADMIN"));
				
				members.add(member);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			closeDB(conn, stmt, rs);
		}

		return members;
	}
	
	/**
	 * Member 가져오기 
	 * @param memberId
	 * @return
	 */
	public MemberVO getOneMemberByMemberId(String memberId) {
		
		MemberVO member = new MemberVO();

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getOneMemberByMemberId/text()");
			stmt = conn.prepareStatement(query);

			stmt.setString(1, memberId);

			rs = stmt.executeQuery();

			if (rs.next()) {
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setNickName(rs.getString("NICK_NAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setEmail(rs.getString("EMAIL"));
				member.setIsAdmin(rs.getString("IS_ADMIN"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}

		return member;
	}
	
	/**
	 * delete Member by memberId 
	 * @param memberId
	 */
	public void deleteMemberByMemberId(String memberId) {

		int insertCount = 0;
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/deleteMemberByMemberId/text()");
			stmt = conn.prepareStatement(query);
			System.out.println(query);

			stmt.setString(1, memberId);

			insertCount = stmt.executeUpdate();

			if (insertCount > 0) {
				stmt.close();
				System.out.println("회원 삭제 성공");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}

	
	/**
	 * DB loader
	 */
	private void loadOracleDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private void closeDB(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

	}



}
