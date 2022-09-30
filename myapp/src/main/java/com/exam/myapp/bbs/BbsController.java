package com.exam.myapp.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
// http://localhost:8000/myapp/bbs/list.do 로 요청을 보내면, 
// MemberController 클래스의 list() 메서드가 실행되고, 
// 브라우저 화면에 "회원목록" 이라고 출력되도록 구현 (jsp에서)

@Controller
@RequestMapping("/bbs/")
public class BbsController {
	@Autowired
	private BbsService bbsService;
	
	@RequestMapping(path = "list.do", method = RequestMethod.GET)  //@GetMapping("/bbs/list.do") - 구버전(4.초기)은 지원x
	public String list(Model model) {
		List<BbsVo> list = bbsService.selectList();
		model.addAttribute("bbsList", list);
		return "bbs/list";
//		return new InternalResourceView("/WEB-INF/views/bbs/list.jsp");
//		return new JstlView("/WEB-INF/views/bbs/list.jsp");
	}
	
	@RequestMapping(path = "add.do", method = RequestMethod.GET)  
	public String addform(BbsVo vo) {
		return "bbs/add";
	}
	
	@RequestMapping(path = "add.do", method = RequestMethod.POST)  
	public String add(@Valid BbsVo vo, BindingResult bindingResult) {  
//		if(bindingResult.hasErrors()) {  //검증결과 에러가 있다면
//			return "bbs/add"; //회원추가 jsp 화면을 다시 출력 
//		}
		int num = bbsService.insert(vo);
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(path = "edit.do", method = RequestMethod.GET)
	public String editform(BbsVo vo, Model model) {
		
		BbsVo mvo = bbsService.select(vo);
		model.addAttribute("bbsVo", mvo);
		
		return "bbs/edit";
	}
	
	@RequestMapping(path = "edit.do", method = RequestMethod.POST)  
	public String edit(@ModelAttribute("bbsVo") @Valid BbsVo vo, BindingResult bindingResult) {
//		if(bindingResult.hasFieldErrors("bbsName") || bindingResult.hasFieldErrors("bbsPoint")) {
//			return "bbs/edit";
//		}
		
		int num = bbsService.update(vo);
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(path = "del.do", method = RequestMethod.GET)
	public String del(BbsVo vo) {
		int num = bbsService.delete(vo);
		return "redirect:/bbs/list.do";
	}

	@RequestMapping(path = "duplicate.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> duplicate(BbsVo vo) { 
		
		BbsVo mvo = bbsService.select(vo);
		
		Map<String,Object> map = new HashMap<String, Object>(); //보통 받을때는 인터페이스인 Map타입으로 받음.
		map.put("result", mvo==null);  // 사용가능한 경우 {result:true}, 불가능한 경우 {result:false}
		
		return map;
	}
	
	
	
}
