package com.soldesk.meoggolgol.MeoggolgolProject.qna;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.soldesk.meoggolgol.MeoggolgolProject.notice.paging.Pagination;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnAController {
	private final QnARepository qar;
	private final QnAService qas;
	
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
		model.addAttribute("qnaDetailList", qar.getQNADetailInfo(id));
		return "QandA/qna_detail";
	}
}
