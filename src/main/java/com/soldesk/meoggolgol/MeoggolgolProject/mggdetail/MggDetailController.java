package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MggDetailController {
	private final MggDetailRepository mdr;
	
	@GetMapping("/mgg-detail")
	public String goMggDetail(@RequestParam double lo,@RequestParam double la, Model model) {
		System.out.println(lo);
		System.out.println(la);
		model.addAttribute("la", la);
		model.addAttribute("lo", lo);
		return "mgg-detail";
	}
	
}
