package com.ktds.smahn.history.vo;

public interface Description {

	// %s는 이자리에 문자가 들어올 것이다. 라는 의미 
	// %s를 문자열로 바꿔주는 것이 ... 배열임 
	public static final String VISIT_LOGIN_PAGE = "[%s]가 로그인 페이지에 접근했습니다.";
	public static final String LOGIN = "[%s] 님이 로그인 했습니다.";
	public static final String LOGIN_FAIL = "[%s]가 [%s]로 로그인을 시도했지만 실패했습니다.";
	public static final String ALREADY_LOGIN = "[%s]님이 이미 로그인되어, list페이지로 이동합니다.";
	
	public static final String LIST = "[%s]님이 목록보기 페이지에 접근했습니다.";
	public static final String LIST_PAGING = "[%s]님이 [%s]번째 페이지로 이동했습니다.";
	public static final String LIST_SEARCH = "[%s]님이 목록보기 페이지에서 [%s]로 [%s]를 검색했습니다.";
	
	public static final String DETAIL = "[%s]님이  [%s]번째 글을 읽었습니다.";
	public static final String DETAIL_DESCRIPTION = "제목: [%s] <br/>글쓴이: [%s] <br/>내용: [%s]<br/>";
}
