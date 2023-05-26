package com.soldesk.meoggolgol.MeoggolgolProject.introduction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AboutController {
	
	@GetMapping("/about")
	public String goAbout() {
		return "aboutForm";
	}
}
