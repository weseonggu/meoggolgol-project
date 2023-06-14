package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.soldesk.meoggolgol.MeoggolgolProject.Member.MemberSignIn;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
		model.addAttribute("placeName", placeName);		// --> 식당 이름
		model.addAttribute("MapURL",restaurantInfo.getMapURL());
		model.addAttribute("operation",restaurantInfo.getOperation());
		model.addAttribute("businessHours",restaurantInfo.getBusinessHours());
		model.addAttribute("locationDetail",restaurantInfo.getLocationDetail());
		model.addAttribute("menu",mrs.menu(placeUrl));
		model.addAttribute("facilityInfo", restaurantInfo.getFacilityInfos());

		model.addAttribute("reviewRequest", new ReviewRequest());
		model.addAttribute("review", mrr.getReviewInfo(placeName));
		

	    return "restaurant-detail";
	}

	// 음식점 리뷰 등록 요청
	@PostMapping("/restaurant-detail/review")
	public String writeReview(@RequestBody ReviewRequest reviewRequest, HttpServletRequest httpservletrequest, @RequestParam String placeUrl) {
		
		// 먹자 골목 이름 정보 + 식당 이름 정보 가져와서 reviewRequest에 넣고,
		// 받아온 제목 + 내용도 reviewRequest에 넣고,
		// 작성자는 세션에 있는 멤버 닉네임 가져와서 넣음
		RestaurantInfo restaurantInfo = mrs.info(placeUrl);
		
		// 리뷰 별점
		
		// 리뷰 상세 내용
		System.out.println(reviewRequest.getContent());
		
		// 세션에 있는 회원 정보 가져오기
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
		
		// 세션 값 콘솔 확인
		System.out.println(membersignin);
	
		if (membersignin != null) {
			
			// 리뷰 작성자
			String writer = membersignin.getMember_nickname();	// 세션 값 중 member_nickname 가져와서 리뷰 작성자로 만들기
			System.out.println(writer);
			
			// NoticeService를 사용하여 공지사항 저장
			// mrs.메소드
		}
		
		return "redirect:/restaurant-detail";
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
