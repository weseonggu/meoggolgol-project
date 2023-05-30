package com.soldesk.meoggolgol.MeoggolgolProject.notice.paging;

import org.openqa.selenium.print.PageSize;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Paginga {
	public final Pagination pn;
	
	
	public Pagination pagination(int totalList, int page) {
		// 현재 페이지
		pn.setPage(page);
		// 총 게시글 수
		pn.setTotalListCnt(totalList);
		
		// 총 페이지 수
		pn.setTotalPageCnt((int)Math.ceil(totalList * 1.0 / pn.getPageSize()));
		
		// 총 블럭 수
		pn.setTotalBlockCnt((int)Math.ceil(page*1.0)/pn.getBlockSize());
		
		// 현재 블럭
		pn.setBlock((int)Math.ceil(page*1.0)/pn.getBlockSize());
		
		// 블럭 시작 페이지
		pn.setStartPage((pn.getBlock()- 1) * pn.getBlockSize());
		
		// 블락 마지막 페이지
		pn.setEndPage(pn.getStartPage() + pn.getBlockSize() -1);
		
		// 블럭 마지막 페이지에 대한 validation
		if (pn.getEndPage() > pn.getTotalPageCnt()) {
			pn.setEndPage(pn.getTotalPageCnt());
		}
		
		// 이전 블럭( 클릭시, 이전 블럭 마지막 페이지)
		pn.setPrevBlock((pn.getBlock() * pn.getBlockSize()) - pn.getBlockSize());
		
		// 이전 블럭에 대한 validation
		if (pn.getPrevBlock() < 1) {
			pn.setPrevBlock(1);
		}
		
		// 다음 블럭(클리기, 다음 블럭 첫번째 페이지)
		pn.setNextBlock((pn.getBlock() * pn.getBlockSize()) + 1);
		
		// 다음 블럭에 대한 validation
		if (pn.getNextBlock() > pn.getTotalPageCnt()) {
			pn.setNextBlock(pn.getTotalPageCnt());
		}
        
		// db 접근 시작 index
		pn.setStartIndex((page - 1) * pn.getPageSize());
		
		return pn;
	}
}
