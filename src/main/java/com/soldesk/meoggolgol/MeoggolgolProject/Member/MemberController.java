package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("member_id")
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
	
	@GetMapping("/signin")
	public String goSignIn(Model model) {
	    model.addAttribute("membersignin", new MemberSignIn());
	    return "signInForm";
	}

	@PostMapping("/signin")
	public String signIn(@Valid MemberSignIn membersignin, BindingResult bindingResult) {
	    
	    if (bindingResult.hasErrors()) {
	        return "signInForm";
	    }
	    
	    if (reposi.check(membersignin.getMember_id(), membersignin.getMember_pw()) == null) {
	        bindingResult.rejectValue("member_id", "invalidCredentials", "아이디 또는 패스워드가 일치하지 않습니다.");
	        return "signInForm";
	    }
	    
	    // 로그인 성공
	    return "redirect:/";
	}

	


}
 