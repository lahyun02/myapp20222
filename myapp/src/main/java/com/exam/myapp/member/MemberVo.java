package com.exam.myapp.member;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVo {
	@NotNull  // 이 변수의 값이 null이 되어서는 안된다는 검증조건 
	@Size(max = 50, min = 1)     //글자수 제한-최소 1 글자이상, 최대 50글자 이하여야 한다는 검증조건. null은 아니지만 빈문자열""로 갈때
	private String memId;
	@NotNull @Size(max = 50, min = 1) 
	private String memPass;
	@NotNull @Size(max = 50, min = 1) 
	private String memName;
	//message - 에러났을 때 출력할 메시지 작성 
	@Digits(integer = 3, fraction = 0, message = "최대 3자리 정수까지 입력 가능" )  //정수 3자리, 소수점이하 0자리여야한다는 검증조건. 
	private int memPoint;

	//그외 @Min(0)- 최소값이 0이어야 한다는 검증조건- 여러가지 사용 가능 
	
}


