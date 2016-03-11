package com.ktds.smahn.file.biz;

import java.io.File;

import com.ktds.smahn.file.dao.FileDAO;
import com.ktds.smahn.file.vo.FileVO;
import com.ktds.smahn.util.MultipartHttpServletRequest.MultipartFile;

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

}
