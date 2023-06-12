package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.time.LocalDateTime;
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
			(WRITER, TITLE, CONTENT, REG_DATE)
			values(?,?,?,?);
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

	// notice 수정
	private static String UPDATE_NOTICE=
			"""
				update notice set TITLE = ?, CONTENT = ?, REG_DATE = ?, WRITER = ? where notice_num = ?
			""";
	// notice 삭제
	private static String DELETE_NOTICE=
			"""
				delete from notice where WRITER = ? and notice_num = ?
			""";
	
	public void insertNotice(String writer, NoticeRequest noticerequest, LocalDateTime regDate) {
		jdbc.update(INSERT_NOTICE,
				writer,
				noticerequest.getTitle(),
				noticerequest.getContent(),
				regDate
				);
	}
	
	// 공지사항 수정
	public void updateNotice(String writer, NoticeRequest noticerequest, LocalDateTime regDate, Long notice_num) {
		jdbc.update(UPDATE_NOTICE, writer, noticerequest.getTitle(), noticerequest.getContent(), regDate, notice_num);
	}
	
	 // 공지사항 세부 정보
	 public List<Map<String,Object>> getNoticeDetailInfo(int notice_num){
	 	return jdbc.queryForList(SELECT_NOTICE_detail, notice_num);
	 }
	
	// 공지사항 세부 정보
	public Map<String, Object> getNoticeDetail(int notice_num) {
		return jdbc.queryForMap(SELECT_NOTICE_detail, notice_num);
	}
	
	// 공지사항 페이징
	public List<Map<String,Object>> getNoticeInfo(int start, int size){
		return jdbc.queryForList(SELECT_NOTICE_LIST, start, size);
	}
	
	// 총 공지사항 게시물 수
	public int getTotalCount(){
		return jdbc.queryForObject(NOTICE_TOTAL_COUNT, Integer.class);
	}
	
	// 공지사항 삭제
	public void deleteNotice(String writer,int num) {
		jdbc.update(DELETE_NOTICE, writer, num);
	}
}
