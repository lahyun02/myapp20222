package com.exam.myapp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//스프링이 컨트럴러 실행 전 LoginInterceptor 를 실행하게끔 설정파일에서 등록해줘야 한다.
// 로그인 검사 
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	
	// 1. preHandle 컨트롤러 실행 전 
	//파라미터 인자(Object handler - 핸들러 실행체인, 다음에 실행할 인터셉터나 컨트롤러의 정보가 담겨있음. // request(요청객체)- 세션은 요청객체에서 꺼낸다 // response(응답객체) )
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러(의 @RequestMapping 메서드) 실행 전에 공통적으로 수행하는 작업들
		
		// 로그인했는지 검사한다.
		HttpSession session = request.getSession(); // 객체 가져오기
		MemberVo vo = (MemberVo) session.getAttribute("loginUser");  // 객체에서 loginUser라는 이름으로 저장된 정보 꺼내오기
		
		if (vo == null) {  //로그인하지 않은 경우
			// 컨트롤러가 아니기때문에 jps파일이름을 반환할 수 없기 때문에 응답객체에서 가져와서 redirect시킨다.
			response.sendRedirect(request.getContextPath() + "/member/login.do"); //로그인화면으로 리다이렉트  
			//경우에 따라sendError 메소드를 통해 에러메시지 출력 가능(접근권한이 없는데 로그인했다는 등)
			return false;  //컨트롤러 미실행
		}
		//로그인한 경우
		return true;  //컨트롤러 실행 
		//리턴값이 true, false인지에 따라 컨트롤러를 실행할지 결정 
	}
	
	// postHandle - 컨트롤러 실행 후, jsp 출력 전
	
	// afterCompletion - 컨트롤러 실행 후, jsp 출력 후
	
}
