package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MggRestAPI {
	private final MggRestService mrs;
	
	@GetMapping("/restaurantDetailInfo")
	public RestaurantInfo getRestInfo(@RequestParam String placeUrl) {
		RestaurantInfo restaurantInfo = mrs.info(placeUrl);
	    return restaurantInfo;
	}

	@GetMapping("/restaurantMenuInfo")
	public ArrayList<Menu> getRestMenuInfo(@RequestParam String placeUrl) {
		return mrs.menu(placeUrl);
	}
}
