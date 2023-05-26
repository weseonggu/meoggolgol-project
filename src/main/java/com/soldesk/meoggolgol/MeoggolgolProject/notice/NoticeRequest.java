package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 공지사항 등록 요청 클래스 
public class NoticeRequest {
	
	// 게시글 생성 시점에는 필요 없지만 이후에 수정할 때 필요해서 일단 넣음
	private long notice_num;
	
	private String writer;
	
	@NotEmpty(message = "공지사항의 제목은 필수 입력 사항입니다.")
	private String title;

	@NotEmpty(message = "공지사항의 내용은 필수 입력 사항입니다.")
	private String content;
	
	private Date reg_date;

}
