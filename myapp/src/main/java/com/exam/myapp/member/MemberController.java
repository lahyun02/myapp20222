package com.exam.myapp.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/member/")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(path = "list.do", method = RequestMethod.GET)  //@GetMapping("/member/list.do") - 구버전(4.초기)은 지원x
	public String list(Model model) {
		List<MemberVo> list = memberService.selectList();
		model.addAttribute("memList", list);
		return "member/list";
//		return new InternalResourceView("/WEB-INF/views/member/list.jsp");
//		return new JstlView("/WEB-INF/views/member/list.jsp");
	}
	
	@RequestMapping(path = "add.do", method = RequestMethod.GET)  
	public String addform(MemberVo vo) {
		//스프링 form태그를 쓰면 MemberVo객체와 연결시키기 때문에 매개변수로써 필요함(없으면 에러) 
		//1. 주소창에 쳐서 get방식으로 add.do요청이 가면 이 메소드가 실행된다. 
		//2. add.jsp에서 form:태그를 MemberVo객체와 연결지어 사용하겠다고 하는데 MemberVo객체가 없으면 에러가 난다..!
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
	public String edit(MemberVo vo) {
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
	
	
}
