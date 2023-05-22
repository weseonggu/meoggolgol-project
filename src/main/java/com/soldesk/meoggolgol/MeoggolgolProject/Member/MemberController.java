package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 회원가입 페이지 진입
	@GetMapping("/joinForm")
	private String join() {
		System.out.println("회원가입페이지");
		return "joinForm";

	}

}
