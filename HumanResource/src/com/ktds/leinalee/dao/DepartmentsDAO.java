package com.ktds.leinalee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ktds.leinalee.vo.DepartmentsVO;

public class DepartmentsDAO {

	public List<DepartmentsVO> getAllDepartments() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<DepartmentsVO> departments = new ArrayList<DepartmentsVO>();
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = "SELECT * FROM DEPARTMENTS";
			stmt = conn.prepareStatement(query);
			
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} 
		
		DepartmentsVO department = null;
		
		try {
			while( rs.next() ) {
				department = new DepartmentsVO();
				department.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
				department.setDepartmentName(rs.getString("DEPARTMENT_ID"));
				department.setManagerId(rs.getInt("DEPARTMENT_ID"));
				department.setLocationId(rs.getInt("DEPARTMENT_ID"));
				
				departments.add(department);
			}
		} catch (SQLException e) {
			
		} finally {
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
			
			if (conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return departments;
	}
	
	public static void main(String[] args) {
		
		DepartmentsDAO dao = new DepartmentsDAO();
		List<DepartmentsVO> departments = dao.getAllDepartments();
		
		for ( DepartmentsVO vo : departments ) {
			System.out.println(vo);
		}
	}
	
}
