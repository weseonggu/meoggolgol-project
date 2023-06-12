package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soldesk.meoggolgol.MeoggolgolProject.Member.MemberSignIn;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

		// 받아온 제목+내용은 noticerequest에 넣고,
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
			LocalDate regDate = LocalDate.now();
					
			// 등록일자 제대로 들어왔는지 콘솔 확인
			// System.out.println(regDate);
			
			// 별점
			int score = reviewrequest.getRr_score();
			
			// 리뷰 텍스트
			String review = reviewrequest.getRr_review();
			
			mrr.saveReview(reviewrequest.getRr_mggname(), reviewrequest.getRr_restaurantname(), 
					score, reviewrequest.getRr_review(), reviewrequest.getRr_writer());

			// 콘솔에 저장된 공지사항 출력        
			System.out.println("번호: " + reviewrequest.getRr_score());
			System.out.println("내용: " + reviewrequest.getRr_review());
			System.out.println("작성자: " + reviewrequest.getRr_writer());
			}

			// 적절한 처리 결과를 반환하거나 다른 화면으로 리다이렉션하는 로직을 구현합니다.
			return "redirect:/restaurant-detail";
	}


	//음식점 리뷰 목록 요청
	@PostMapping("/restaurant-detail")
	public String postReview(@Valid ReviewRequest reviewrequest) {
		return "restaurant-detail";
	}
	
}