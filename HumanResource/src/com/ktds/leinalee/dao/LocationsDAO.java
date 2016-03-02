package com.ktds.leinalee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.vo.LocationsVO;

public class LocationsDAO {

	public List<LocationsVO> getAllLocations() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<LocationsVO> locations = new ArrayList<LocationsVO>();
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = "SELECT * FROM LOCATIONS";
			stmt = conn.prepareStatement(query);
			
			rs = stmt.executeQuery();			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		LocationsVO location = null;
		
		try {
			while(rs.next()) {
				location = new LocationsVO();
				location.setLocationId(rs.getInt("LOCATION_ID"));
				location.setStreetAddress(rs.getString("STREET_ADDRESS"));
				location.setPostalCode(rs.getString("POSTAL_CODE"));
				location.setCity(rs.getString("CITY"));
				location.setStateProvince(rs.getString("STATE_PROVINCE"));
				location.setCountryId(rs.getString("COUNTRY_ID"));
				
				locations.add(location);
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
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return locations;
	}
}
