package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MggRestRepository{
	
	private final JdbcTemplate jdbc;
	
	private static String SELECT_REST_REVIEW=
			"""
			select * from restaurantreview where rr_restaurantname = ? order by rr_num desc
			""";

	private static final String INSERT_REVIEW = "INSERT INTO restaurantreview (rr_mggname, rr_restaurantname, rr_score, rr_review, rr_writer, rr_date) VALUES (?, ?, ?, ?, ?, ?)";
	
	// 리뷰 세부 정보 가져오기
	public List<Map<String,Object>> getReviewInfo(String restName){
		return jdbc.queryForList(SELECT_REST_REVIEW, restName);
	}
	
	// 리뷰 저장하기
	public void saveReview(String rr_mggname, String rr_restaurantname, int rr_score, String rr_review, String rr_writer, LocalDateTime rr_date) {
        jdbc.update(INSERT_REVIEW, rr_mggname, rr_restaurantname, rr_score, rr_review, rr_writer, rr_date);
    }	
	
}