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
public class MemberSignIn {
	
	@Size
	@NotEmpty(message = "사용자ID는 필수항목입니다.")
	private String member_id;

	@Size
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String member_pw;
	
	@Size
	private String member_name;
	
	@Size
	private String member_nickname;
	
	@Size
	private String member_birth;
	
	@Size
	private String member_phoneNumber;
	
	@Email
	@Size
	private String member_email;

}
