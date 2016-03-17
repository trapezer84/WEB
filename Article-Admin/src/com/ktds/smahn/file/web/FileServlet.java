package com.ktds.smahn.file.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.smahn.file.biz.FileBiz;
import com.ktds.smahn.file.vo.FileVO;
import com.ktds.smahn.util.Root;

/**
 * Servlet implementation class FileServlet
 */
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FileBiz fileBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
        super();
        fileBiz = new FileBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		
//		FileVO file = fileBiz.showFile(articleId);
				
		// 6. 보내주기
		response.sendRedirect(Root.get(this) + "/detail?articleId="+articleId);
	}

}
