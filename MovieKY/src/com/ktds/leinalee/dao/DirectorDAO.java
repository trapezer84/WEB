package com.ktds.leinalee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.util.xml.XML;
import com.ktds.leinalee.vo.Const;
import com.ktds.leinalee.vo.DirectorVO;

public class DirectorDAO {
	
	public List<DirectorVO> getOneDirectorDAObyMovieId(int movieId) {
		List<DirectorVO> directors = new ArrayList<DirectorVO>();
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORLD);
			String query = XML.getNodeString("//query/movie/getOneDirectorDAObyMovieId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, movieId);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		DirectorVO director = null;
		
		try {
			while (rs.next()) {
				director = new DirectorVO();
				director.setDirectorId(rs.getInt("DIRECTOR_ID"));
				director.setDirectorName(rs.getString("DIRECTOR_NAME"));
				
				System.out.println(rs.getInt("DIRECTOR_ID"));
				System.out.println(rs.getString("DIRECTOR_NAME"));
				directors.add(director);
				
			}
		} catch (SQLException e) {
		} finally {
			closeDB(conn, stmt, rs);
		}
		
		return directors;
		
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
			} catch (SQLException e) {}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
}
