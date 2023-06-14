package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainPageController {

	@GetMapping("/")
	public String hellowMeoggolgol() {
		return "mainpage";
	}
	
	@GetMapping("/userguide")
	public String goUserGuide() {
		return "user-guide";
	}

}
