package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.crawling.Selenium;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MggRestService{
	
	private final Selenium ce;
	
	public void info(String url) {
		System.out.println(url);
		try {
//			System.out.println(ce.getMapURL(url));
//			System.out.println(ce.getBusinessHours(url));
//			ArrayList<Menu> menus = ce.getRestMenu(url);
//			System.out.println(menus);
			ce.getLocationDetail(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}