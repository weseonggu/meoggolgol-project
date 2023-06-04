package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MggRestController {

	private final MggRestService mrs;
	
	@GetMapping("/restaurant-detail")
	public String goRestPage(@RequestParam double lo, @RequestParam double la, @RequestParam String imgUrl, @RequestParam String placeUrl, @RequestParam String placeName, @RequestParam String roadAddress) {
		mrs.info(placeUrl);
	    return "restaurant-detail";
	}
}