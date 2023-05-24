package com.soldesk.meoggolgol.MeoggolgolProject.Member;

public class LogInCheckResult {
	private int errorCode;
	private MemberSignIn memberSignIn;

	public LogInCheckResult(int errorCode, MemberSignIn memberSignIn) {
	   this.errorCode = errorCode;
	   this.memberSignIn = memberSignIn;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public MemberSignIn getMemberSignIn() {
		return memberSignIn;
	}
}
