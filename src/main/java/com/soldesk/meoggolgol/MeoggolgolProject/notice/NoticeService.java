package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeRepository nr;
	
	// db에 있는 게시글 가져오기
	public List<NoticeResponse> getNoticeInfo() {
		List<NoticeResponse> noticelist = nr.getNoticeInfo();
		
		return noticelist;
	}
	
}
