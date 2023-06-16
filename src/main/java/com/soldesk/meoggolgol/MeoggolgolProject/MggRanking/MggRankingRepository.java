package com.soldesk.meoggolgol.MeoggolgolProject.MggRanking;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MggRankingRepository {
private final JdbcTemplate jdbc;
	
	private static String SELECT_RANKING=
			"""
			SELECT rr_mggname, average_score, RANK() OVER (ORDER BY average_score DESC) AS ranking
			FROM (
			    SELECT rr_mggname, AVG(rr_score) AS average_score
			    FROM restaurantreview
			    GROUP BY rr_mggname
			) AS subquery
			ORDER BY ranking ASC
			LIMIT 9;
			""";

	private static String SELECT_MGG_REST_RANKING=
			"""
			SELECT rr_mggname, rr_restaurantname, average_score, RANK() OVER (ORDER BY average_score DESC) AS ranking
			FROM (
			    select * , AVG(rr_score) AS average_score from restaurantreview where rr_mggname = ? GROUP BY rr_restaurantname
			) AS subquery
			ORDER BY ranking ASC
			LIMIT 3;
			""";
	
	public List<Map<String,Object>> getRanking(){
		return jdbc.queryForList(SELECT_RANKING);
	}

	public List<Map<String,Object>> getRestRanking(String mggName){
		return jdbc.queryForList(SELECT_MGG_REST_RANKING, mggName);
	}
}
