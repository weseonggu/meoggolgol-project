package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeRepository noReposi;
	
	@GetMapping("/notice")
	private String goNotice() {
		return "noticeForm";
	}

}
