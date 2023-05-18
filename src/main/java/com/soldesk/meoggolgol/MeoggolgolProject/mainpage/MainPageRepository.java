package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MainPageRepository {
	
	@Autowired
	private final JdbcTemplate jdbc;
	
	private static String SELECT_QUERY=
			"""
			select FCLTY_NM, SIGNGU_CD from Alley_information where SIDO_CD  = ? and SIGNGU_CD = ?
			""";
	
	public List<Map<String, Object>> getAllFind(int a, int b){
		return jdbc.queryForList(SELECT_QUERY, a, b);
	}
}
