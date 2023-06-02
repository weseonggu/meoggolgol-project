package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.AssertFalse.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MggDetailRestAPI {
	
	private final MggDetailService mgs;
	
	@GetMapping("/restaurantCardImg")
	public ImgUrl crawlingIngUrl(@RequestParam String url, @RequestParam int id) {
		String imgUrl=mgs.searchImage(url);
		
		ImgUrl urlaa = new ImgUrl(id+"",imgUrl);
		System.out.println(urlaa);
		return urlaa;
	}
	
	
	@GetMapping("/restaurantInfo")
	public ArrayList<Restaurant> getRestaurantInfo(@RequestParam double lo, @RequestParam double la, int page){
		return mgs.searchRestaurants(la,lo,page,15);
	}
	
}
