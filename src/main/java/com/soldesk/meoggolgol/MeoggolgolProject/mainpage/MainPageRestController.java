package com.soldesk.meoggolgol.MeoggolgolProject.mainpage;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainPageRestController {
	
	private final MainPageRepository mpr;
	
	@GetMapping("/sigungu")
	public List<Map<String, Object>> findSigunguData(@RequestParam int sidoCode) {
		return mpr.getSidoFind(sidoCode);
		
	}

}
