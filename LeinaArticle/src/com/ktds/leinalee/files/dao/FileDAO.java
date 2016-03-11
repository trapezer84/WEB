package com.ktds.leinalee.files.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.Const;
import com.ktds.leinalee.files.vo.FileVO;
import com.ktds.leinalee.util.xml.XML;

public class FileDAO {

	public void addFile(FileVO newFile) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/file/addFile/text()");
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, newFile.getArticleId());
			stmt.setString(2, newFile.getFileName());
			stmt.setString(3, newFile.getFileLocation());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	public void deleteFile(int articleId) {
		
		int insertCount = 0;
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/file/deleteFile/text()");
			stmt = conn.prepareStatement(query);
			System.out.println(query);

			stmt.setInt(1, articleId);

			insertCount = stmt.executeUpdate();

			if (insertCount > 0) {
				stmt.close();
				System.out.println("첨부파일 삭제 성공");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	/**
	 * 첨부 파일의 리스트 가져옴 
	 * @param articleId
	 * @return
	 */
	public List<FileVO> getFileListByArticle(int articleId) {
		
		int insertCount = 0;
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/file/getFileListByArticleId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleId);
			rs = stmt.executeQuery();

			List<FileVO> fileList = new ArrayList<FileVO>();
			FileVO file = null;
			while (rs.next()) {
				file = new FileVO();
				file.setFileId(rs.getInt("FILE_ID"));
				file.setFileName(rs.getString("FILE_NAME"));
				file.setFileLocation(rs.getString("FILE_LOCATION"));
				
				fileList.add(file);
			}
			
			// file이 null이라면 null
			if (file == null) {
				return null;
			}
			
			return fileList;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}		
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
