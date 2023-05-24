package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public LogInCheckResult check(String submittedId, String submittedPw) {
        MemberSignIn memberSignIn = memberRepository.getInfoByID(submittedId);

        if (memberSignIn == null) {
            return new LogInCheckResult(1, null); // 아이디 오류
        } else {
            if (!(memberSignIn.getMember_pw().equals(submittedPw))) {
                return new LogInCheckResult(2, null); // 비밀번호가 일치하지 않음
            } else {
                return new LogInCheckResult(0, memberSignIn); // 비밀번호가 일치, 정상
            }
        }
    }



}

