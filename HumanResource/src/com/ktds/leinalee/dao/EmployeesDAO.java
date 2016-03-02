package com.ktds.leinalee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.vo.EmployeesVO;

public class EmployeesDAO {

	//1. 테이블을 가져오는 메소드 작성
	public List<EmployeesVO> getAllEmployees() {
		
		
		//2. 드라이버
		try {
			//oracle driver을 로드한다는 의미... 동적 클래스 로딩! DCL 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		//Connection, PreparedStatement, ResultSet 변수 생성 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//4. 
		List<EmployeesVO> employees = new ArrayList<EmployeesVO>();
		
		try {
			//DB에 연결함 
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			//Query를 실행할 준비를 함 
			String query = " SELECT * FROM EMPLOYEES ";
			stmt = conn.prepareStatement(query);
			
			//Query의 실행 결과를 가져온다. 
			//Select 쿼리 일 때만 사용한다. 
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		EmployeesVO employee = null;
		try {
			//single row, 즉 row가 1개면 if multi-row 일 경우에는 while을 사용한다. 
			while ( rs.next() ) {
				employee = new EmployeesVO();
				employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				employee.setFirstName(rs.getString("FIRST_NAME"));
				employee.setLastName(rs.getString("LAST_NAME"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				//date타입은 오라클이 String타입이랑 비교 가능하도록 해준다.
				employee.setHireDate(rs.getString("HIRE_DATE"));
				employee.setSalary(rs.getInt("SALARY"));
				employee.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
				employee.setManagerId(rs.getInt("MANAGER_ID"));
				employee.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
				
				employees.add(employee);
			}
		} catch (SQLException e) {
			
		} finally {
			//close의 순서는 반대로 된다. 
			if ( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			
			if ( stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			
			if ( conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		return employees;
	}
	
//	public static void main(String[] args) {
//		EmployeesDAO dao = new EmployeesDAO();
//		
//		for ( EmployeesVO vo : employees ) {
//			System.out.println();
//		}
//	}
//	
	
}
