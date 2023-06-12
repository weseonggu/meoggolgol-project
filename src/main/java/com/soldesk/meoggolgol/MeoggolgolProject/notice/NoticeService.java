package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.notice.paging.Pagination;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeRepository nr;
	
	// 공지사항 저장하기
	public void saveNotice(String writer, NoticeRequest noticeRequest, LocalDateTime regDate) {
        nr.insertNotice(writer, noticeRequest, regDate);
    }
	// 공지사항 작성자 가져오기
	public String getNoticeWriter(int num) {
		NoticeResponse noticeResponse = new NoticeResponse();
		noticeResponse.setWriter(nr.getNoticeDetailInfo(num).get(0).get("WRITER").toString());
		return noticeResponse.getWriter();
	}
	// 공지사항 제목 가져오기
	public String getNoticeTitle(int num) {
		NoticeResponse noticeResponse = new NoticeResponse();
		noticeResponse.setTitle(nr.getNoticeDetailInfo(num).get(0).get("TITLE").toString());
		return noticeResponse.getTitle();
	}
	// 공지사항 내용 가져오기
	public String getNoticeContent(int num) {
		NoticeResponse noticeResponse = new NoticeResponse();
		noticeResponse.setContent(nr.getNoticeDetailInfo(num).get(0).get("CONTENT").toString());
		return noticeResponse.getContent();
	}
	// 공지사항 수정
	public void updateNotice(String writer, NoticeRequest noticerequest, LocalDateTime regDate, long notice_num) {
		
		
		nr.updateNotice(writer, noticerequest, regDate, notice_num);
	}
	// 공지사항 삭제
	public void deleteNotice(String writer, int num) {
		nr.deleteNotice(writer, num);
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