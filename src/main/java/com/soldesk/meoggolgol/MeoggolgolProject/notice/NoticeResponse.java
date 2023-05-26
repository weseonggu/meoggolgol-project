package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 등록된 공지사항 응답 클래스 (사용자에게 보여지는 데이터 처리)
public class NoticeResponse {
	
	private long notice_num;
	
	private String writer;
	
	private String title;
	
	private String content;
	
	private Date reg_date;

}
