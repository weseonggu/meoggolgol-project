package com.soldesk.meoggolgol.MeoggolgolProject.join;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {
	private int member_index;
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_nickname;
	private Date member_birthday;
	private String member_phoneNumber;
	private String member_email;
}
