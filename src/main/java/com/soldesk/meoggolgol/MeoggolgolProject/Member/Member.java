package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Size(min = 3, max = 20)
	@NotEmpty(message = "사용자ID는 필수항목입니다.")
	private String member_id;
	
	@Size(min = 5, max = 15)
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String member_pw;
	
	@NotEmpty(message = "비밀번호확인은 필수항목입니다.")
	private String member_pw_check;
	
	@NotEmpty(message = "가입자의 이름은 필수항목입니다.")
	@Size(min = 1, max = 10)
	private String member_name;
	
	@NotEmpty(message = "닉네임은 필수항목입니다.")
	@Size(min = 1, max = 10)
	private String member_nickname;
	
	private String member_birth;
	
	@NotEmpty(message = "휴대폰번호는 필수항목입니다.")
	private String member_phoneNumber;
	
	@NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
	private String member_email;
}
