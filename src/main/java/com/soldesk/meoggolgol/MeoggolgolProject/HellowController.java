package com.soldesk.meoggolgol.MeoggolgolProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HellowController {

	
	@GetMapping("/")
	public String hellow() {
		return"mainpage";
	}
}
