package com.ktds.smahn.reply.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.reply.biz.ReplyBiz;
import com.ktds.smahn.reply.vo.ReplyVO;

/**
 * Servlet implementation class DoWriteReplyServlet
 */
public class DoWriteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ReplyBiz replyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoWriteReplyServlet() {
        super();
        replyBiz = new ReplyBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//form으로 받으면 doGet을 받지 못하게 해야한다. 
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘 못 된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//댓글의 정보를 get
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		int parentReplyId = Integer.parseInt(request.getParameter("parentReplyId"));
		//groupId = 0 이면 신규로 등록되는 댓글 
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		//orderNo = 0 이면 신규로 등록되는 댓글 
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		String reply = request.getParameter("descript");
		
		// replyVO 생성
		ReplyVO replyInfo = new ReplyVO();
		replyInfo.setArticleId(articleId);
		replyInfo.setDepth(depth);
		replyInfo.setParentReplyId(parentReplyId);
		replyInfo.setGroupId(groupId);
		replyInfo.setOrderNo(orderNo);
		replyInfo.setDescript(reply);

		// session으로 memebrId를 받아온다
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( member != null ) {
			// session에서 받아온 memebrId를 replyInfo에 저장 
			replyInfo.setMemberId(member.getMemberId());
		}
		
		replyBiz.addNewReplyDepthOne(replyInfo);
		response.sendRedirect("/detail?articleId="+articleId);
		
	}

}
