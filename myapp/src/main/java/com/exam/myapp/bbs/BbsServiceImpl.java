package com.exam.myapp.bbs;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exam.myapp.attach.AttachDao;
import com.exam.myapp.attach.AttachVo;

@Service
public class BbsServiceImpl implements BbsService {
	@Autowired
	private BbsDao bbsDao;
	
	@Autowired
	private AttachDao attachDao;
	
	@Override
	public List<BbsVo> selectList() {
		return bbsDao.selectList();
	}

	@Override
	public int insert(BbsVo vo) {
		
		int num = bbsDao.insert(vo); // 첨부파일을 db에 insert하기 전, 게시글을 먼저 insert해야 글번호를 알 수 있다.
		
		for (MultipartFile mf : vo.getUpfileList()) {
			System.out.println(mf.getOriginalFilename());
			System.out.println(mf.getSize()); 
			//MultipartFile 객체 mf의 정보를 AttachVo 객체에 담은 다음
			AttachVo avo = new AttachVo(); 
			avo.setAttOrgName(mf.getOriginalFilename());  //첨부파일의 원래 이름
			String newName = UUID.randomUUID().toString(); // 중복확률이 매우 낮은 임의의 문자열 생성 - UUID 이용
			
			try {
				//mf의 파일 내용을 지정한 파일로 저장
				mf.transferTo( new File("D:/web/upload/"+newName) );   
				//첨부파일을 성공적으로 경로에 저장했을 경우에 새이름으로 저장
				avo.setAttNewName( newName );
				avo.setAttBbsNo( vo.getBbsNo() );  //selectKey로 vo의 bbsNo에 다음시퀀스값이 조회된 후 들어가있는 상태이므로 가능
				//AttachVo 객체의 정보를 attach 테이블에 insert. 
				attachDao.insert(avo); 
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}  
			//- 게시글에 insert한 후, 첨부파일에 insert해야 첨부파일에 insert할 때 몇 번글에 대한 첨부파일인지 저장가능.
		}
		
		return num;
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
