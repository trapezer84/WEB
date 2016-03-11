package com.ktds.smahn.article.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.file.biz.FileBiz;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.MultipartHttpServletRequest;
import com.ktds.smahn.util.MultipartHttpServletRequest.MultipartFile;

/**
 * Servlet implementation class WrtieActionServlet
 */
public class WrtieActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleBiz articleBiz;
	private FileBiz fileBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WrtieActionServlet() {
		super();
		articleBiz = new ArticleBiz();
		fileBiz = new FileBiz();
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

		// file을 저장할 ArticleVO(FileVO extends) 생성
		ArticleVO newArticle = new ArticleVO();
				
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);

		String title = multipartRequest.getParameter("title");
		String descript = multipartRequest.getParameter("descript");
		// file이 null이면 not upload , null이 아니면 upload! 		
		MultipartFile file = multipartRequest.getFile("file");
//		File upFile = file.write("D:\\" + file.getFileName());
		
		// 1. 세션의 정보
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");

		// 4. 하나의 ArticleVO로 만들기
		newArticle.setTitle(title);
		newArticle.setDescript(descript);
		newArticle.setMemberId(loginMember.getMemberId());

		int articleId = 0;
		articleId = articleBiz.addNewArticle(newArticle);
		
		if (file !=null ) {
			//upFile.getPath();
			// file을 DB에 저장
			fileBiz.addFile(file, articleId);
		}

		// 5. Biz에서 정보 처리
		// articleBiz.addNewArticle(request);
		// 6. 보내주기
		response.sendRedirect("/list");

	}

}
