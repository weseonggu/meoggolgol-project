package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
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



	public MemberSignIn getInfoByID(String submittedId) {
	    String query = "SELECT * FROM Member_info WHERE member_id = ?";
	    try {
	       MemberSignIn memberSignIn = jdbc.queryForObject(query, (rs, rowNum) -> {
	        	
	            MemberSignIn membersignin = new MemberSignIn();
	            
	            membersignin.setMember_id(rs.getString("member_id"));
	            membersignin.setMember_pw(rs.getString("member_pw"));
	            membersignin.setMember_name(rs.getString("member_name"));
	            membersignin.setMember_nickname(rs.getString("member_nickname"));
	            
	            // 생일이 null인 경우 문자열 처리
	            String memberBirth = rs.getString("member_birth");
	            if (memberBirth == null) {
	                memberBirth = "등록된 생일 정보가 없습니다."; 
	            }
	            membersignin.setMember_birth(memberBirth);
	            
	            membersignin.setMember_phoneNumber(rs.getString("member_phoneNumber"));
	            membersignin.setMember_email(rs.getString("member_email"));
	            
	            return membersignin;
	            
	        }, submittedId);
	       return memberSignIn;
	       
	    } catch (EmptyResultDataAccessException e) {
	        return null; // 회원 아이디가 존재하지 않음
	    }
	}

}
