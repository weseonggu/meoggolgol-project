package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soldesk.meoggolgol.MeoggolgolProject.join.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Transactional
	public int memberinsert(Member member) {
		memberRepository.save(member);
	}
	
}
