package com.ktds.leinalee.member.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.leinalee.member.dao.MemberDAO;
import com.ktds.leinalee.member.vo.MemberVO;


public class MemberBiz {

	private MemberDAO memberDAO;
	
	public MemberBiz() {
		memberDAO = new MemberDAO();
	}
	
	public boolean login(MemberVO member, HttpServletRequest request) {
		
		// 1. 회원 정보를 가져온다.
		MemberVO loginMember = memberDAO.getMemberByIdAndPassword(member);
		
		// 2. 회원 정보가 존재 하는지 확인한다.
		if ( loginMember != null ) {
			
			// 3. 존재한다면, 세션을 가져온다.
			HttpSession session = request.getSession();
			session.setAttribute("_MEMBER_", loginMember);
		}
		
		// 4. 결과를 보고한다.
		return loginMember != null;
	}
}
