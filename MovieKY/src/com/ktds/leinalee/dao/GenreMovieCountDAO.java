package com.ktds.leinalee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.vo.Const;
import com.ktds.leinalee.vo.GenreMovieCountVO;

public class GenreMovieCountDAO {

	public List<GenreMovieCountVO> getAllGenreMovieCount() {
		
		List<GenreMovieCountVO> movies = new ArrayList<GenreMovieCountVO>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORLD);
			String query = "SELECT G.GENRE_TITLE , COUNT(M.MOVIE_ID) CNT "
					+ "FROM MOVIE M , GENRE G , GENRE_LIST GL "
					+ "WHERE M.MOVIE_ID = GL.MOVIE_ID AND G.GENRE_ID = GL.GENRE_ID "
					+ "GROUP BY G.GENRE_TITLE";
			System.out.println(query);
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		GenreMovieCountVO movie = null;
		try {
			while(rs.next()) {
				movie = new GenreMovieCountVO();
				movie.setGenreTitle(rs.getString("GENRE_TITLE"));
				movie.setMovieCount(rs.getInt("CNT"));
				
				System.out.println(rs.getString("GENRE_TITLE"));
				movies.add(movie);
			}
		} catch (SQLException e) {
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return movies;
	}
}
