package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> origin/weseonggu

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainPageController {
<<<<<<< HEAD
	private final MainPageRepository mpr;
	
	@GetMapping("/index")
	public String list(Model model) {
		
		List<Map<String, Object>> questlist =  this.mpr.getAllFind(42210);
		
		ArrayList<Mgg> requestList = new ArrayList<>();
		
		for (int i = 0; i < questlist.size()/2; i++) {
			requestList.add(new Mgg(questlist.get(i).get("FCLTY_NM").toString(), questlist.get(i).get("SIGNGU_CD").toString()));
		}
		
		System.out.println(requestList);
		
		model.addAttribute("requestMgg", requestList);
		
		return"index";
	}
=======
	
	private final MainPageRepository mpr;
	
	@GetMapping("/")
	public String hellow(@RequestParam int sidoCode) {
		System.out.println(sidoCode);
		return"mainpage";
	}

>>>>>>> origin/weseonggu
}
