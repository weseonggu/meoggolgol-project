package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.soldesk.meoggolgol.MeoggolgolProject.join.Member;

// 가입 저장을 위해 사용되는 인터페이스
public interface MRInterface {
	Member save(Member member);

    Optional<Member> findByIndex(int member_index);
    Optional<Member> findByName(String member_id);
    Optional<Member> findByPW(String member_pw);
    Optional<Member> findByNickname(String member_nickname);
    Optional<Member> findByBirthday(Date member_birthday);
    Optional<Member> findByPhoneNumber(String member_phoneNumber);
    Optional<Member> findByPhoneEmail(String member_email);

    List<Member> findAll(); // 모든 회원 리스트 반환
}
