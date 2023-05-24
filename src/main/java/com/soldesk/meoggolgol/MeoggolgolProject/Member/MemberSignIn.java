package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignIn {
	
	@NotEmpty(message = "사용자ID는 필수항목입니다.")
	private String member_id;

	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String member_pw;

}
