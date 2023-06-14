package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MggRestController {

	private final MggRestRepository mrr;
	private final MggRestService mrs;
	
	// 음식점 상세 페이지 요청
	@GetMapping("/restaurant-detail")
	public String goRestPage(@RequestParam double lo, @RequestParam double la, @RequestParam String imgUrl, @RequestParam String placeUrl, @RequestParam String placeName, @RequestParam String roadAddress, Model model) {
		model.addAttribute("reviewRequest", new ReviewRequest());
		model.addAttribute("review", mrr.getReviewInfo(placeName));
	    return "restaurant-detail";
	}
	
	// 음식점 리뷰 등록 요청
	@PostMapping("/restaurant-detail/review")
	public String writeReview(@RequestBody ReviewRequest reviewRequest) {
//		@RequestBody ReviewRequest reviewrequest
		System.out.println(reviewRequest.getContent());
		return "성공";

	}



	
}