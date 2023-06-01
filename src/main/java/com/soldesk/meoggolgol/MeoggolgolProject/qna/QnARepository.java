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

	// 등록 요청 받은 공지사항 저장하기
		private static String INSERT_QNA=
				"""
				insert into QandA
				(WRITER, TITLE, CONTENT, REG_DATE)
				values(?,?,?,?);
				""";
	
	private static String INSERT_QNA_REPLY=
			"""
			insert into QandA_reply(WRITER, COMMENT, REG_DATE, qa_num)
			values(?,?,?,?)
			""";
	
	private static String SELECT_QANDA_DETAIL=
			"""
			select * from QandA where qa_num = ?
			""";
	
	private static String SELECT_QANDA_LIST=
			"""
			select * from QandA order by qa_num desc limit ?, ?
			""";
	
	private static String QANDA_TOTAL_COUNT=
			"""
			select count(*) from QandA
			""";
	
	private static String SELECT_QANDA_DETAIL_REPLY=
			"""
			select * from QandA_reply where qa_num = ? order by REG_DATE desc
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
	
	// Q&A 페이징
	public List<Map<String,Object>> getQNAInfo(int start, int size){
		return jdbc.queryForList(SELECT_QANDA_LIST, start, size);
	}
	// 총 Q&A 게시물 수
	public int getTotalCount(){
		return jdbc.queryForObject(QANDA_TOTAL_COUNT, Integer.class);
	}
	// Q&A 댓글
		public List<Map<String,Object>> getQNADetailReply(int comment_num){
			return jdbc.queryForList(SELECT_QANDA_DETAIL_REPLY, comment_num);
		}
}
