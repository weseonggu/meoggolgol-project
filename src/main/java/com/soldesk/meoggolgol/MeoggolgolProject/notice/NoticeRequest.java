package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.time.LocalDate;

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
	
	@NotEmpty(message = "공지사항의 제목을 입력해주세요.")
	@NotEmpty(message = "공지사항의 제목이 입력되지 않았습니다.")
	private String title;

	@NotEmpty(message = "공지사항의 상세 내용를 입력해주세요.")
	@NotEmpty(message = "작성된 내용이 없습니다.")
	private String content;
	
	private LocalDate reg_date;

}
