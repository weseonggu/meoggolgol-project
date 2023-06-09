package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.crawling.Selenium;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MggRestService{
	
	private final Selenium ce;
	
	public RestaurantInfo info(String url) {
		System.out.println(url);
		try {
			return ce.getMapURL(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}