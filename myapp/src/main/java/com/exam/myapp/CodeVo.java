package com.exam.myapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class CodeVo {
	
	private String id;
	private String title;
	
//  @AllArgsConstructor -> 생성자 자동 생성. 다음 코드와 같음.  
//   id, title을 파라미터값으로 받은 다음, 필드에 저장한 후 사용하는
//	public CodeVo(String id, String title) {
//		super();
//		this.id = id;
//		this.title = title;
//	}
	
	
	
}
