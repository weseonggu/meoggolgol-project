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
			select FCLTY_NM, SIGNGU_CD from Alley_information where SIGNGU_CD = ?
			""";
	
	private static String SELECT_GU=
			"""
			select name from sigungu_information where sido = ?
			""";
	
	private static String SELECT_MGG=
			"""
			select FCLTY_NM, SIGNGU_CD from Alley_information where SIGNGU_CD in (select code from sigungu_information  where name like ?)
			""";
	
	public List<Map<String, Object>> getAllFind(int a){
		return jdbc.queryForList(SELECT_QUERY, a);
	}
	
	public List<Map<String, Object>> getSidoFind(int a){
		return jdbc.queryForList(SELECT_GU, a);
	}
	
	public List<Map<String, Object>> getMggListFind(String a){
		return jdbc.queryForList(SELECT_MGG, a);
	}
	
}
