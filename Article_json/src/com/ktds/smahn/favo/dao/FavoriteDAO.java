package com.ktds.smahn.favo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.smahn.Const;
import com.ktds.smahn.favo.vo.FavoriteVO;
import com.ktds.smahn.util.xml.XML;

public class FavoriteDAO {

	public int selectFavoriteCount(FavoriteVO favoriteVO) {
		int favoriteCount = 0;

		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/favorite/selectFavoriteCount/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, favoriteVO.getArticleId());
			stmt.setString(2, favoriteVO.getMemberId());
			
			rs = stmt.executeQuery();

			rs.next();
			
			favoriteCount = rs.getInt(1);
			
			return favoriteCount;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	public void insertFavorite (FavoriteVO favoriteVO) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/favorite/insertFavorite/text()");
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, favoriteVO.getArticleId());
			stmt.setString(2, favoriteVO.getMemberId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}

	}
	
	public void deleteFavorite (FavoriteVO favoriteVO) {
		int insertCount = 0;
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/favorite/deleteFavorite/text()");
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, favoriteVO.getArticleId());
			stmt.setString(2, favoriteVO.getMemberId());

			insertCount = stmt.executeUpdate();

			if (insertCount > 0) {
				stmt.close();
				System.out.println("게시글 삭제 성공");
			}

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
