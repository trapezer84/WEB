package com.ktds.leinalee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.vo.JobHistoryVO;

public class JobHistoryDAO {

	public List<JobHistoryVO> getAllJobHistory() {
		
		List<JobHistoryVO> jobHistories = new ArrayList<JobHistoryVO>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			String query = "SELECT * FROM JOB_HISTORY";
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		JobHistoryVO jobHistory = null;
		
		try {
			while( rs.next() ) {
				jobHistory = new JobHistoryVO();
				jobHistory.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				jobHistory.setStartDate(rs.getString("START_DATE"));
				jobHistory.setEndDate(rs.getString("END_DATE"));
				jobHistory.setJobId(rs.getString("JOB_ID"));
				jobHistory.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
				
				jobHistories.add(jobHistory);
			}
		} catch (SQLException e) {
		} finally {
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
		
		return jobHistories;
	}
}
