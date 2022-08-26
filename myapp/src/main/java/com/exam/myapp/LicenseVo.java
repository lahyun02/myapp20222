package com.exam.myapp;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

//자격증이 여러개일 경우, 자격증을 저장할 수 있는 Vo객체를 따로 만들어 받는다.

@Getter @Setter
public class LicenseVo {
	private String licenseName;
	private String licenseOrg;
	//날짜/시간문자열을 자바의 날짜/시간 타입으로 변환하는 방식을 지정
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date licenseDate;
	
	//숫자문자열을 숫자타입으로 변환하는 방식을 지정하는 경우
	//@NumberFormat(pattern = "#,###") 사용 가능
}
