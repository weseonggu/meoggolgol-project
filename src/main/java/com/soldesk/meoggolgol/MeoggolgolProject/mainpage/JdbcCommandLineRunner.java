package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JdbcCommandLineRunner implements CommandLineRunner {
	private final MainPageRepository mpr;
	@Override
	public void run(String... args) throws Exception {
//		System.out.println(mpr.getAllFind().size()/2);
//		System.out.println(mpr.getAllFind().get(4).get("FCLTY_NM"));
		Mgg mgg = null;
		ArrayList<Mgg> requestList = new ArrayList<>();
		
		for (int i = 0; i < mpr.getAllFind().size()/2; i++) {
			mgg = new Mgg(mpr.getAllFind().get(i).get("FCLTY_NM") + "", mpr.getAllFind().get(i).get("SIGNGU_CD")+"");
			requestList.add(mgg);
		}
//		System.out.println(requestList.get(0).getFCLTY_NM());
		Model model = null;
		model.addAttribute("requestionList", requestList);
	}
}
