package com.ktds.gmkim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.gmkim.util.xml.XML;
import com.ktds.gmkim.vo.DirectorVO;

public class DirectorDAO {

	/**
	 * Get All Directors
	 * 
	 * @return
	 */
	public List<DirectorVO> getAllDirectors() {

		// 1. Driver
		loadOracleDriver();

		// 2. Conn, Stat, Rs are null
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// 3. list
		List<DirectorVO> directors = new ArrayList<DirectorVO>();

		try {
			// 4. conn
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);

			// 5. stmt
			String query = XML.getNodeString("//query/director/getAllDirectors/text()");
			stmt = conn.prepareStatement(query);

			// 6. r
			rs = stmt.executeQuery();

			// 7. v
			DirectorVO directorVO = null;

			// 8. s
			while (rs.next()) {

				directorVO = new DirectorVO();

				directorVO.setDirectorId(rs.getInt("DIRECTOR_ID"));
				directorVO.setDirectorName(rs.getString("DIRECTOR_NAME"));

				directors.add(directorVO);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {

			// close
			closeDB(conn, stmt, rs);

		}
		return directors;
	}

	/**
	 * Get Directors by Movie ID
	 * 
	 * @return
	 */
	public List<DirectorVO> getDirectorsByMovieId(int movieId) {

		// 1. Driver
		loadOracleDriver();

		// 2. Conn, Stat, Rs are null
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// 3. list
		List<DirectorVO> directors = new ArrayList<DirectorVO>();

		try {
			// 4. conn
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);

			// 5. stmt
			String query = XML.getNodeString("//query/director/getDirectorsByMovieId/text()");
			stmt = conn.prepareStatement(query);

			// SQL Parameter Mapping
			stmt.setInt(1, movieId);

			// 6. r
			rs = stmt.executeQuery();

			// 7. v
			DirectorVO directorVO = null;

			// 8. s
			while (rs.next()) {

				directorVO = new DirectorVO();

				directorVO.setDirectorName(rs.getString("DIRECTOR_NAME"));

				directors.add(directorVO);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {

			// close
			closeDB(conn, stmt, rs);

		}
		return directors;
	}

	public void insertNewDirectorOfNewMovie(DirectorVO director) {

		int insertCount = 0;

		// 1. Driver
		loadOracleDriver();

		// 2. Conn, Stat, Rs are null
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			// 4. Conn
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);

			// 5. Stmt
			String query = XML.getNodeString("//query/director/insertNewDirectorOfNewMovie/text()");
			stmt = conn.prepareStatement(query);

			// SQL Parameter Mapping
			stmt.setInt(1, director.getDirectorId());
			stmt.setInt(2, director.getMovieId());

//			insertCount = stmt.executeUpdate();
			stmt.executeUpdate();

			// 장르가 잘 등록이 되었는 지 확인해야 한다.
//			if (insertCount > 0) { // insertCount = 1이라는 것은 1건이 등록되었다란 의미
//				stmt.close();
//				query = XML.getNodeString("//query/director/getLatestDirectorId/text()");
//				stmt = conn.prepareStatement(query);
//
//				ResultSet rs = stmt.executeQuery();
//
//				int genreListId = 0;
//
//				if (rs.next()) {
//					genreListId = rs.getInt(1);
//				}
//
//				rs.close();
//
//				return genreListId;
//			}

			// 6. RS

			// 7. Set

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}


	}

	/**
	 * Load Oracle Driver
	 */
	private void loadOracleDriver() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * Close DB
	 * 
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
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
