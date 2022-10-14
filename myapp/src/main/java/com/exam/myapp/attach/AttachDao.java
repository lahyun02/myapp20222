package com.exam.myapp.attach;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AttachDao {
	
//	public List<AttachVo> selectList();

	public int insert(AttachVo vo);

	public AttachVo select(AttachVo vo);

//	public int update(AttachVo vo);

//	public int delete(AttachVo vo);

}
