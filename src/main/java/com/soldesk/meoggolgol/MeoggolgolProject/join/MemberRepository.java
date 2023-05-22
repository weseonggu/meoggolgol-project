package com.soldesk.meoggolgol.MeoggolgolProject.join;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	private final JdbcTemplate jdbc;
	
	private static final String INSERT_MEMBER =
			"""
			INSERT INTO members (member_id, member_pw, member_name, member_nickname, member_birthday, member_phoneNumber, member_email)
			VALUES (?, ?, ?, ?, ?, ?, ?)
			""";
	
	public void registerMember(Member member) {
		jdbc.update(INSERT_MEMBER, member.getMember_id(), member.getMember_pw(), member.getMember_name(),
				member.getMember_nickname(), member.getMember_birthday(), member.getMember_phoneNumber(),
				member.getMember_email());
	}
}
