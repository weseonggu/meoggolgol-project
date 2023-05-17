package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MainPageRepository {
	private final JdbcTemplate jdbc;
	
	private static String SELECT_QUERY=
			"""
			select FCLTY_NM, SIGNGU_CD from Alley_information
			""";
	
	public List<Map<String, Object>> getAllFind(){
		return jdbc.queryForList(SELECT_QUERY);
	}
}
