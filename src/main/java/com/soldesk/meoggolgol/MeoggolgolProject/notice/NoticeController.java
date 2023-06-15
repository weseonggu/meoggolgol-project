package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.soldesk.meoggolgol.MeoggolgolProject.Member.MemberSignIn;
import com.soldesk.meoggolgol.MeoggolgolProject.notice.paging.Pagination;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService ns;
	private final NoticeRepository noReposi;
	
	// 공지사항 등록 페이지 요청
	@GetMapping("/uploadnotice")
	private String goRegNotice(NoticeRequest noticerequest, HttpServletRequest httpservletreuquest) {
		
		// 보안 문제를 위해서 세션의 manager 값이 Y일 경우에만 공지사항 폼으로 넘겨주기
		
		// 세션 가져오기
		HttpSession session = httpservletreuquest.getSession();
		
		// 먹골골 회원이 아닌 경우 -> 다시 noticeForm으로 보내기
		if (session.getAttribute("member_info") == null) {
			return "redirect:/notice";
		}
		
		// 세션 값 집어넣기
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
		
		// 세션 값 콘솔 확인
		System.out.println(session.getAttribute("member_info"));
		
		// 먹골골 일반 회원일 경우 -> 다시 noticeForm으로 보내기
		if ("N".equals(membersignin.getManager())) {
			return "redirect:/notice";
		}
		
		// 먹골골 관리자일 경우 -> noticeUploadForm으로 보내기
		return "notice/noticeUploadForm";
	}
		
	// 공지사항 등록 요청
	@PostMapping("/uploadnotice")
	public String regNotice(@RequestBody NoticeRequest noticerequest, HttpServletRequest httpservletrequest) {

		// 받아온 제목 + 내용은 noticerequest에 넣고,
		// 작성자는 세션에 있는 멤버 닉네임 가져와서 넣고, 
		// 등록일자는 localDate() 사용해서 넣음
		
		// 세션 값 가져오기
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");

		// 세션 값 콘솔 확인
		System.out.println(membersignin);
		
		if (membersignin != null) {
			// 세션 값 중 member_nickname 가져오기
			String writer = membersignin.getMember_nickname();
			
			// 세션에 있던 member_nickname 제대로 들어왔는지 콘솔 확인
			System.out.println(writer);
			
			// 등록일자는 현재 날짜로 설정
			LocalDateTime regDate = LocalDateTime.now();
			
			// 등록일자 제대로 들어왔는지 콘솔 확인
			// System.out.println(regDate);
	        
			// NoticeService를 사용하여 공지사항 저장
			ns.saveNotice(writer, noticerequest, regDate);
			
			// 콘솔에 저장된 공지사항 출력        
			System.out.println("번호: " + noticerequest.getNotice_num());
			System.out.println("제목: " + noticerequest.getTitle());
			System.out.println("내용: " + noticerequest.getContent());
			System.out.println("작성자: " + writer);
			System.out.println("등록일자: " + regDate);
		}
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
		} else {
			start = pagination.getPageSize() * (page -1);
		}
		model.addAttribute("pagination", pagination);
		model.addAttribute("noticelist", noReposi.getNoticeInfo(start, size));
		return "notice/noticeForm";
	}
	
	// 공지사항 수정 페이지 요청
	@GetMapping(value = "/notice/detail/update/{id}")
	private String goUpdateNotice(NoticeRequest notierequest, Model model,@PathVariable("id") Integer id, HttpServletRequest httpservletreuquest) {
		// 세션 가져오기
		HttpSession session = httpservletreuquest.getSession();
				
		// 먹골골 회원이 아닌 경우 -> 다시 noticeForm으로 보내기
		if (session.getAttribute("member_info") == null) {
			return "redirect:/notice";
		}
		
		// 관리자 정보 가져오기
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
				
		// 세션 값 콘솔 확인
		System.out.println(session.getAttribute("member_info"));
				
		// 먹골골 일반 회원일 경우 -> 다시 notice로 보내기
		if ("N".equals(membersignin.getManager())) {
			return "notice/noticeForm";
		}
				
		// 먹골골 관리자일 경우 -> notice_update으로 보내기
		
		// 공지사항 제목 + 번호 가져오기
		model.addAttribute("id", id);
		model.addAttribute("title", ns.getNoticeTitle(id));
		
		// 제목 값 확인
		System.out.println(ns.getNoticeTitle(id));
		
		// 공지사항 내용 가져오기
		Map<String, Object> noticeDetail = noReposi.getNoticeDetail(id);
		if (noticeDetail != null) {
			
			// 공지사항 내용
	        String content = noticeDetail.get("CONTENT").toString();
	        
	        // 내용 값 확인
	        System.out.println(content);
	        
	        model.addAttribute("content", content);
	        
	        return "notice/notice_update";
	    } else {
	    	return "notice/noticeForm";
		}
	}

	// 공지사항 수정
	@PostMapping(value = "/notice/update/{id}")
	private String updateNotice(@RequestBody NoticeRequest noticerequest, HttpServletRequest httpservletrequest, @PathVariable("id") Integer id)throws Exception {
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
		// 세션 값 콘솔 확인
		System.out.println(membersignin);
		if (membersignin != null) {
			
			// 세션 값 중 member_nickname 가져오기
			String writer = membersignin.getMember_nickname();
			
			// 세션에 있던 member_nickname 제대로 들어왔는지 콘솔 확인
			System.out.println(writer);
			//System.out.println(ns.getNoticeWriter(id));
			
			// 수정일자는 현재 날짜로 설정
			LocalDateTime regDate = LocalDateTime.now();
			System.out.println(regDate);
			
			System.out.println(noticerequest);
			
			Map<String, Object> noticeDetail = noReposi.getNoticeDetail(id);
			Long notice_num = (Long) noticeDetail.get("notice_num");
		
			if ("Y".equals(membersignin.getManager())) {
				ns.updateNotice(noticerequest.getTitle(), noticerequest.getContent(), regDate, writer, notice_num);
			}
		}
		return "redirect:/notice/detail/"+id;
	}
	
	// notice 삭제
	@GetMapping(value = "/notice/detail/{id}/delete")
	private String deleteNotice(@PathVariable("id") Integer id, HttpServletRequest httpservletrequest)throws Exception {
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
		// 세션 값 콘솔 확인
		System.out.println(membersignin);
		// 세션 값 중 member_nickname 가져오기
		if (membersignin != null) {
			String writer = membersignin.getMember_nickname();
			// 세션에 있던 member_nickname 제대로 들어왔는지 콘솔 확인
			System.out.println(writer);
			if (writer.equals(ns.getNoticeWriter(id))) {
				ns.deleteNotice(writer, id);
			}
			return "redirect:/notice";
		} else {
			return "redirect:/notice/detail/"+id;
		}
	}

	// 공지사항 세부 정보 요청
	@GetMapping(value = "/notice/detail/{id}")
	private String goNoticeDetail(Model model, @PathVariable("id") Integer id) {
	    Map<String, Object> noticeDetail = noReposi.getNoticeDetail(id);
	    if (noticeDetail != null) {
	        String content = noticeDetail.get("CONTENT").toString();
	        noticeDetail.put("renderedContent", content);
	        model.addAttribute("noticeDetail", noticeDetail);
	        return "notice/notice_detail";
	    } else {
	        return "notice/noticeForm";
	    }
	}
}
