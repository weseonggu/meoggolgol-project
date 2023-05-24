
package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public int check(String submittedId, String submittedPw) {
        MemberSignIn memberSignIn = memberRepository.getInfoByID(submittedId);

        if (memberSignIn == null) {
            return 1; // 아이디 오류
        } else {
            if (!(memberSignIn.getMember_pw().equals(submittedPw))) {
            	return 2; // 비밀번호가 일치하지 않음
            } else {
                return 3; // 비밀번호가 일치
            }
        }
    }
}

