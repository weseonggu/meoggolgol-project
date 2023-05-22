package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	
	private String member_id;
	private String member_pw;
	private String member_nickname;
	private Data member_birth;
	private String member_phoneNumber;
	private String member_email;
	
}
