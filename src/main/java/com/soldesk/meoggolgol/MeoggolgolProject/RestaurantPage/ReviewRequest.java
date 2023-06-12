package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 리뷰 등록 요청 클래스
public class ReviewRequest {

	private long rr_num;
	
	private String rr_mggname;
	
	private String rr_restaurantname;
	
	private int rr_score;
	
	private String rr_review;
	
	private String rr_writer;
	
}
