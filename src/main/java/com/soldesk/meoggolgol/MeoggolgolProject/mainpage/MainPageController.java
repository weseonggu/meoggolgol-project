package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainPageController {
	private final MainPageRepository mpr;

	@GetMapping("/")
	public String hellow() {
		return "mainpage";
	}

}
