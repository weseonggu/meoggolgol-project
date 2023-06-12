package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MggRestController {

	private final MggRestRepository mrr;
	private final MggRestService mrs;
	
	// 음식점 상세 페이지 요청
	@GetMapping("/restaurant-detail")
	public String goRestPage(@RequestParam double lo, @RequestParam double la, @RequestParam String imgUrl, @RequestParam String placeUrl, @RequestParam String placeName, @RequestParam String roadAddress, Model model) {
		RestaurantInfo restaurantInfo = mrs.info(placeUrl);
		model.addAttribute("placeName", placeName);
		model.addAttribute("MapURL",restaurantInfo.getMapURL());
		model.addAttribute("operation",restaurantInfo.getOperation());
		model.addAttribute("businessHours",restaurantInfo.getBusinessHours());
		model.addAttribute("locationDetail",restaurantInfo.getLocationDetail());
		model.addAttribute("menu",restaurantInfo.getRestMenu());
		model.addAttribute("facilityInfo", restaurantInfo.getFacilityInfos());
		model.addAttribute("reviewRequest", new ReviewRequest());
	    return "restaurant-detail";
	}
	
	// 음식점 리뷰 등록 요청
	@GetMapping("/restaurant-detail/review")
	public String regReview(ReviewRequest reviewrequest, BindingResult bindingresult, HttpServletRequest httpservletrequest) {

		if (bindingresult.hasErrors()) {
			return "notice/noticeUploadForm";
		}
		
		return "restaurant-detail";
	}
	
	//음식점 리뷰 목록 요청
	@PostMapping("/restaurant-detail")
	public String postReview(@Valid ReviewRequest reviewrequest) {
		return "restaurant-detail";
	}
	
}