package com.soldesk.meoggolgol.MeoggolgolProject.qna;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.notice.paging.Pagination;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnAService {
	private final QnARepository qar;
	
	// qna 저장하기
	public void saveQNA(String writer, QandA qandA, LocalDate regDate) {
		qar.insertQnA(writer, qandA, regDate);
	}
	// qna 작성자 가져오기
	public String getQNAWriter(int num) {
		QandA qandA = new QandA();
		qandA.setWriter(qar.getQNADetailInfo(num).get(0).get("WRITER").toString());
		return qandA.getWriter();
	}
	// qna 제목 가져오기
	public String getQNATitle(int num) {
		QandA qandA = new QandA();
		qandA.setTitle(qar.getQNADetailInfo(num).get(0).get("TITLE").toString());
		return qandA.getTitle();
	}
	// qna 내용 가져오기
	public String getQNAContent(int num) {
		QandA qandA = new QandA();
		qandA.setContent(qar.getQNADetailInfo(num).get(0).get("CONTENT").toString());
		return qandA.getContent();
	}
	
	// qna 댓글 작성자 가져오기
	public String getQNAReplyWriter(int num) {
		QnAReply qnAReply = new QnAReply();
		qnAReply.setWriter(qar.getQNAReplyInfo(num).get(0).get("WRITER").toString());
		return qnAReply.getWriter();
	}
	// qna 댓글 내용 가져오기
	public String getQNAReplyComment(int num) {
		QnAReply qnAReply = new QnAReply();
		qnAReply.setComment(qar.getQNAReplyInfo(num).get(0).get("COMMENT").toString());
		return qnAReply.getComment();
	}
	
	// 댓글 저장
	public void saveQNAReply(String writer, String qnAReply, LocalDate regDate, long qa_num) {
		qar.InsertQnAReply(writer, qnAReply, regDate, qa_num);
	}
	
	// qna 수정
	public void updateQNA(String title, String content, LocalDate regDate, String writer, int num) {
		qar.updateQna(title, content, regDate, writer, num);
	}
	// 댓글 수정
	public void updateQNAReply(String comment, LocalDate regDate, String writer, int num) {
		qar.updateQnaReply(comment, regDate, writer, num);
	}
	
	// qna 삭제
	public void deleteQNA(String writer, int num) {
		qar.deleteQna(writer, num);
	}
	// 댓글 삭제
	public void deleteQNAReply(String writer, int num) {
		qar.deleteQnaReply(writer, num);
	}

	public Pagination paging(int totalListCnt, int page) {
		Pagination pagination = new Pagination();
		// 현재 페이지
		pagination.setPage(page);
		// 총 게시글 수
		pagination.setTotalListCnt(totalListCnt);
		// 6. 총 페이지 수
		pagination.setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0 / pagination.getPageSize()));
		// 7. 총 블럭 수
		pagination.setTotalBlockCnt((int) Math.ceil(pagination.getTotalPageCnt() * 1.0 / pagination.getBlockSize()));
		// 현재 블럭
		pagination.setBlock((int) Math.ceil((page * 1.0)/pagination.getBlockSize())); 
		// 블럭 시작 페이지
		pagination.setStartPage((pagination.getBlock() - 1) * pagination.getBlockSize() + 1);
		// 블럭 마지막 페이지
		pagination.setEndPage(pagination.getStartPage() + pagination.getBlockSize() - 1);
		// 블럭 마지막 페이지에 대한 확인
		if(pagination.getEndPage() > pagination.getTotalPageCnt()){pagination.setEndPage(pagination.getTotalPageCnt());}
		// 이전 블럭(클릭 시, 이전 블럭 마지막 페이지)
		pagination.setPrevBlock((pagination.getBlock() * pagination.getBlockSize()) - pagination.getBlockSize());
		// 이전 블럭에 대한 확인
		if(pagination.getPrevBlock() < 1) {pagination.setPrevBlock(1);}
		// 다음 블럭(클릭 시, 다음 블럭 첫번째 페이지)
		pagination.setNextBlock((pagination.getBlock() * pagination.getBlockSize()) + 1);
		// 다음 블럭에 대한 확인
		if(pagination.getNextBlock() > pagination.getTotalPageCnt()) {pagination.setNextBlock(pagination.getTotalPageCnt());}
		// DB 접근 시작 index **/
		pagination.setStartIndex((page-1) * pagination.getPageSize());
		return pagination;
	}
}