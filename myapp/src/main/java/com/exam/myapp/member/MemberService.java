package com.exam.myapp.member;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


public interface MemberService {
	// 회원목록
	public List<MemberVo> selectList();

	public int insert(MemberVo vo); 
}
