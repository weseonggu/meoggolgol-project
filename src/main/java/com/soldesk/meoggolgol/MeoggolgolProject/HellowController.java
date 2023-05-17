package com.soldesk.meoggolgol.MeoggolgolProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HellowController {
	@GetMapping("/")
	public String hellow() {
		return"mainpage";
	}
}
