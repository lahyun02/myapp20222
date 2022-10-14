package com.exam.myapp.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.JstlView;

import com.exam.myapp.attach.AttachVo;
import com.exam.myapp.member.MemberVo;

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
	public String add(@Valid BbsVo vo, BindingResult bindingResult, HttpSession session) {  
//		public String add(@Valid BbsVo vo, BindingResult bindingResult, HttpSession session, @SessionAttribute("loginUser") MemberVo mvo) {  
		// 스프링 4.3버전 이후 -> @SessionAttribute("loginUser") MemberVo mvo 이렇게 파라미터 안에서 작성 가능
//		if(bindingResult.hasErrors()) {  //검증결과 에러가 있다면
//			return "bbs/add"; //회원추가 jsp 화면을 다시 출력 
//		}
		
		//세션을 받은 후(파라미터), 세션에 저장되어있던 로그인한 사람의 아이디를 세션에서 가져와 작성자의 아이디로 저장
		MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
		vo.setBbsWriter( mvo.getMemId() );
		
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
	
	// 아이디 중복체크
//	@RequestMapping(path = "duplicate.do", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> duplicate(BbsVo vo) { 
//		
//		BbsVo mvo = bbsService.select(vo);
//		
//		Map<String,Object> map = new HashMap<String, Object>(); //보통 받을때는 인터페이스인 Map타입으로 받음.
//		map.put("result", mvo==null);  // 사용가능한 경우 {result:true}, 불가능한 경우 {result:false}
//		
//		return map;
//	}
	
	//컨트롤러 메서드에서 응답객체를 인자로 받고 반환타입이 void이면,
	//스프링은 해당 컨트롤러가 직접 응답을 처리한닫고 판단하여
	//스프링은 뷰에 대한 처리를 하지 않음 
	@RequestMapping(path = "down.do", method = RequestMethod.GET)
	public void down(AttachVo vo, HttpServletResponse resp) {   //파일을 받아서 응답하려면 HttpServletResponse를 인자로 받아야 함
		AttachVo avo = bbsService.selectAttach(vo); //첨부파일 정보 조회
		
		//지정한 첨부파일을 담고 있는 실제 서버 상의 파일 객체를 획득 
		File f = bbsService.getAttachFile(avo);
		
		//브라우저가 응답데이터를 다운로드하도록 응답 내용의 타입 정보를 설정 -> octet-stream : 브라우저에서 보여주지말고 바이너리정보니까 다운로드시켜라.
		resp.setContentType("application/octet-stream");
//		try {
//			//원래 파일의 타입대로 응답 내용의 타입 정보를 설정
//			resp.setContentType( Files.probeContentType( f.toPath() ) );
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		resp.setContentLengthLong(f.length()); //파일 크기를 응답 내용의 길이로 설정 
		
		try {
			//응답으로 전송하는 파일을 어떤 이름으로 저장해야하는지를 Content-Disposition 헤더로 설정
			//아스키코드 이외의 문자들은 URL 인코딩 후, +를 공백 문자로 변경 필요  
			String fname = URLEncoder.encode(avo.getAttOrgName(), "UTF-8").replace("+", "%20");  // 주소창에 한글이나 특수문자-> % 등의 인코드해서 넣음. 그 형태대로 인코딩해서 넣음. 
			resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fname);      //attachment - 다운로드 / inline - 보여지게함
			
			//첨부파일의 내용을 응답객체에 쓰기(전송)
			FileCopyUtils.copy( new FileInputStream(f), resp.getOutputStream() );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
