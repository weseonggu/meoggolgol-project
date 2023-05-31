package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.notice.paging.Pagination;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeRepository nr;
	
	// 공지사항 저장하기
	public void saveNotice(NoticeRequest noticeRequest, String writer, LocalDate regDate) {
        nr.insertNotice(noticeRequest, writer, regDate);
    }

	public ArrayList<NoticeResponse> getNoticeInfo(int page){
		Pagination pagination = new Pagination();
		int start = (pagination.getPage() - 1) * pagination.getPageSize();
		int size = pagination.getPageSize() * page;
		try {
			NoticeResponse notice = new NoticeResponse();
			ArrayList<NoticeResponse> noticelist = new ArrayList<>();
			for (int i = 0; i < nr.getNoticeInfo(start, size).size(); i++) {
				notice.setNotice_num(Long.parseLong(nr.getNoticeInfo(start,size).get(i).get("notice_num").toString()));
				notice.setWriter(nr.getNoticeInfo(start,size).get(i).get("WRITER").toString());
				notice.setTitle(nr.getNoticeInfo(start,size).get(i).get("TITLE").toString());
				notice.setContent(nr.getNoticeInfo(start,size).get(i).get("CONTENT").toString());
				notice.setReg_date(nr.getNoticeInfo(start,size).get(i).get("REG_DATE").toString().replace("T", " ").toString());
				noticelist.add(notice);
			}
			return noticelist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
