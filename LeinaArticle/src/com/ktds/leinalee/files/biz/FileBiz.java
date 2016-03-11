package com.ktds.leinalee.files.biz;

import java.io.File;

import com.ktds.leinalee.files.dao.FileDAO;
import com.ktds.leinalee.files.vo.FileVO;
import com.ktds.leinalee.util.MultipartHttpServletRequest.MultipartFile;

public class FileBiz {
	
	private FileDAO fileDAO;
	
	public FileBiz() {
		fileDAO = new FileDAO();
	}

	/**
	 * 파일 저장
	 * @param file
	 */
	public void addFile(MultipartFile file, int articleId) {
		
		FileVO newFile = new FileVO();
		
		String fileName = file.getFileName();
		File upFile = file.write("D:\\" + file.getFileName());
		
		newFile.setArticleId(articleId);
		newFile.setFileName(fileName);
		newFile.setFileLocation(upFile.getPath());
		
		fileDAO.addFile(newFile);
		
	}

	public void deleteFile(int articleId) {
		
		fileDAO.deleteFile(articleId);
	}

//	public FileVO getFileDetail(int articleId) {
//		fileDAO.getFileDetail(articleId);
//	}

}
