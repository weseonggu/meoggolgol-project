package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeRepository nr;
	
	// db에 있는 게시글 가져오기
	public ArrayList<NoticeResponse> getNoticeInfo(){
		System.out.println(nr.getNoticeInfo());
		try {
			NoticeResponse notice = new NoticeResponse();
			ArrayList<NoticeResponse> noticelist = new ArrayList<>();
			for (int i = 0; i < nr.getNoticeInfo().size(); i++) {
				notice.setNotice_num(Long.parseLong(nr.getNoticeInfo().get(i).get("notice_num").toString()));
				notice.setWriter(nr.getNoticeInfo().get(i).get("WRITER").toString());
				notice.setTitle(nr.getNoticeInfo().get(i).get("TITLE").toString());
				notice.setContent(nr.getNoticeInfo().get(i).get("CONTENT").toString());
				notice.setReg_date(nr.getNoticeInfo().get(i).get("REG_DATE").toString().replace("T", " ").toString());
				noticelist.add(notice);
			}
			
			return noticelist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
