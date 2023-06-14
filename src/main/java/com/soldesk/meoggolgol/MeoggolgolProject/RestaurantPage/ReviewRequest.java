package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 리뷰 등록 요청 클래스
public class ReviewRequest {
	
	private String mggname;
	
	private String placeName;
	
	private int score;

	private String content;
	
}
