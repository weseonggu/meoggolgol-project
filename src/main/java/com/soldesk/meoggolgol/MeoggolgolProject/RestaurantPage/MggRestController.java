package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String goRestPage(@RequestParam String placeName, Model model) {

		model.addAttribute("reviewRequest", new ReviewRequest());
		model.addAttribute("review", mrr.getReviewInfo(placeName));
		
	    return "restaurant-detail";
	}

	// 음식점 리뷰 등록 요청
	@ResponseBody
	@PostMapping("/restaurant-detail/review")
	public String writeReview(@RequestBody ReviewRequest reviewRequest, HttpServletRequest httpservletrequest, Model model) {
		
		// 먹자 골목 이름 정보 + 식당 이름 + 리뷰 별점 + 리뷰 상세 내용 데이터 가져와서 reviewRequest에 넣고,
		// 리뷰 작성자는 세션에 있는 멤버 닉네임 가져와서 넣음

		// 먹자 골목 이름 콘솔 확인
		System.out.println(reviewRequest.getMggname());
		
		// 음식점 이름 콘솔 확인
		System.out.println(reviewRequest.getPlaceName());
		
		// 리뷰 상세 내용 콘솔 확인
		System.out.println(reviewRequest.getContent());
		
		// 리뷰 별점 콘솔 확인
		System.out.println(reviewRequest.getScore());
		
		// 세션에 있는 회원 정보 가져오기
		HttpSession session = httpservletrequest.getSession();
		MemberSignIn membersignin = (MemberSignIn) session.getAttribute("member_info");
	
		if (membersignin != null) {
			
			// 세션 값 중 member_nickname 가져와서 리뷰 작성자로 만들기
			String rr_writer = membersignin.getMember_nickname();
			
			// 리뷰 작성자 콘솔 확인
			System.out.println(rr_writer);
			
			// 등록일자는 현재 날짜로 설정
			LocalDateTime rr_date = LocalDateTime.now();
						
			// 등록일자 제대로 들어왔는지 콘솔 확인
			System.out.println(rr_date);
			
			// NoticeService를 사용하여 공지사항 저장
			mrs.saveNotice(reviewRequest, rr_writer, rr_date);
		}
		return "성공";
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