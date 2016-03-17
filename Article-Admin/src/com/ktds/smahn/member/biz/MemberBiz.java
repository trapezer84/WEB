package com.ktds.smahn.member.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.dao.MemberDAO;
import com.ktds.smahn.member.vo.MemberListVO;
import com.ktds.smahn.member.vo.MemberSearchVO;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.web.Paging;

public class MemberBiz {

	private MemberDAO memberDAO;

	public MemberBiz() {
		memberDAO = new MemberDAO();
	}

	/**
	 * 로그인 업무를 하는 메소드
	 * 
	 * @param member
	 * @param request
	 *            세션
	 * @return
	 */
	public boolean login(MemberVO member, HttpServletRequest request) {

		// 원래는 테스크 별로 메소드를 분리시키는게 맞다.
		// 1. 회원 정보를 가져온다.
		MemberVO loginMember = memberDAO.getMemberByIdAndPassword(member);

		// 2. 회원 정보가 존재한다면, 세션에 집어 넣는다.
		// 세션은 request가 가지고 있다.
		if (loginMember != null) {

			// 세션을 가지고 온다.
			HttpSession session = request.getSession();

			// 세션에 한 회원의 정보를 저장한다.
			// session.setAttribute("_KEY명_", VALUE);
			session.setAttribute("_MEMBER_", loginMember);
		}

		// 3. 널이 아니면(회원 정보가 있다면) true 결과를 보고한다.
		return loginMember != null;
	}

	public boolean addNewMember(HttpServletRequest request) {
		
		// 1. 등록정보를 받아오기 
		String memberId = request.getParameter("userId");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("userPassword");
		String email = request.getParameter("userEmail");

		if( memberId == null ) {
			return false;
		} 
		
		// 2. 하나의 memberVO 설정하기 
		MemberVO newMemberInfo = new MemberVO();
		newMemberInfo.setMemberId(memberId);
		newMemberInfo.setNickName(nickName);
		newMemberInfo.setPassword(password);
		newMemberInfo.setEmail(email);
		
		memberDAO.addNewMember(newMemberInfo);
		
		return true;
	}

	/**
	 * Member List를 받아오는 Biz 
	 * @return
	 */
	public MemberListVO getMemberList(MemberSearchVO searchVO) {
		
		// 1. 전체 게시글의 수
		int allMemberCount = memberDAO.getAllMemberCount();
		// 1-1. 기본으로 페이지를 만들어준다. 
		Paging paging = new Paging();
		paging.setTotalArticleCount(allMemberCount);
		// 1-2. page 가져올 때 계산 쉽게 하기 위해서 page number은 0부터 시작 
		paging.setPageNumber(searchVO.getPageNO()+"");
		
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		//전체 article 받아오기
		List<MemberVO> members = memberDAO.getAllMembers(searchVO);
		
		// 2. DAO로부터 받아온 결과를 출력
		MemberListVO memberList = new MemberListVO();
		memberList.setMemberList(members);
		// 2-2. 페이지 추가 
		memberList.setPaging(paging);
		
		return memberList;
		
	}

	/**
	 * Member Detail 정보를 받아오기 
	 * @param memberId
	 * @return
	 */
	public MemberVO showDetail(String memberId) {
		MemberVO member = memberDAO.getOneMemberByMemberId(memberId);
		return member;
	}

	public void deleteMembers(String[] deleteMemberIds, MemberVO member) {
		
		if( member.isAdmin() ) {
			for (String memberId : deleteMemberIds) {
				memberDAO.deleteMemberByMemberId(memberId);
			}
		}
		
	}

}
