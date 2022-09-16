package com.exam.myapp.member;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
	// 회원목록
	public List<MemberVo> selectList();

	public int insert(MemberVo vo);

	public MemberVo select(MemberVo vo);

	public int update(MemberVo vo);

	public int delete(MemberVo vo);

}
