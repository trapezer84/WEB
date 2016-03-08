package com.ktds.smahn.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.smahn.Const;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.xml.XML;

/**
 * 
 * @author sm ahn
 *
 */
public class MemberDAO {

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
			// 세션은 노출될 위험은 있지만, 도난당할 위험은 있다. 누구도 session에 있는 정보를 꺼내볼 수 없다.
			if( rs.next() ){
				validMember = new MemberVO();
				validMember.setMemberId( rs.getString("MEMBER_ID") );
				validMember.setNickName( rs.getString("NICK_NAME") );
				validMember.setPassword( rs.getString("PASSWORD") );
				validMember.setEmail( rs.getString("EMAIL") );
			}
			
			return validMember;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			closeDB(conn, stmt, rs);
		}
	}

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
