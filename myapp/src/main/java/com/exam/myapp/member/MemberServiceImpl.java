package com.exam.myapp.member;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<MemberVo> selectList() {
		return memberDao.selectList();
	}

	@Override
	public int insert(MemberVo vo) {
		return memberDao.insert(vo);
	}

}
