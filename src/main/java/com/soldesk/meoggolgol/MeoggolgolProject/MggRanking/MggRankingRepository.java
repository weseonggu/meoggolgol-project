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
			SELECT rr_mggname, weight, average_score, RANK() OVER (ORDER BY weight * average_score DESC) AS ranking
			FROM (
			    SELECT rr_mggname,
			        AVG(rr_score) AS average_score,
			        COUNT(*) AS record,
			        COUNT(*) / AVG(rr_score) AS weight
			    FROM (
			        SELECT rr_mggname, rr_restaurantname, rr_score,
			            AVG(rr_score) OVER (PARTITION BY rr_mggname, rr_restaurantname) AS avg_score
			        FROM restaurantreview
			    ) AS subquery
			    GROUP BY rr_mggname
			) AS subquery2
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
	
	private static String SELECT_MGG_LO_LA=
			"""
			select FCLTY_LO, FCLTY_LA from Alley_information where FCLTY_NM = ?
			""";
	
	public List<Map<String,Object>> getRanking(){
		return jdbc.queryForList(SELECT_RANKING);
	}

	public List<Map<String,Object>> getRestRanking(String mggName){
		return jdbc.queryForList(SELECT_MGG_REST_RANKING, mggName);
	}
	
	public List<Map<String,Object>> getLoLa(String mggName){
		return jdbc.queryForList(SELECT_MGG_LO_LA, mggName);
	}
}
