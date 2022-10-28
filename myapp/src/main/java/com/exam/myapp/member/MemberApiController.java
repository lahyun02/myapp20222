package com.exam.myapp.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.JstlView;

// 브라우저에서 
// http://localhost:8000/myapp/member/list.do 로 요청을 보내면, 
// MemberController 클래스의 list() 메서드가 실행되고, 
// 브라우저 화면에 "회원목록" 이라고 출력되도록 구현 (jsp에서)

@Controller
@RequestMapping("/api/member/")
public class MemberApiController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(path = "list.do", method = RequestMethod.GET)  //@GetMapping("/member/list.do") - 구버전(4.초기)은 지원x
	@ResponseBody  //반환값을 그대로 응답 내용으로 전송 
	public Map<String, Object> list() {
		List<MemberVo> list = memberService.selectList();
		Map<String, Object> model = new HashMap<String, Object>(); 
		model.put("memList", list);
		return model; 
//		return new InternalResourceView("/WEB-INF/views/member/list.jsp");
//		return new JstlView("/WEB-INF/views/member/list.jsp");
	}
	
	@RequestMapping(path = "add.do", method = RequestMethod.GET)  
	public String addform(MemberVo vo) {
		//스프링 form태그를 쓰면 MemberVo객체와 연결시키기 때문에 매개변수로써 필요함(없으면 에러) 
		//1. 주소창에 쳐서 get방식으로 add.do요청이 가면 이 메소드가 실행된다. 
		//2. add.jsp에서 form:태그를 MemberVo객체와 연결지어 사용하겠다고 하는데 MemberVo객체가 없으면 에러가 난다..!
		
		// @ModelAttribute("memberVO") MemberVo vo에서 @ModelAttribute("memberVO")를 생략하면, 타입 MemberVo에서 첫글자만 소문자로 자동변환 생성
		//-> 스프링 form태그 모델명 이름 이유
		return "member/add";
	}
	
	@RequestMapping(path = "add.do", method = RequestMethod.POST)  
	//@RequestMapping메서드의 매개변수에 @Valid를 적용하면, 해당 객체의 클래스에 지정한 검증 조건에 맞는지 검증 수행 
	//@Valid 매개변수 뒤에 BindingResult (또는 Errors) 타입의 매개변수를 추가하면, 검증결과를 받아서 사용 가능 //bindingResult에 오류결과가 담긴다. 여러 에러 결과를 꺼내올수있음.
	public String add(@Valid MemberVo vo, BindingResult bindingResult) {  
		// form:요소에서 submit했을때 데이터가 vo로 전송될때 vo값이 올바른 값인지 검증. 올바르지 않을 시 정보를 다시 add.jsp페이지로 돌려보냄.
		//보안상 중요한 정보는 클라이언트쪽에서 검증하는 걸로 충분하지 x, 서버(java쪽)에서 해야 한다. --> 검증 라이브러리 
		if(bindingResult.hasErrors()) {  //검증결과 에러가 있다면
//			System.out.println("검증 실패!"); 
			return "member/add"; //회원추가 jsp 화면을 다시 출력 
		}
		int num = memberService.insert(vo);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(path = "edit.do", method = RequestMethod.GET)
	public String editform(MemberVo vo, Model model) {
		
		MemberVo mvo = memberService.select(vo);
		model.addAttribute("memVo", mvo);
		
		return "member/edit";
	}
	
	@RequestMapping(path = "edit.do", method = RequestMethod.POST)  
	public String edit(@ModelAttribute("memVo") @Valid MemberVo vo, BindingResult bindingResult) {
		//오류가 있으면 return "member/edit"; 검증을 추가했기 때문에 위 editform메서드에서 모델객체에 저장한 mvo와 vo의 이름이 다르기 때문에 모델명 이름을 맞춰줘야한다. 
		if(bindingResult.hasFieldErrors("memName") || bindingResult.hasFieldErrors("memPoint")) {
			//특정필드에 오류가 있는지 검사 - 회원정보 변경시에는 이름과 포인트만 검증
			return "member/edit";
			
			// if(bindingResult.hasErrors()) 일 경우 MemboerVo에 검증 지정한 거 모두다 검증하지만,  
			// 회원정보 변경 페이지에서 비밀번호는 검사 안하게 만들어놨기 때문에 MemboerVo에서 비밀번호 검증 오류가 됨. (null이나 빈문자열)
		}
		
		int num = memberService.update(vo);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(path = "del.do", method = RequestMethod.GET)
	public String del(MemberVo vo) {
		int num = memberService.delete(vo);
		return "redirect:/member/list.do";
	}

	@RequestMapping(path = "duplicate.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> duplicate(MemberVo vo) { 
		
		MemberVo mvo = memberService.select(vo);
		
		Map<String,Object> map = new HashMap<String, Object>(); //보통 받을때는 인터페이스인 Map타입으로 받음.
		map.put("result", mvo==null);  // 사용가능한 경우 {result:true}, 불가능한 경우 {result:false}
		
//		return "map";
		return map;
		
//		int cnt = 0;
		
//		if( !("").equals(mvo.getMemId()) || mvo.getMemId() != null ) {
//		if( mvo != null ) {
//			cnt = 1;
//			//return "success";
//		} else 
//			//return "fail";
	}
	
	@RequestMapping(path = "check.do", method = RequestMethod.POST)
	@ResponseBody
	public String check(MemberVo vo) { 
		
		MemberVo mvo = memberService.select(vo);
		
		Map<String,Object> map = new HashMap<String, Object>(); //보통 받을때는 인터페이스인 Map타입으로 받음.
		map.put("result", mvo==null);  // 사용가능한 경우 {result:true}, 불가능한 경우 {result:false}
		
//		return "map";
		
//		int cnt = 0;
		
//		if( !("").equals(mvo.getMemId()) || mvo.getMemId() != null ) {
//		if( mvo != null ) {
//			cnt = 1;
//			//return "success";
//		} else 
//			//return "fail";
		return null;
	}
	
	@RequestMapping(path = "login.do", method = RequestMethod.GET)
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(path = "login.do", method = RequestMethod.POST)
	public String login(MemberVo vo, HttpSession session) {
		
		MemberVo memberVo = memberService.selectLogin(vo);
		
		if(memberVo == null) { //로그인 실패
			System.out.println("로그인실패");
			return "member/login";
		}
		//로그인 성공
		//로그인시, 사람마다 하나씩 갖고 있는 세션에 저장시킨다. 나중에 필요한 곳에서 loginUser라고 저장된 세션을 꺼내면 memberVo 데이터를 쓸 수 있다. 
		System.out.println("로그인 성공");
		session.setAttribute("loginUser", memberVo);
		
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(path = "logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 현재 세션에 기록되어 있는 정보를 지우는 것이기 떄문에 파라미터에 MemberVo객체는 필요없으므로 지우고, 세션객체는 지워야하기 때문에 파라미터에 세센을 넣는다.
		// 로그아웃 -> 세션에서  loginUser 이름으로 저장되어 있는 객체를 꺼냈을 때 아무것도 없어야 함.
		// 세션 지우기 3가지 방법 
//		session.setAttribute("loginUser", null); // 1. "loginUser"이름으로 저장되어있는 객체에 null 삽입
//		session.removeAttribute("loginUser"); // 2. "loginUser"이름으로 저장되어있는 객체를 없앤다. (메서드)
		session.invalidate(); // 3. 세션 객체 자체를 지운다. 세션에 저장되어 있는 loginUser뿐만 아니라, 세션 자체를 다 지워버림. 초기화 
		// - (유효하지 않은 세션이다라고 할 경우 톰캣이 세션을 다 지워버림. 그리고 새로 세션을 만든다.)
		return "redirect:/member/login.do";
	}
	
	
}
