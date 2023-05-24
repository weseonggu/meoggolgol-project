 
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

	    LogInCheckResult checkResult = mService.check(submittedId, submittedPw);

	    if (checkResult.getErrorCode() == 1) {
	        bindingResult.rejectValue("member_id", "passwordInCorrect", "아이디를 다시 입력해주세요.");
	        return "signInForm";
	    }
	    if (checkResult.getErrorCode() == 2) {
	        bindingResult.rejectValue("member_pw", "passwordInCorrect", "비밀번호를 다시 입력해주세요.");
	        return "signInForm";
	    }

	    MemberSignIn memberSignIn = checkResult.getMemberSignIn();
	    // 인증 성공 시 세션에 데이터 저장
	    session.setAttribute("member_id", memberSignIn.getMember_id());
	    session.setAttribute("member_pw", memberSignIn.getMember_pw());
	    session.setAttribute("member_name", memberSignIn.getMember_name());
	    session.setAttribute("member_nickname", memberSignIn.getMember_nickname());
	    session.setAttribute("member_birth", memberSignIn.getMember_birth());
	    session.setAttribute("member_phoneNumber", memberSignIn.getMember_phoneNumber());
	    session.setAttribute("member_email", memberSignIn.getMember_email());

	    // 인증 성공하고 세션에 데이터까지 저장한 후 메인페이지 이동
	    System.out.println(session.getAttribute("member_name"));
	    return "redirect:/";
	}

	@PostMapping("/signout")
	public String logout(Member member, SessionStatus sessionStatus, HttpSession session) throws Exception {
		// 어노테이션이 관리하는 member_id 세션 삭제
		sessionStatus.setComplete();
		
		// httpsession으로 직접 저장한 속성들 삭제
		session.removeAttribute("member_pw");
		session.removeAttribute("member_name");
		session.removeAttribute("member_nickname");
		session.removeAttribute("member_birth");
		session.removeAttribute("member_phoneNumber");
		session.removeAttribute("member_email");
		return "redirect:/";
	}
}
 