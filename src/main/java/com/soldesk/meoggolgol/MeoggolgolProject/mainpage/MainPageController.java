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
		
		List<Map<String, Object>> questlist =  this.mpr.getAllFind();
		System.out.println(questlist);
		
//		Mgg mgg = new Mgg(null, null);
		ArrayList<Mgg> requestList = new ArrayList<>();
		
		for (int i = 0; i < mpr.getAllFind().size()/2; i++) {
//			mgg = new Mgg(mpr.getAllFind().get(i).get("FCLTY_NM") + "", mpr.getAllFind().get(i).get("SIGNGU_CD")+"");
//			System.out.println(mpr.getAllFind().get(i).get("FCLTY_NM").toString());
//			System.out.println(mpr.getAllFind().get(i).get("SIGNGU_CD").toString());
//			mgg.setFCLTY_NM(mpr.getAllFind().get(i).get("FCLTY_NM").toString());
//			mgg.setSIGNGU_CD(mpr.getAllFind().get(i).get("SIGNGU_CD").toString());
//			requestList.add(mgg);
			requestList.add(new Mgg(mpr.getAllFind().get(i).get("FCLTY_NM").toString(), mpr.getAllFind().get(i).get("SIGNGU_CD").toString()));
		}
		
		System.out.println(requestList);
//		System.out.println(requestList.get(0).getFCLTY_NM());
//		
		model.addAttribute("adf", requestList);
		
		return"index";
	}
}
