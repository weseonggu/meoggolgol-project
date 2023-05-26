package com.soldesk.meoggolgol.MeoggolgolProject.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInCheckResult {
	private int errorCode;
	private MemberSignIn memberSignIn;
}
