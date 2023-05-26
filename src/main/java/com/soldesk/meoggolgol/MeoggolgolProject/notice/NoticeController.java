package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private String goNotice(Model model) {
		
		List<NoticeResponse> noticelist = noReposi.getNoticeInfo();
		
		model.addAttribute("noticelist", noticelist);
		return "noticeForm";
	}
	
	
	
	@PostMapping("/uploadnotice")
	public String regNotice(@Valid NoticeRequest notieinfo, BindingResult bindingresult) {
		
		
		return "";
	}

}
