package com.ktds.gmkim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.gmkim.util.xml.XML;
import com.ktds.gmkim.vo.ActorVO;
import com.ktds.gmkim.vo.GenreVO;

public class ActorDAO {

	/**
	 * Get All Actors
	 * 
	 * @return
	 */
	public List<ActorVO> getAllActors() {
		
		// 1. Driver
		loadOracleDriver();
		
		// 2. Conn, Stat, Rs are null
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 3. list
		List<ActorVO> Actors = new ArrayList<ActorVO>();
		
		try {
			// 4. conn
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);
			
			// 5. stmt
			String query = XML.getNodeString("//query/actor/getAllActors/text()");
			stmt = conn.prepareStatement(query);
			
			// 6. r
			rs = stmt.executeQuery();
			
			// 7. v
			ActorVO ActorVO = null;
			
			// 8. s
			while ( rs.next() ) {
				
				ActorVO = new ActorVO();
				
				ActorVO.setActorId(rs.getInt("ACTOR_ID"));
				ActorVO.setActorName(rs.getString("ACTOR_NAME"));
				
				Actors.add(ActorVO);
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			
			// close
			closeDB(conn, stmt, rs);
			
		}
		return Actors;
	}
	
	/**
	 * Get Actors by Movie ID
	 * 
	 * @return
	 */
	public List<ActorVO> getActorsByMovieId(int movieId) {
		
		// 1. Driver
		loadOracleDriver();
		
		// 2. Conn, Stat, Rs are null
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 3. list
		List<ActorVO> Actors = new ArrayList<ActorVO>();
		
		try {
			// 4. conn
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);
			
			// 5. stmt
			String query = XML.getNodeString("//query/actor/getActorsByMovieId/text()");
			stmt = conn.prepareStatement(query);
			
			// SQL Parameter Mapping
			stmt.setInt(1, movieId);
			
			// 6. r
			rs = stmt.executeQuery();
			
			// 7. v
			ActorVO ActorVO = null;
			
			// 8. s
			while ( rs.next() ) {
				
				ActorVO = new ActorVO();
				
				ActorVO.setActorName(rs.getString("ACTOR_NAME"));
				
				Actors.add(ActorVO);
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			
			// close
			closeDB(conn, stmt, rs);
			
		}
		return Actors;
	}
	
	/**
	 * Add New Actor by Actor_Name
	 * 
	 * @param actorName
	 * @return
	 */
	public boolean addNewActor( String actorName ) {
		
		// 1. Driver
		loadOracleDriver();
		
		// 2. C, S are null
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);
			
			String query = XML.getNodeString("//query/actor/insertNewActor/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, actorName);
			
			int insertCount = stmt.executeUpdate();
			
			return insertCount > 0;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			
			// close
			closeDB(conn, stmt, null);
			
		}
	}
	
	// 새로운 장르 리스트 추가하기
	public void insertNewActorOfNewMovie(ActorVO actor) {
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
			String query = XML.getNodeString("//query/actor/insertNewActorOfNewMovie/text()");
			stmt = conn.prepareStatement(query);

			// SQL Parameter Mapping
			stmt.setInt(1, actor.getMovieId());
			stmt.setInt(2, actor.getActorId());

//			insertCount = stmt.executeUpdate();
			stmt.executeUpdate();
			
			// 장르가 잘 등록이 되었는 지 확인해야 한다. 
//			if (insertCount > 0 ) { //insertCount = 1이라는 것은 1건이 등록되었다란 의미
//				stmt.close();
//				query = XML.getNodeString("//query/actor/getLatestActorId/text()");
//				stmt = conn.prepareStatement(query);
//				
//				ResultSet rs = stmt.executeQuery();
//				
//				int actorListId = 0;
//				
//				if (rs.next()) {
//					actorListId = rs.getInt(1);
//				}
//				
//				rs.close();
//				
//				return actorListId;
//			}
			
			// 6. RS

			// 7. Set

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}

//		return 0;
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
