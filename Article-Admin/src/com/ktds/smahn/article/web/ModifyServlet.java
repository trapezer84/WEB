package com.ktds.smahn.article.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.Root;

/**
 * Servlet implementation class ModifyServlet
 */
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleBiz articleBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
        articleBiz = new ArticleBiz();
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
		// update를 하는 코드를 거의 정해져있다. 
		
		// 1. 어떠한 글을 수정할 것인지 정해야 한다.
		// 1-1. 수정하고자 하는 글의 ID를 파라미터로 받아와야 한다. 
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		
		// 2. 수정하고자 하는 글의 정보를 가져와야 한다. 
		ArticleVO article = articleBiz.showDetail(articleId);
		
		// 2-1. 수정하고자 하는 글의 작성자가 본인이 맞는지 확인한다. 
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		if (article.getMemberId().equals(member.getMemberId())) {
			
			// 글을 수정시 /n이 <br/>로 나오는 것을 수정하기 위한 코드
			String descript = article.getDescript();
			descript = descript.replace("<br/>", "\n");
			article.setDescript(descript);
			
			// 3. 수정하고자 하는 글의 정보를 JSP로 보내준다. 
			request.setAttribute("article", article);
			
			RequestDispatcher rd = request.getRequestDispatcher(
					"/WEB-INF/view/article/write.jsp");
			rd.forward(request, response);
		} 
		else {
			response.sendRedirect(Root.get(this) + "/list");
		}
		
		
		
		
	}

}
