package com.exam.myapp.bbs;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exam.myapp.attach.AttachVo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BbsVo {

    private int bbsNo;
    private String bbsTitle;
    private String bbsContent;
    private String bbsWriter;
    private Date bbsRegDate;
    private int bbsCount;
    //폼 데이터에 포함된 파일은 MultipartFile 타입 변수로 받을 수 있다. 스프링이 첨부파일데이터를 MultipartFile 타입으로 만들어서 넣어주기 때문.
    private List<MultipartFile> upfileList; //파라미터 받기 위해 존재 
    
    // 여러개의 첨부파일 정보를 담는다 
    private List<AttachVo> attList;
    
    
}