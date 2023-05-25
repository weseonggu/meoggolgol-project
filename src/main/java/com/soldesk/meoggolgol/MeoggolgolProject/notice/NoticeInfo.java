package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeInfo {
	
	private String writer;
	
	@NotEmpty(message = "공지사항의 제목은 필수 입력 사항입니다.")
	private String title;

	@NotEmpty(message = "공지사항의 내용은 필수 입력 사항입니다.")
	private String content;
	
	private String reg_date;

}
