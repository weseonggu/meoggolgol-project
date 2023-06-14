package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	@GetMapping("/restaurant-detail/review")
	public ModelAndView writeReview(@RequestParam(required = false) Double lo, @RequestParam(required = false) Double la, @RequestParam String imgUrl, @RequestParam String placeUrl, @RequestParam String placeName, @RequestParam String roadAddress, Model model, @ModelAttribute("reviewRequest") ReviewRequest reviewRequest) {
	    if (lo == null || la == null) {
	        // lo와 la 값이 없는 경우에 대한 처리
	        // 예를 들어, 기본 값을 설정하거나 오류 메시지를 반환할 수 있습니다.
	    } else {
	        // lo와 la 값이 있는 경우에 대한 처리
	        System.out.println(reviewRequest.getContent());
	    }
	    ModelAndView modelAndView = new ModelAndView("성공");
	    return modelAndView;
	}


	
}
//	// 음식점 리뷰 등록 요청
//	@GetMapping("/restaurant-detail/review")
//	public String writeReview(@RequestBody ReviewRequest reviewRequest) {
////		@RequestBody ReviewRequest reviewrequest
//		System.out.println(reviewRequest.getContent());
//		return "성공";
//
//	}