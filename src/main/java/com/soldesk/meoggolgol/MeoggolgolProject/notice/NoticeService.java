package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeService nsr;
	
	// db에 있는 게시글 가져오기
	public NoticeResponse getNoticeInfo() {
		NoticeResponse noicelist = nsr.getNoticeInfo();
	}
	
}
