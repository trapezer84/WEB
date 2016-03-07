package com.ktds.leinalee.filter;

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

import com.ktds.leinalee.member.vo.MemberVO;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
public class SessionCheckFilter implements Filter {

	private List<String> whiteList;
	
	private List<String> staticResourceList;
	
    /**
     * Default constructor. 
     */
    public SessionCheckFilter() {
    	whiteList = new ArrayList<String>();
    	whiteList.add("/"); //메인과 
    	whiteList.add("/doLogin"); //로그인하는 페이지는 접근이 가능해야하니까 whitelist에 포함  
    	whiteList.add("/favicon.ico"); 
    	
    	staticResourceList = new ArrayList<String>(); //위의 있는 page들 제외하고 filter를 거치치 말아야 하는 것들 
    	//만일 여기서 그냥 /resource를 적게 된다면 그냥 돌아가버림 /resource/~~를 해야지 정확하게 돌아감
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
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String uri = req.getRequestURI();
		System.out.println(uri);
		
		//uri를 확인한다. 
		if ( !whiteList.contains(uri) ) { //whitelist가 아닌것들을 check! 
			boolean isURIResourceFile = false;
			
			//contains를 못한다... 그래서 forEach문으로 
			for (String staticResource : staticResourceList) {
				//즉, /resource/~으로 시작한다면. 
				if ( uri.startsWith(staticResource) ) {
					isURIResourceFile = true;
					break;
				}
			}
			
			if( !isURIResourceFile ) { //whitelist에 포함되지 않지만 filter를 거치지 말아야 함 
				HttpSession session = req.getSession();
				
				//회원인지 확인하기 위해서 세션을 받아온다. 
				MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
				//세션이 null인지 확인한다. null이라면 비회원 
				if ( member == null ) {
					//goto login page(or index page)
					HttpServletResponse res = (HttpServletResponse) response;
					res.sendRedirect("/");
					return;
				}	
				
			}
		}
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig) : 어떤 설정을 해줄 때만 가능 (spring할 때 사용)  
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
