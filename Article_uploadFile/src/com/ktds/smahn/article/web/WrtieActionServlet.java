package com.ktds.smahn.article.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.CloneUtil;
import com.ktds.smahn.util.MultipartHttpServletRequest;
import com.ktds.smahn.util.MultipartHttpServletRequest.MultipartFile;

/**
 * Servlet implementation class WrtieActionServlet
 */
public class WrtieActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleBiz articleBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WrtieActionServlet() {
		super();
		articleBiz = new ArticleBiz();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);

		String title = multipartRequest.getParameter("title");
		String descript = multipartRequest.getParameter("descript");
		MultipartFile file = multipartRequest.getFile("file");
		File upFile = file.write("D:\\" + file.getFileName());

		// 1. 세션의 정보
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");

		// 4. 하나의 ArticleVO로 만들기
		ArticleVO newArticle = new ArticleVO();
		newArticle.setTitle(title);
		newArticle.setDescript(descript);
		newArticle.setMemberId(loginMember.getMemberId());

		articleBiz.addNewArticle(newArticle);
		
		// // 5. Biz에서 정보 처리
		// articleBiz.addNewArticle(request);
		// // 6. 보내주기
		response.sendRedirect("/list");

	}

}
