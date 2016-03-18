package com.ktds.smahn.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.history.vo.OperationHistoryVO;
import com.ktds.smahn.member.vo.MemberVO;

/**
 * 세션을 체크한다.
 * Servlet Filter implementation class SessionCheckFilter
 */
public class SessionCheckFilter implements Filter {
	
	private List<String> whiteList;
	
	private List<String> staticResourceList;

    /**
     * 필터에서 생성자는 의미 없다.
     * Default constructor. 
     */
    public SessionCheckFilter() {
    	
    	whiteList = new ArrayList<String>();
    	//  게스트가 바로 통과할 수 있는 uri
    	whiteList.add("/");
    	whiteList.add("/doLogin");
    	whiteList.add("/registerMember");
    	whiteList.add("/addNewMember");
    	// url 앞에 뜨는 아이콘
    	whiteList.add("/favicon.ico");
    	
    	//resource를 jsp에서 보여주려면 따로 list를 만들어주어야 한다.
    	staticResourceList = new ArrayList<String>();
    	// 						/resource --> resourceImage 도 그냥 지나감.
    	staticResourceList.add("/resource/");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		
		//ServletRequest, ServletResponse : 인터페이스
		//HttpServletRequest, HttpServletResponse : 클래스
		// 인터페이스와 클래스는 상속관계, 즉 부모와 자식 관계
		HttpServletRequest req = (HttpServletRequest) request;
		
		String uri = req.getRequestURI();
		System.out.println(uri);
		
		
		
		if( !whiteList.contains(uri) ){
			
			boolean isURIResourceFile = false;
			
			for( String staticResource : staticResourceList ){
				if( uri.startsWith(staticResource) ) {
					//사용자가 요청한 uri가 /resource로 시작한다면 그냥 지나가라.
					isURIResourceFile = true;
					break;
				}
			}
		
		
			if( !isURIResourceFile ){ 
			
				// whiteList에 uri가 없다면 세션을 체크해라.
				HttpSession session = req.getSession();
				
				// 데이터를 넣을때는 setAttribute, 가지고올때는 getAttribute를 쓴다. 
				MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
				// 세션에 null이 있으면 로그인페이지로 redirect한다. 
				if( member == null ){
					HttpServletResponse res = (HttpServletResponse) response; 
					res.sendRedirect("/");
					return;
				} else {
					OperationHistoryVO historyVO = new OperationHistoryVO(); 
					historyVO.setIp(request.getRemoteHost());
					historyVO.setMemberId(member.getMemberId());
					historyVO.setUrl(req.getRequestURI());
					
					request.setAttribute("OperationHistoryVO", historyVO);
				}
			}
		}
		


		// 여기까지 왔다는 의미는 로그인이 잘 되었다. 라는 의미
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * web.xml에 어떤 설정을 해 줄때만 가능하다. 
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
