package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.crawling.SeleniumSingleton;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MggRestService{
	
	private final SeleniumSingleton ce;
	private final MggRestRepository mReposi;
	
	public RestaurantInfo info(String url) {
		try {
			return ce.getDetailRest(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Menu> menu(String url) {
		try {
			return ce.getRestMenu(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 리뷰 저장하기
	public void saveNotice(ReviewRequest reviewrequest, String rr_writer, LocalDateTime rr_date) {
		String rr_mggname = reviewrequest.getMggname();
		String rr_restaurantname = reviewrequest.getPlaceName();
		int rr_score = reviewrequest.getScore();
		String rr_review = reviewrequest.getContent();
		
		mReposi.saveReview(rr_mggname, rr_restaurantname, rr_score, rr_review, rr_writer, rr_date);
	}
}