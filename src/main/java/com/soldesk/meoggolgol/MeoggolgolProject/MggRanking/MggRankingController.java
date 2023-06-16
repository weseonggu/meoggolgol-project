package com.soldesk.meoggolgol.MeoggolgolProject.MggRanking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MggRankingController {
	private final MggRankingRepository mrr;
	
	// 음식점 상세 페이지 요청
		@GetMapping("/mggRanking")
		public String goRestPage(Model model) {

			model.addAttribute("rank", mrr.getRanking());
			
		    return "mggRanking";
		}
}
