package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 공지사항 댓글
public class NoticeComment {
	private int comment_num;
	private String writer;
	private String comment;
	private Date reg_date;
	private long notice_num;
}
