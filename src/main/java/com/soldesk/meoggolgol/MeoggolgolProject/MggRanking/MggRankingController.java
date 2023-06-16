package com.soldesk.meoggolgol.MeoggolgolProject.MggRanking;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

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
			ArrayList<Ranking> ranking = new ArrayList<>();
			for (int i = 0; i < mrr.getRanking().size(); i++) {
				ArrayList<Rest> rests = new ArrayList<>();
				Ranking rank = new Ranking();
				rank.setMgg_name((String) mrr.getRanking().get(i).get("rr_mggname"));
				rank.setAverage_score((BigDecimal) mrr.getRanking().get(i).get("average_score"));
				rank.setRanking((BigInteger) mrr.getRanking().get(i).get("ranking"));
				for (int j = 0; j < mrr.getRestRanking((String) mrr.getRanking().get(i).get("rr_mggname")).size(); j++) {
					Rest rest = new Rest();
					rest.setRr_restaurantname(mrr.getRestRanking((String) mrr.getRanking().get(i).get("rr_mggname")).get(j).get("rr_restaurantname").toString());
					rest.setAverage_score((BigDecimal)mrr.getRestRanking((String) mrr.getRanking().get(i).get("rr_mggname")).get(j).get("average_score"));
					rest.setRanking((BigInteger)mrr.getRestRanking((String) mrr.getRanking().get(i).get("rr_mggname")).get(j).get("ranking"));
					rests.add(rest);
				}
				rank.setRest(rests);
				ranking.add(rank);
			}
			System.out.println(ranking);
			model.addAttribute("ranking", ranking);
		    return "mggRanking";
		}
}
