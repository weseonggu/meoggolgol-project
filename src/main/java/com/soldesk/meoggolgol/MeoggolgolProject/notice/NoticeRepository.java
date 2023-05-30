package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.List;
import java.util.Map;

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

	private static String SELECT_NOTICE=
			"""
			select * from notice
			""";
	
	private static String SELECT_NOTICE_LIST=
			"""
			select * from notice order by REG_DATE limit ?, ?
			""";
	
	private static String NOTICE_TOTAL_COUNT=
			"""
			select count(*) from notice
			""";
	
	public void insertNotice(NoticeRequest noticerequest) {
		
	}
	
	public List<Map<String,Object>> getNoticeInfo(){
		return jdbc.queryForList(SELECT_NOTICE);
	}
	
	public List<Map<String,Object>> getNoticeInfo(int start, int end){
		return jdbc.queryForList(SELECT_NOTICE_LIST, start, end);
	}
	
	public int getTotalCount(){
		return jdbc.queryForObject(NOTICE_TOTAL_COUNT, Integer.class);
	}
}