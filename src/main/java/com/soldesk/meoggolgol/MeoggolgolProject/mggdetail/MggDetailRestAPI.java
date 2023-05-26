package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MggDetailRestAPI {
	
	private final MggDetailService mgs;
	
	@GetMapping("/restaurantCard")
	public ImgUrl crawlingIngUrl(@RequestParam String url) {
		String imgUrl=mgs.searchImage(url);
		System.out.println("api: "+imgUrl);
		ImgUrl urlaa = new ImgUrl(imgUrl);
		return urlaa;
	}
}
