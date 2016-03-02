package com.ktds.leinalee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.vo.Const;
import com.ktds.leinalee.vo.GradeVO;

public class GradeDAO {

	public List<GradeVO> getAllGrade() {
		
		List<GradeVO> grades = new ArrayList<GradeVO>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORLD);
			String query = "SELECT * FROM GRADE";
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		GradeVO grade = null;
		try {
			while(rs.next()) {
				grade = new GradeVO();
				grade.setGradeId(rs.getInt("GRADE_ID"));
				grade.setGradeTitle(rs.getString("GRADE_TITLE"));
				
				grades.add(grade);
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
		
		return grades;
		
	}
}
