package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soldesk.meoggolgol.MeoggolgolProject.notice.paging.Pagination;

import com.soldesk.meoggolgol.MeoggolgolProject.Member.MemberSignIn;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService ns;
	private final NoticeRepository noReposi;
	
	@GetMapping("/notice")
	private String goNotice(@RequestParam(defaultValue = "1") int page, Model model) {
		// 총 공지사항 수
		int totalListCnt = noReposi.getTotalCount();
		ArrayList<NoticeResponse> noticelist = ns.getNoticeInfo(page);
		Pagination pagination = ns.paging(totalListCnt, page);
		model.addAttribute("pagination", pagination);
		model.addAttribute("noticelist", noticelist);
		return "noticeForm";
	}
	
	@GetMapping(value = "/notice/detail/{id}")
	private String goNoticedtail(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("noticeDetailList", noReposi.getNoticeDetailInfo(id));
		return "notice_detail";
	}
	
	@GetMapping("/uploadnotice")
	private String goRegNotice(NoticeRequest noticerequest) {
		return "noticeUploadForm";

	}
	
	@PostMapping("/uploadnotice")
	public String regNotice(@Valid NoticeRequest notierequest, BindingResult bindingresult, HttpServletRequest httpservletrequest) {
		
		// 세션 값 가져오기
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
		
		return "noticeForm";
	}
	
}
