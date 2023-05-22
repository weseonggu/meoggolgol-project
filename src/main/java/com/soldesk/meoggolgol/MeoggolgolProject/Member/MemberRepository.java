package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.SQL;

import com.soldesk.meoggolgol.MeoggolgolProject.join.Member;

public class MemberRepository {
	
	@Autowired
	private DataSource dataSource;
	
	public void save(Member member) throws SQLException{
		String sql = "insert into Member_info(member_id,member_pw,member_name,member_nickname,member_birth,member_phoneNumber,member_email) values(?,?,?,?,?,?,?)";
		Connection connection = dataSource.getConnection();
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, member.getMember_id());
		ps.setString(2, member.getMember_pw());
		ps.setString(3, member.getMember_name());
		ps.setString(4, member.getMember_nickname());
		ps.setDate(5, member.getMember_birthday());
		ps.setString(6, member.getMember_phoneNumber());
		ps.setString(7, member.getMember_email());
	}



}
