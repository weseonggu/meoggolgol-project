package com.soldesk.meoggolgol.MeoggolgolProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 아무거나

@Controller
public class HellowController {
	@GetMapping("/")
//	@ResponseBody
	public String hellow() {
		return"index";
	}
}
