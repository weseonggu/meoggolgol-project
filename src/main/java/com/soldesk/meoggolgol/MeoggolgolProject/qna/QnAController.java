package com.soldesk.meoggolgol.MeoggolgolProject.qna;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soldesk.meoggolgol.MeoggolgolProject.Member.MemberSignIn;
import com.soldesk.meoggolgol.MeoggolgolProject.notice.paging.Pagination;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnAController {
	private final QnARepository qar;
	private final QnAService qas;
	
	// 공지사항 등록 페이지 요청
	@GetMapping("/uploadQNA")
	private String goRegQNA(QandA qandA) {
		return "QandA/qnaUploadForm";
	}
	
	// 공지사항 등록 요청
	@PostMapping("/uploadQNA")
	public String regQNA(@Valid QandA qandA, BindingResult bindingresult, HttpServletRequest httpservletrequest) {
		// input으로 받아온 제목+내용은 noticerequest에 넣고,
		// 작성자는 세션에 있는 멤버 닉네임 가져와서 넣고, 
		// 등록일자는 localDate() 사용해서 넣음
		// 세션 값 가져오기
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
		// 세션 값 콘솔 확인
		System.out.println(membersignin);
		// 세션 값 중 member_nickname 가져오기
		String writer = membersignin.getMember_nickname();
		// 세션에 있던 member_nickname 제대로 들어왔는지 콘솔 확인
		System.out.println(writer);
		// 등록일자는 현재 날짜로 설정
		LocalDate regDate = LocalDate.now();
		// 등록일자 제대로 들어왔는지 콘솔 확인
		// System.out.println(regDate);
		// NoticeService를 사용하여 공지사항 저장
	    qas.saveQNA(writer, qandA, regDate);
	    // 콘솔에 저장된 공지사항 출력        
	    System.out.println("번호: " + qandA.getQa_num());
	    System.out.println("제목: " + qandA.getTitle());
	    System.out.println("내용: " + qandA.getContent());
	    System.out.println("작성자: " + writer);
	    System.out.println("등록일자: " + regDate);
			
		return "redirect:/qna";
	}
	
	@GetMapping("/qna")
	private String goQNA(@RequestParam(defaultValue = "1") int page, Model model) {
		// 총 공지사항 수
		int totalListCnt = qar.getTotalCount();
		Pagination pagination = qas.paging(totalListCnt, page);
		int start;
		int size = pagination.getPageSize();
		if (page == 1) {
			start = pagination.getStartPage() - 1;
		}else {
			start = pagination.getPageSize() * (page -1);
		}
		model.addAttribute("pagination", pagination);
		model.addAttribute("qnalist", qar.getQNAInfo(start, size));
		return "QandA/qnaForm";
	}
	
	@GetMapping(value = "/qna/detail/{id}")
	private String goQNAdtail(Model model,@PathVariable("id") Integer id) {
		System.out.println(id);
		model.addAttribute("id", id);
		model.addAttribute("qnaDetailList", qar.getQNADetailInfo(id));
		model.addAttribute("qnaReply", qar.getQNADetailReply(id));
		return "QandA/qna_detail";
	}
	
	// 댓글 작성
	@PostMapping("/qna/detail/{id}")
	private String insertComment(@PathVariable("id") Integer id, @RequestParam("content") String content,  HttpServletRequest httpservletrequest)throws Exception {
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
		// 세션 값 콘솔 확인
		System.out.println(membersignin);
		// 세션 값 중 member_nickname 가져오기
		String writer = membersignin.getMember_nickname();
		// 세션에 있던 member_nickname 제대로 들어왔는지 콘솔 확인
		System.out.println(writer);
		// 등록일자는 현재 날짜로 설정
		LocalDate regDate = LocalDate.now();
		qas.saveQNAReply(writer, content, regDate, id);
		return "redirect:/qna/detail/"+id;
	}
}
