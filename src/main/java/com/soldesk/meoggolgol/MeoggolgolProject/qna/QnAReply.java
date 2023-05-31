package com.soldesk.meoggolgol.MeoggolgolProject.qna;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnAReply {
	private int comment_num;
	private String writer;
	private String comment;
	private Date reg_date;
	private long qa_num;
}
