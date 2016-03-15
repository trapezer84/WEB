package com.ktds.leinalee.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.leinalee.Consts;
import com.ktds.leinalee.member.vo.MemberVO;
import com.ktds.leinalee.util.xml.XML;


public class MemberDAO {

	/**
	 * 
	 * @param member
	 * @return
	 */
	public MemberVO getMemberByIdAndPassword(MemberVO member) {

		// 오라클 드라이버를 불러온다.
		loadOracleDriver();

		// Connection, Statement, ResultSet 변수를 생성한다.
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			
			// DB에 연결한다.
			conn = DriverManager.getConnection(Consts.DB_URL, Consts.DB_ARTICLE_USER, Consts.DB_ARTICLE_PASSWORD);

			// Query를 입력한다.
			String query = XML.getNodeString("//query/member/getMemberByIdAndPassword/text()");
			stmt = conn.prepareStatement(query);

			// Query의 ?에 데이터 입력한다.
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getPassword());

			// 쿼리를 실행하고 결과를 ResultSet에 넣는다.
			rs = stmt.executeQuery();
			
			// 새로운 VO 인스턴스 생성한다.
			MemberVO validMember = null;

			if (rs.next()) {
				
				validMember = new MemberVO();
				
				validMember.setMemberId( rs.getString("MEMBER_ID") );
				validMember.setPassword( rs.getString("PASSWORD") );
				validMember.setNickName( rs.getString("NICK_NAME") );
				validMember.setEmail( rs.getString("EMAIL") );
				
			}
			
			return validMember;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}

	// Load Oracle Driver
	private void loadOracleDriver() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	// Close DB
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
