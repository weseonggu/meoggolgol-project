package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

	public void insertNotice(NoticeRequest noticerequest) {

	}
}
