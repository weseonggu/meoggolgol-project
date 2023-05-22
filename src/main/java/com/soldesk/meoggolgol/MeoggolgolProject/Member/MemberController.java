package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.soldesk.meoggolgol.MeoggolgolProject.join.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private MemberRepository memberRepository;
	
	public MemberController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원가입 페이지 진입
	@GetMapping("/joinForm")
	private String join() {
		System.out.println("회원가입페이지");
		return "joinForm";

	}
	
	@PostMapping("/join")
    public String join(Member member) { 
        System.out.println("user: " + member);
        Member memberEntity = memberRepository.save(member);
        System.out.println("userentity:  " + memberEntity);
        // redirect: mapping주소
        return "redirect:/loginForm"; 
    }

}
