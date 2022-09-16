package com.exam.myapp.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String addform() {
		return "member/add";
	}
	
	@RequestMapping(path = "add.do", method = RequestMethod.POST)  
	public String add(MemberVo vo) {
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
