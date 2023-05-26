package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeRepository noReposi;
	
	@GetMapping("/notice")
	private String goNotice() {
		return "noticeForm";
	}
	
	
	@GetMapping("/uploadnotice")
	private String goRegNotice(NoticeRequest noticerequest) {
		return "noticeUploadForm";

	}
	
	@PostMapping("/uploadnotice")
	public String regNotice(@Valid NoticeRequest notierequest, BindingResult bindingresult) {
		
		
		return "noticeUploadForm";
	}

}
