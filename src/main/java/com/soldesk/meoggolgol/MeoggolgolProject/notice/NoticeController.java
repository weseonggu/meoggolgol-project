package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soldesk.meoggolgol.MeoggolgolProject.notice.paging.Paging;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService ns;
	private final NoticeRepository noReposi;
	
	@GetMapping("/notice")
	private String goNotice(@RequestParam int page, Model model) {
		ArrayList<NoticeResponse> noticelist = ns.getNoticeInfo(page);
		Paging paging = ns.getPage();
		model.addAttribute("page", paging);
		model.addAttribute("noticelist", noticelist);
		return "noticeForm";
	}
	
	@GetMapping(value = "/notice/detail/{id}")
	private String goNoticedtail(Model model,@PathVariable("id") Integer id) {
		ArrayList<NoticeResponse> noticelist = ns.getNoticeInfo();
		model.addAttribute("noticelist", noticelist);
		return "notice_detail";
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
