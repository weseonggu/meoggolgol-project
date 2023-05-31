package com.soldesk.meoggolgol.MeoggolgolProject.qna;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QandA {
	private long qa_num;
	private String writer;
	private String title;
	private String content;
	private Date reg_date;
}
