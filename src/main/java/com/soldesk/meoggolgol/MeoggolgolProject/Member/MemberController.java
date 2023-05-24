 
package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("member_id")
public class MemberController {
	
	private final MemberRepository reposi;
	private final MemberService mService;
	
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
	public String goSignIn(MemberSignIn membersignin) {
	   
	    return "signInForm";
	}

	@PostMapping("/signin")
	public String signIn(@Valid MemberSignIn membersignin, BindingResult bindingResult, HttpSession session) {
	    if (bindingResult.hasErrors()) {
	        return "signInForm";
	    }
	    
	    String submittedId = membersignin.getMember_id();
	    String submittedPw = membersignin.getMember_pw();
	    
	    int checked = mService.check(submittedId, submittedPw);
	    System.out.println(checked);
	    
	    if (checked == 1) {
	    	bindingResult.rejectValue("member_id", "passwordInCorrect", "아이디 없");
	    	return "signInForm";
	    } 
	    if (checked == 2) {
	    	bindingResult.rejectValue("member_pw", "passwordInCorrect", "비번 틀림");
	    	return "signInForm";
	    }
	    
	    // 인증 성공 시 세션에 데이터 저장
	    session.setAttribute("member_id", membersignin.getMember_id());
	    session.setAttribute("member_id", membersignin.getMember_pw());
	    session.setAttribute("member_id", membersignin.getMember_name());
	    session.setAttribute("member_id", membersignin.getMember_nickname());
	    session.setAttribute("member_id", membersignin.getMember_birth());
	    session.setAttribute("member_id", membersignin.getMember_phoneNumber());
	    session.setAttribute("member_id", membersignin.getMember_email());
	    
	    // 인증 성공
	    return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(Member member, SessionStatus sessionStatus) throws Exception {
		sessionStatus.setComplete();
		// 명시적으로 써줘야 메인페이지 찾아감 
		// "redirect:/"는 못 찾더라구요
		return "redirect:/mainpage";
	}


}
 