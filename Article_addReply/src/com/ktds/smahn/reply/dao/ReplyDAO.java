package com.ktds.smahn.reply.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.smahn.Const;
import com.ktds.smahn.reply.vo.ReplyVO;
import com.ktds.smahn.util.xml.XML;

public class ReplyDAO {

	/**
	 * reply list 가져옴 
	 * @param articleId
	 * @return
	 */
	public List<ReplyVO> getReplyListByArticleId(int articleId) {
		
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/getReplyListByArticleId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleId);
			rs = stmt.executeQuery();

			List<ReplyVO> replyList = new ArrayList<ReplyVO>();
			ReplyVO reply = null;
			while (rs.next()) {
				reply = new ReplyVO();
				
				reply.setReplyId(rs.getInt("REPLY_ID"));
				reply.setArticleId(rs.getInt("ARTICLE_ID"));
				reply.setMemberId(rs.getString("MEMBER_ID"));
				reply.setNickName(rs.getString("ARTICLE_ID"));
				reply.setDescript(rs.getString("DESCRIPT"));
				reply.setGroupId(rs.getInt("GROUP_ID"));
				reply.setParentReplyId(rs.getInt("PARENT_REPLY_ID"));
				reply.setDepth(rs.getInt("DEPTH"));
				reply.setOrderNo(rs.getInt("ORDER_NO"));
				
				replyList.add(reply);
			}

			// reply이 null이라면 null
			if (reply == null) {
				return null;
			}

			return replyList;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}

	
	public int addFile(ReplyVO reply) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/insertReply/text()");
			stmt = conn.prepareStatement(query);

			stmt.setString(1, reply.getMemberId());
			stmt.setInt(2, reply.getArticleId());
			stmt.setString(3, reply.getDescript());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
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
