package com.exam.myapp.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(path = "add.do", method = RequestMethod.GET)  //@GetMapping("/member/list.do") - 구버전(4.초기)은 지원x
	public String addform() {
		return "member/add";
	}
	
	@RequestMapping(path = "add.do", method = RequestMethod.POST)  //@GetMapping("/member/list.do") - 구버전(4.초기)은 지원x
	public String add(MemberVo vo) {
		int num = memberService.insert(vo);
		return "redirect:/member/list.do";
	}
	
	
}
