package com.ktds.gmkim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.gmkim.util.xml.XML;
import com.ktds.gmkim.vo.MovieVO;


public class MovieDAO {
	
	/**
	 * Get All Movies
	 * 
	 * @return
	 */
	public List<MovieVO> getAllMovies() {
		
		// 1. Driver
		loadOracleDriver();
		
		// 2. Conn, Stat, Rs are null
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 3. list
		List<MovieVO> movies = new ArrayList<MovieVO>();
		
		try {
			// 4. conn
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);
			
			// 5. stmt
			String query = XML.getNodeString("//query/movie/getAllMovie/text()");
			System.out.println(query);
			stmt = conn.prepareStatement(query);
			
			// 6. r
			rs = stmt.executeQuery();
			
			// 7. v
			MovieVO movieVO = null;
			
			// 8. s
			while ( rs.next() ) {
				
				movieVO = new MovieVO();
				
				movieVO.setMovieId(rs.getInt("MOVIE_ID"));
				movieVO.setTitle(rs.getString("TITLE"));
				movieVO.setRate(rs.getDouble("RATE"));
				movieVO.setRunningTime(rs.getString("RUNNING_TIME"));
				movieVO.setOpenDate(rs.getString("OPEN_DATE"));
				movieVO.setGradeId(rs.getInt("GRADE_ID"));
				movieVO.setGradeTitle(rs.getString("GRADE_TITLE"));
				movieVO.setActorCount(rs.getInt("ACTOR_COUNT"));
				
				movies.add(movieVO);
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			
			// close
			closeDB(conn, stmt, rs);
			
		}
		return movies;
	} // getAllMovies

	
	/**
	 * Get a Movie Info By MovieId
	 * 
	 * @param movieId
	 * @return
	 */
	public MovieVO getOneMovieInfoByMovieId( int movieId ) {
		
		// 1. Driver
		loadOracleDriver();
		
		// 2. Conn, Stat, Rs are null
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 3. VO
		MovieVO movieVO = new MovieVO();
		
		try {
			
			// 4. Conn
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);
			
			// 5. Stmt
			String query = XML.getNodeString("//query/movie/getOneMovieInfoByMovieId/text()");
			stmt = conn.prepareStatement(query);
			
			// SQL Parameter Mapping
			stmt.setInt(1, movieId);
			
			// 6. RS
			rs = stmt.executeQuery();
			
			// 7. Set
			if ( rs.next() ) {
			
				movieVO.setMovieId(rs.getInt("MOVIE_ID"));
				movieVO.setTitle(rs.getString("TITLE"));
				movieVO.setRate(rs.getDouble("RATE"));
				movieVO.setRunningTime(rs.getString("RUNNING_TIME"));
				movieVO.setOpenDate(rs.getString("OPEN_DATE"));
				movieVO.setGradeId(rs.getInt("GRADE_ID"));
				movieVO.setGradeTitle(rs.getString("GRADE_TITLE"));
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);			
		}
		
		return movieVO;
		
	}
	
	public int insertNewMovie(MovieVO movie) {
		
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
					String query = XML.getNodeString("//query/movie/insertNewMovie/text()");
					stmt = conn.prepareStatement(query);
					
					// SQL Parameter Mapping
					stmt.setString(1, movie.getTitle());
					stmt.setDouble(2, movie.getRate());
					stmt.setString(3, movie.getRunningTime());
					stmt.setString(4, movie.getOpenDate());
					stmt.setInt(5, movie.getGradeId());
					
					insertCount = stmt.executeUpdate();
					
					// 영화가 잘 등록이 되었는 지 확인해야 한다. 
					if (insertCount > 0 ) { //insertCount = 1이라는 것은 1건이 등록되었다란 의미
						stmt.close();
						query = XML.getNodeString("//query/movie/getLatestMovieId/text()");
						stmt = conn.prepareStatement(query);
						
						ResultSet rs = stmt.executeQuery();
						
						int movieId = 0;
						
						if (rs.next()) {
							movieId = rs.getInt(1);
						}
						
						rs.close();
						
						return movieId;
					}
					
					// 6. RS
					
					// 7. Set
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				finally {
					closeDB(conn, stmt, null);			
				}
		
		
		return 0;
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
		if ( rs != null ) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
		if ( stmt != null ) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
		if ( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	
}


