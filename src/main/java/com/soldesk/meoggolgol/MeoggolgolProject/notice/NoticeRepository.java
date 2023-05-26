package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soldesk.meoggolgol.MeoggolgolProject.mggdetail.SelectMgg;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {

	private final JdbcTemplate jdbc;

	// 등록 요청 받은 공지사항 저장하기
	private static String INSERT_NOTICE=
			"""
			insert into notice
			(notice_num, WRITER, TITLE, CONTENT)
			values(?,?,?,?);
			""";

	private static String SELECT_NOTICE=
			"""
			select * from notice
			""";
	
	public void insertNotice(NoticeRequest noticerequest) {
		
	}
	
	public List<NoticeResponse> getNoticeInfo(){
		return jdbc.query(SELECT_NOTICE, (rs, rn) -> new NoticeResponse());
	}
}
