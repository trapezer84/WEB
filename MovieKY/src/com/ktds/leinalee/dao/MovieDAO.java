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
import com.ktds.leinalee.vo.MovieVO;

public class MovieDAO {

	public List<MovieVO> getAllMovie() {
		
		List<MovieVO> movies = new ArrayList<MovieVO>();
		
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORLD);
			
//			String query = "SELECT M.*, G.GRADE_TITLE, AC.ACTOR_COUNT FROM MOVIE M, GRADE G, (SELECT MOVIE_ID, COUNT(ACTOR_LIST_ID) ACTOR_COUNT FROM ACTOR_LIST GROUP BY MOVIE_ID) AC WHERE M.GRADE_ID = G.GRADE_ID AND AC.MOVIE_ID(+) = M.MOVIE_ID";
			String query = XML.getNodeString("//query/movie/getAllMovie/text()");
			
			stmt = conn.prepareStatement(query);
			
			System.out.println(query);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		MovieVO movie = null;
		
		try {
			while(rs.next()) {
				movie = new MovieVO();
				
				movie.setMovieId(rs.getInt("MOVIE_ID"));
				movie.setTitle(rs.getString("TITLE"));
				movie.setRate(rs.getDouble("RATE"));
				movie.setRunningTime(rs.getString("RUNNING_TIME"));
				movie.setOpenDate(rs.getString("OPEN_DATE"));
				movie.setGradeId(rs.getInt("GRADE_ID"));
				movie.setGradeTitle(rs.getString("GRADE_TITLE"));
				movie.setActorCount(rs.getInt("ACTOR_COUNT"));
				
				movies.add(movie);
			}
		} catch (SQLException e) {
		} finally {
			closeDB(conn, stmt, rs);
		}
		
		return movies;
		
	}
	
	public MovieVO getOneMovieInfoByMovieId(int movieId){
		
		//1. Driver load....
		this.loadOracleDriver();
		
		//2. 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		MovieVO movie = new MovieVO();
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORLD);
			String query = XML.getNodeString("//query/movie/getOneMovieInfoByMovieId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, movieId);
			rs = stmt.executeQuery();
			
			if ( rs.next() ) {				
				movie.setMovieId(rs.getInt("MOVIE_ID"));
				movie.setTitle(rs.getString("TITLE"));
				movie.setRate(rs.getDouble("RATE"));
				movie.setRunningTime(rs.getString("RUNNING_TIME"));
				movie.setOpenDate(rs.getString("OPEN_DATE"));
				movie.setGradeId(rs.getInt("GRADE_ID"));
				movie.setGradeTitle(rs.getString("GRADE_TITLE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
		
		return movie;
		
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
