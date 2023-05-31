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
		return urlaa;
	}
	
//	@GetMapping("/restaurantCardImg")
//	public ArrayList<ImgUrl> crawlingIngUrl(
//			@RequestParam(value="id[]") ArrayList<String> id,
//			@RequestParam(value="urlList[]") ArrayList<String> urlList) {
//		ArrayList<ImgUrl> imgJson = new ArrayList<>();
//		for (int i = 0; i < id.size(); i++) {
//			System.out.println(id.get(i));
//			System.out.println(urlList.get(i));
//			System.out.println(mgs.searchImage(urlList.get(i)));
//			imgJson.add(new ImgUrl(id.get(i)+"", mgs.searchImage(urlList.get(i))));
//		}
////		System.out.println(imgJson);
//		return imgJson;
//
//	}
	
	@GetMapping("/restaurantInfo")
	public ArrayList<Restaurant> getRestaurantInfo(@RequestParam double lo, @RequestParam double la, int page){
		return mgs.searchRestaurants(la,lo,page,15);
	}
	
}
