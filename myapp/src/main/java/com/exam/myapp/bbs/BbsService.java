package com.exam.myapp.bbs;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


public interface BbsService {
	// 회원목록
	public List<BbsVo> selectList();

	public int insert(BbsVo vo);

	public BbsVo select(BbsVo vo);

	public int update(BbsVo vo);

	public int delete(BbsVo vo);

}
