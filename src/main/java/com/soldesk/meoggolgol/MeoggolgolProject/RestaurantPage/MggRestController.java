package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MggRestController {

	private final MggRestService mrs;
	
	@GetMapping("/restaurant-detail")
	public String goRestPage(@RequestParam double lo, @RequestParam double la, @RequestParam String imgUrl, @RequestParam String placeUrl, @RequestParam String placeName, @RequestParam String roadAddress, Model model) {
		RestaurantInfo restaurantInfo = mrs.info(placeUrl);
		model.addAttribute("placeName", placeName);
		model.addAttribute("MapURL",restaurantInfo.getMapURL());
		model.addAttribute("operation",restaurantInfo.getOperation());
		model.addAttribute("businessHours",restaurantInfo.getBusinessHours());
		model.addAttribute("locationDetail",restaurantInfo.getLocationDetail());
		model.addAttribute("menu",restaurantInfo.getRestMenu());
		model.addAttribute("facilityInfo", restaurantInfo.getFacilityInfos());
	    return "restaurant-detail";
	}
}