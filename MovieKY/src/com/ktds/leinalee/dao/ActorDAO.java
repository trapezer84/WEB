package com.ktds.leinalee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.util.xml.XML;
import com.ktds.leinalee.vo.ActorVO;
import com.ktds.leinalee.vo.Const;

public class ActorDAO {

	public List<ActorVO> getOActorDAObyMovieId(int movieId) {
		
		List<ActorVO> actors = new ArrayList<ActorVO>();
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
	
		try {
			conn =  DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORLD);
			String query = XML.getNodeString("//query/movie/getOneDirectorDAObyMovieId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, movieId);
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		
		
		
		return actors;
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
