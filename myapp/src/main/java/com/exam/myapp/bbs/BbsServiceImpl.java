package com.exam.myapp.bbs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BbsServiceImpl implements BbsService {
	@Autowired
	private BbsDao bbsDao;
	
	@Override
	public List<BbsVo> selectList() {
		return bbsDao.selectList();
	}

	@Override
	public int insert(BbsVo vo) {
		return bbsDao.insert(vo);
	}

	@Override
	public BbsVo select(BbsVo vo) {
		return bbsDao.select(vo);
	}

	@Override
	public int update(BbsVo vo) {
		return bbsDao.update(vo);
	}

	@Override
	public int delete(BbsVo vo) {
		return bbsDao.delete(vo);
	}


}
