package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainPageController {
	private final MainPageRepository mpr;
	
	@GetMapping("/index")
	public String list(Model model) {
		
		List<Map<String, Object>> questlist =  this.mpr.getAllFind(41, "%고양시%");
//		System.out.println(questlist);
		
		ArrayList<Mgg> requestList = new ArrayList<>();
		
		for (int i = 0; i < questlist.size()/2; i++) {
//			requestList.add(new Mgg(mpr.getAllFind().get(i).get("FCLTY_NM").toString(), mpr.getAllFind().get(i).get("SIGNGU_CD").toString()));
			requestList.add(new Mgg(questlist.get(i).get("FCLTY_NM").toString(), questlist.get(i).get("SIGNGU_CD").toString()));
		}
		
		System.out.println(requestList);
		
		model.addAttribute("requestMgg", requestList);
		
		return"index";
	}
}
