package com.soldesk.meoggolgol.MeoggolgolProject.qna;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QnARepository {
	private final JdbcTemplate jdbc;

	// 등록 요청 받은 QnA 저장하기
	private static String INSERT_QNA=
			"""
			insert into QandA
			(WRITER, TITLE, CONTENT, REG_DATE)
			values(?,?,?,?);
			""";
	// 댓글 저장
	private static String INSERT_QNA_REPLY=
			"""
			insert into QandA_reply(WRITER, COMMENT, REG_DATE, qa_num)
			values(?,?,?,?)
			""";
	// QnA 세부 정보
	private static String SELECT_QANDA_DETAIL=
			"""
			select * from QandA where qa_num = ?
			""";
	// QnA 전체 리스트
	private static String SELECT_QANDA_LIST=
			"""
			select * from QandA order by qa_num desc limit ?, ?
			""";
	// QnA 전체 갯수
	private static String QANDA_TOTAL_COUNT=
			"""
			select count(*) from QandA
			""";
	// QnA 댓글 저체 리스트
	private static String SELECT_QANDA_DETAIL_REPLY=
			"""
			select * from QandA_reply where qa_num = ? order by COMMENT desc
			""";
	// 댓글 세부 정보
	private static String SELECT_QANDA_REPLY=
			"""
			select * from QandA_reply where comment_num = ?
			""";
	
	// Q&A 수정
	private static String UPDATE_QANDA=
			"""
				update QandA set TITLE = ? , CONTENT = ?, REG_DATE = ? where WRITER = ? and qa_num = ?
			""";
	// Q&A 댓글 수정
	private static String UPDATE_QANDA_REPLY=
			"""
				update QandA_reply set COMMENT = ?, REG_DATE = ? where WRITER = ? and comment_num = ?
			""";
	
	// Q&A 삭제
	private static String DELETE_QANDA=
			"""
				delete from QandA where WRITER = ? and qa_num = ?
			""";
	// Q&A 댓글 삭제		
	private static String DELETE_QANDA_REPLY=
			"""
				delete from QandA_reply where WRITER = ? and comment_num = ?
			""";
	
	
	
	// Q&A 등록 
	public void insertQnA(String writer, QandA qandA, LocalDate regDate) {
		// 작성자랑 등록일자 제대로 있는지 콘솔 출력
		System.out.println(writer);
		System.out.println(regDate);
		jdbc.update(INSERT_QNA,
				writer,
				qandA.getTitle(),
				qandA.getContent(),
				regDate
				);
	}
	// qna 댓글 등록
	public void InsertQnAReply(String writer, String qnAReply, LocalDate regDate, long qa_num) {
		jdbc.update(INSERT_QNA_REPLY,
				writer,
				qnAReply,
				regDate,
				qa_num
				);
	}

	// Q&A 세부 정보
	public List<Map<String,Object>> getQNADetailInfo(int qna_num){
		return jdbc.queryForList(SELECT_QANDA_DETAIL, qna_num);
	}

	// Q&A 댓글 세부 정보
	public List<Map<String,Object>> getQNAReplyInfo(int comment_num){
		return jdbc.queryForList(SELECT_QANDA_REPLY, comment_num);
	}
	
	// Q&A 페이징
	public List<Map<String,Object>> getQNAInfo(int start, int size){
		return jdbc.queryForList(SELECT_QANDA_LIST, start, size);
	}
	// 총 Q&A 게시물 수
	public int getTotalCount(){
		return jdbc.queryForObject(QANDA_TOTAL_COUNT, Integer.class);
	}
	// Q&A 댓글
	public List<Map<String,Object>> getQNADetailReply(int qa_num){
		return jdbc.queryForList(SELECT_QANDA_DETAIL_REPLY, qa_num);
	}
	
	// Q&A 수정
	public void updateQna(String title, String content, LocalDate regDate, String writer, int num) {
		jdbc.update(UPDATE_QANDA, title, content, regDate, writer, num);
	}
	// Q&A 댓글 수정
	public void updateQnaReply(String comment, LocalDate regDate, String writer, int num) {
		jdbc.update(UPDATE_QANDA_REPLY, comment, regDate, writer, num);
	}
	
	// Q&A 삭제
	public void deleteQna(String writer,int num) {
		jdbc.update(DELETE_QANDA, writer, num);
	}
	// Q&A 댓글 삭제
	public void deleteQnaReply(String writer,int num) {
		jdbc.update(DELETE_QANDA_REPLY, writer, num);
	}
	
	
	
	
	
	
	
}
