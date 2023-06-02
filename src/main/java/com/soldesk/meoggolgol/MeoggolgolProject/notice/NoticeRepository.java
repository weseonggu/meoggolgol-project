package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.time.LocalDate;
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

	public void insertNotice(String writer, NoticeRequest noticerequest, LocalDate regDate) {
		
		// 작성자랑 등록일자 제대로 있는지 콘솔 출력
		// System.out.println(writer);
		// System.out.println(regDate);
		
		jdbc.update(INSERT_NOTICE,
				writer,
				noticerequest.getTitle(),
				noticerequest.getContent(),
				regDate
		);
	}
	
	private static String SELECT_NOTICE_detail=
			"""
			select * from notice where notice_num = ?
			""";
	
	private static String SELECT_NOTICE_LIST=
			"""
			select * from notice order by REG_DATE limit ?, ?
			""";
	
	private static String NOTICE_TOTAL_COUNT=
			"""
			select count(*) from notice
			""";

	// 공지사항 세부 정보
	// public List<Map<String,Object>> getNoticeDetailInfo(int notice_num){
	// 	return jdbc.queryForList(SELECT_NOTICE_detail, notice_num);
	// }
	
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
}
