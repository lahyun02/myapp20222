package com.exam.myapp.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVo {
	private String memId;
	private String memPass;
	private String memName;
	private int memPoint;
}
