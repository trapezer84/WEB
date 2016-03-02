package com.ktds.gmkim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.gmkim.util.xml.XML;
import com.ktds.gmkim.vo.GradeVO;

public class GradeDAO {

	/**
	 * Get All Grades
	 * 
	 * @return
	 */
	public List<GradeVO> getAllGrades() {
		
		// 1. Driver
		loadOracleDriver();
		
		// 2. Conn, Stat, Rs are null
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 3. list
		List<GradeVO> Grades = new ArrayList<GradeVO>();
		
		try {
			// 4. conn
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_MOVIE_USER, Const.DB_MOVIE_PASSWORD);
			
			// 5. stmt
			String query = XML.getNodeString("//query/grade/getAllGrades/text()");
			stmt = conn.prepareStatement(query);
			
			// 6. r
			rs = stmt.executeQuery();
			
			// 7. v
			GradeVO GradeVO = null;
			
			// 8. s
			while ( rs.next() ) {
				
				GradeVO = new GradeVO();
				
				GradeVO.setGradeId(rs.getInt("GRADE_ID"));
				GradeVO.setGradeTitle(rs.getString("GRADE_TITLE"));
				
				Grades.add(GradeVO);
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			
			// close
			closeDB(conn, stmt, rs);
			
		}
		return Grades;
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