 
package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import java.util.Enumeration;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	// 가입하러 가기 get요청
	@GetMapping("/join")
	public String goSingUp(Member member) {
		return "joinForm";
	}
	// 회원가입 post 요청, 유효성 검증, 비번 확인, 아이디 닉네임 중복 확인
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
	
	// 로그인 하러 가기 get요청
	@GetMapping("/signin")
	public String goSignIn(MemberSignIn membersignin) {
	    return "signInForm";
	}

	// 로그인 post요청 
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
	        bindingResult.rejectValue("member_pw", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
	        return "signInForm";
	    }

	    // 인증 성공 시 세션에 데이터 저장
	    session.setAttribute("member_info", checkResult.getMemberSignIn());
	    // 인증 성공하고 세션에 데이터까지 저장한 후 메인페이지 이동
	    
	 // 세션에 저장된 모든 속성과 값을 출력
	    Enumeration<String> attributeNames = session.getAttributeNames();
	    while (attributeNames.hasMoreElements()) {
	        String attributeName = attributeNames.nextElement();
	        Object attributeValue = session.getAttribute(attributeName);
	        System.out.println(attributeName + " : " + attributeValue);
	    }
	    
	    return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("/signout")
	public String logout(Member member, SessionStatus sessionStatus, HttpSession session) throws Exception {
		// 어노테이션이 관리하는 member_id 세션 삭제
		sessionStatus.setComplete();
		
		// httpsession으로 직접 저장한 속성 삭제
		session.removeAttribute("member_info");
		return "redirect:/";
	}
}
 