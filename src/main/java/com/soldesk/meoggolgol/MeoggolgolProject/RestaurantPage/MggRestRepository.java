package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

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
	
	
	// Q&A 댓글 세부 정보
	public List<Map<String,Object>> getReviewInfo(String restName){
		return jdbc.queryForList(SELECT_REST_REVIEW, restName);
	}
}