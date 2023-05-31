package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.time.LocalDate;
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
	
	// 공지사항 등록 페이지 요청
	@GetMapping("/uploadnotice")
	private String goRegNotice(NoticeRequest noticerequest) {
		return "noticeUploadForm";
	}
	
	// 공지사항 등록 요청
	@PostMapping("/uploadnotice")
	public String regNotice(@Valid NoticeRequest notierequest, BindingResult bindingresult, HttpServletRequest httpservletrequest) {
		
		// input으로 받아온 제목+내용은 noticerequest에 넣고,
		// 작성자는 세션에 있는 멤버 닉네임 가져와서 넣고, 
		// 등록일자는 localDate() 사용해서 넣음
		
		// 세션 값 가져오기
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");

		// 세션 값 콘솔 확인
		
		// 세션 값 중 member_nickname 가져오기
		String writer = membersignin.getMember_nickname();
		

		// 등록일자는 현재 날짜로 설정
		LocalDate regDate = LocalDate.now();
		

		// NoticeService를 사용하여 공지사항 저장
        ns.saveNotice(writer, notierequest, regDate);
        
		
		return "redirect:/notice";
	}
	
	// 공지사항 목록 페이지 요청
	@GetMapping("/notice")
	private String goNotice(@RequestParam(defaultValue = "1") int page, Model model) {
		// 총 공지사항 수
		int totalListCnt = noReposi.getTotalCount();
		Pagination pagination = ns.paging(totalListCnt, page);
		int start;
		int size = pagination.getPageSize();
		if (page == 1) {
			start = pagination.getStartPage() - 1;
		}else {
			start = pagination.getStartPage() - 1 + pagination.getPageSize() * (page -1);
		}
		model.addAttribute("pagination", pagination);
		model.addAttribute("noticelist", noReposi.getNoticeInfo(start, size));
		return "noticeForm";
	}
	
	// 공지사항 상세 페이지 요청
	@GetMapping(value = "/notice/detail/{id}")
	private String goNoticedtail(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("noticeDetailList", noReposi.getNoticeDetailInfo(id));
		return "notice_detail";
	}
	
}
