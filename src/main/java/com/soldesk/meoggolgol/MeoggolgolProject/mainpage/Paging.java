package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

public class Paging {
	private int currentPageNo;			//현재 페이지 번호
	private int recordCountPerPage;		//한 페이지당 게시되는 게시물 수
	private int pageSize;				//페이지 리스트에 게시되는 페이지 수
	private int totalRecordCount;		//전체 게시물 수
	private int realEnd;				//페이징 마지막 숫자
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	
	private int firstPageNoOnPageList;	//페이지 리스트의 첫 페이지 번호
	private int lastPageNoOnPageList;	//페이지 리스트의 마지막 페이지 번호
	private int firstRecordIndex; 		//페이징 sql의 조건절에 사용되는 시작 rownum
	
	private boolean xprev;		//이전버튼
	private boolean xnext;		//다음버튼
	

	public int getFirstPageNoOnPageList() {
		lastPageNoOnPageList = (int)(Math.ceil(currentPageNo/10.0)) * 10;
		
		firstPageNoOnPageList = lastPageNoOnPageList - 9;
		return firstPageNoOnPageList;
	}
	
	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
		this.firstPageNoOnPageList = firstPageNoOnPageList;
	}
	
	public int getLastPageNoOnPageList() {
		lastPageNoOnPageList = (int)(Math.ceil(getCurrentPageNo()/10.0)) * 10;
		
		int realEnd = (int)(Math.ceil((getTotalRecordCount() * 1.0) / getRecordCountPerPage()));
		
		
		if(realEnd < lastPageNoOnPageList) {
			lastPageNoOnPageList = realEnd;
		}
		
		return lastPageNoOnPageList;
	}
	
	public void setLastPageNoOnPageList(int lastPageNoOnPageList) {
		this.lastPageNoOnPageList = lastPageNoOnPageList;
	}
	
	public int getFirstRecordIndex() {
		firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}
	
	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}
	
	public boolean getXprev() {
		xprev= getFirstPageNoOnPageList() > 1;
		return xprev;
	}
	
	public void setXprev(boolean xprev) {
		this.xprev = xprev;
	}
	
	public boolean getXnext() {
		
		int realEnd = (int)(Math.ceil((getTotalRecordCount() * 1.0) / getRecordCountPerPage()));
		
		xnext = getLastPageNoOnPageList() < realEnd;
		return xnext;
	}
	
	public void setXnext(boolean xnext) {
		this.xnext = xnext;
	}
	
	public int getRealEnd() {
		
		realEnd = (int)(Math.ceil((getTotalRecordCount() * 1.0) / getRecordCountPerPage()));
		
		return realEnd;
	}
	public void setRealEnd(int realEnd) {
		this.realEnd = realEnd;
	}

}
