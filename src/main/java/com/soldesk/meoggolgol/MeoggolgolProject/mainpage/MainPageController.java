package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainPageController {
	private final MainPageRepository mpr;
	
	@GetMapping("/index")
	public String list(Model model) {
		
		List<Map<String, Object>> questlist =  this.mpr.getAllFind(11230);
		List<Map<String, Object>> mgglist =  this.mpr.getMggListFind("%동대문구");
		
		
		ArrayList<Mgg> requestList = new ArrayList<>();
		ArrayList<Mgg> mggList2 = new ArrayList<>();
		
		for (int i = 0; i < questlist.size(); i++) {
			requestList.add(new Mgg(questlist.get(i).get("FCLTY_NM").toString(), questlist.get(i).get("SIGNGU_CD").toString()));
			mggList2.add(new Mgg(mgglist.get(i).get("FCLTY_NM").toString(), mgglist.get(i).get("SIGNGU_CD").toString()));
		}
		System.out.println(requestList);
		System.out.println("-------");
		System.out.println(mggList2);
		
		model.addAttribute("requestMgg", requestList);
		
		return"index";
	}
	
	@GetMapping("/")
	public String hellow(@RequestParam int sidoCode, Model model) {
		System.out.println(sidoCode);
		List<Map<String, Object>> sidolist =  this.mpr.getSidoFind(sidoCode);
		ArrayList<Sigungu> requestList = new ArrayList<>();
		
		for (int i = 0; i < sidolist.size(); i++) {
			requestList.add(new Sigungu(sidolist.get(i).get("name").toString()));
		}
		System.out.println(requestList);
		
		model.addAttribute("sigungu", requestList);
		
		return"mainpage";
	}
}
