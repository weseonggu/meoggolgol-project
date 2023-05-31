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
	
	private static String INSERT_NOTICE_COMMENT=
			"""
			insert into notice_comment
			(comment_num, WRITER, COMMENT, REG_DATE, notice_num)
			values(?,?,?,?,?);
			""";

	private static String SELECT_NOTICE_detail=
			"""
			select * from notice where notice_num = ?
			""";
	
	private static String SELECT_NOTICE_LIST=
			"""
			select * from notice order by notice_num desc limit ?, ?
			""";
	
	private static String NOTICE_TOTAL_COUNT=
			"""
			select count(*) from notice
			""";
	
	public void insertNoticeComment(NoticeComment noticeComment) {
		jdbc.update(INSERT_NOTICE_COMMENT,
		noticeComment.getComment_num(),
		noticeComment.getWriter(),
		noticeComment.getComment(),
		noticeComment.getReg_date(),
		noticeComment.getNotice_num()
		);
	}
	// 공지사항 세부 정보
	public List<Map<String,Object>> getNoticeDetailInfo(int notice_num){
		return jdbc.queryForList(SELECT_NOTICE_detail, notice_num);
	}
	
	// 공지사항 페이징
	public List<Map<String,Object>> getNoticeInfo(int start, int size){
		return jdbc.queryForList(SELECT_NOTICE_LIST, start, size);
	}
	// 총 공지사항 게시물 수
	public int getTotalCount(){
		return jdbc.queryForObject(NOTICE_TOTAL_COUNT, Integer.class);
	}
}
