package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberRepository reposi;
	
	@GetMapping("/join")
	public String goSingUp(Member member) {
		return "joinForm";
	}

	@PostMapping("/join")
	public String insertMember(@Valid Member member, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "joinForm";
		}
		if(!member.getMember_pw().equals(member.getMember_pw_check())) {
			bindingResult.rejectValue("member_pw_check", "passwordInCorrect", 
                    "2개의 패스워드가 일치하지 않습니다.");
			return "joinForm";
		}
		try {
			
			reposi.insertMember(member);
		}catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "joinForm";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "joinForm";
        }
		System.out.println(member);
		return "redirect:/";
	}

}
