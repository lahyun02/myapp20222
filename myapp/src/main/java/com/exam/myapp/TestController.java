package com.exam.myapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//웹브라우저에서 http://localhost:8000/myapp/test.do 로 요청을 보내면 
//이클립스 콘솔에 "TEST!"라고 출력되도록 구현

//웹브라우저 화면에 "테스트 화면"이라고 출력되도록 구현
//=="테스트 화면"이라고 출력하는 JSP 파일을 만들고, 컨트롤러 실행 후 그 JSP 파일이 실행되도록 구현 

//웹브라우저에서 http://localhost:8000/myapp/test.do?x=3&y=4 로 요청을 보내면
//웹브라우저 화면에 "x + y = 7"이라고 출력되도록 변경 

// 컨트롤러 클래스를 만들경우, 클래스의 이름이 중요한 게 아니라 어느 패키지 안에 넣을 지가 중요하다!  
//@Component, 특별한 의미 없이 스프링에서 사용한다는 뜻 
//@Controller, @Service, @Repository, @Configuration, @ControllerAdvice 등- 스프링에 사용되면서 어떤 특정한 역할로 쓰임

//한글파라미터 인코딩 설정
//1. GET 방식 요청의 파라미터 인코딩 설정
//	서버(톰캣)의 설정파일(server.xml) 파일에 설정
//  HTTP를 담당하는 <Connector>에 URIEncoding="UTF-8" 추가 (톰캣 8 이상은 자동 한글 인코딩, 구 버전은 한글파라미터 인코딩 필요)
//2. POST 방식 요청의 파라미터 인코딩 설정
//	web.xml 파일에 톰캣 또는 스프링이 제공하는 인코딩필터를 등록


@Controller	//웹 요청이 왔을때 실행되는 코드를 담고 있는 클래스를 의미
public class TestController {
	
	//스프링(디스패쳐서블릿)이 컨트롤러 실행 후 뷰에 대한 정보를 받지 못하면,
	//DefaultRequestToViewNameTranslator를 사용하여 뷰이름을 자동생성 
	//현재 요청경로에서 컨텍스트패스까지 삭제하고,
	//맨 앞의 / 와 맨 뒤의 확장자를 제거하여 뷰이름으로 사용
	// 요청경로의 이름과 jsp파일 이름이 동일하면 생략이 가능하다는 의미
	
	//어떤 요청을 받았을때 실행되는 메소드라는 의미 (경로 value 또는 path 사용해 알려주기)
	@RequestMapping(path = "/test.do", method = RequestMethod.POST)  //경로만 지정하면 get, post 모두 실행됨 
//	public String test(@RequestParam("x") int x, int y, Map map) { 
//	public String test(int x, int y, Map map) { 
//	public String test(@ModelAttribute("testVo") TestVo vo, Map map) { 
	public String test(TestVo vo, Map map) { 
		//파라미터메서드이름과 동일한 매개변수 이름을 설정하면 바로 값을 받을수있음. 
		//@RequestParam("x")의 이름과 변수명이 같으면 @RequestParam("x") 생략가능. 다르면 써줘야 함.
		
		System.out.println("TEST!");
		
		//if(vo.getFood() != null) 또는 TestVo에서 list에 초기값을 준다.
		for (String f : vo.getFood()) {
			System.out.println(f);
		}
		
//		int sum = x + y;
		int sum = vo.getX() + vo.getY();
		
		//매개변수 안에 Map map 또는 Model model 또는 ModelMap modelMap
		map.put("result", sum);
//		map.put("testVo", vo);
//		model.addAttribute("result", sum); 
//		modelMap.addAttribute("result", sum); 
		
		// List 안에 있는 LicenseVo객체 - 자격증 정보 꺼내오기
		for (LicenseVo lvo : vo.getLicense()) {
			System.out.println( lvo.getLicenseName() );
			System.out.println( lvo.getLicenseOrg() );
			System.out.println( lvo.getLicenseDate() );
		}
		
		return "test";
	}
	
//	public String test(@ModelAttribute("ma") TestVo vo, ModelMap model) {
//	int s = vo.getX() + vo.getY();
//	model.addAttribute("sum", s);
//	System.out.println("TEST!");
//	return "test";
//}
	
}
