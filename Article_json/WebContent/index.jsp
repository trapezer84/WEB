<%@page import="com.ktds.smahn.history.biz.OperationHistoryBiz"%>
<%@page import="com.ktds.smahn.history.vo.OperationHistoryVO"%>
<%@page import="com.ktds.smahn.member.vo.MemberVO"%>
<%@page import="com.ktds.smahn.history.vo.ActionCode"%>
<%@page import="com.ktds.smahn.history.vo.BuildDescription"%>
<%@page import="com.ktds.smahn.history.vo.Description"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
	OperationHistoryBiz biz = new OperationHistoryBiz();
	
	if (member != null) {
		OperationHistoryVO historyVO= new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setMemberId(member.getMemberId());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.LOGIN);
		historyVO.setDescription( BuildDescription.get	(
															Description.ALREADY_LOGIN, member.getMemberId()
														)
								);
		biz.addHistory(historyVO);
		
		response.sendRedirect("/list");
		return;
	}

	OperationHistoryVO historyVO= new OperationHistoryVO();
	historyVO.setIp(request.getRemoteHost());
	historyVO.setMemberId("");
	historyVO.setUrl(request.getRequestURI());
	historyVO.setActionCode(ActionCode.LOGIN);
	historyVO.setDescription( BuildDescription.get	(
														Description.VISIT_LOGIN_PAGE, request.getRemoteHost()
													)
							);
	biz.addHistory(historyVO);
										
	
 %>
     
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/login.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>