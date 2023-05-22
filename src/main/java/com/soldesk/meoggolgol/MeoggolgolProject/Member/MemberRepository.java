package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	private final JdbcTemplate jdbc;
	
	private static String INSERT_MEMBER=
			"""
			insert into Member_info
			(member_id,member_pw,member_name,member_nickname,member_birth,member_phoneNumber,member_email) 
			values(?,?,?,?,?,?,?);
			""";
	
	public void insertMember(Member member) {
		jdbc.update(INSERT_MEMBER,
				member.getMember_id(),
				member.getMember_pw(),
				member.getMember_name(),
				member.getMember_nickname(),
				member.getMember_birth(),
				member.getMember_phoneNumber(),
				member.getMember_email()
				);
	}



}
