package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.crawling.SeleniumSingleton;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MggRestService{
	
	private final SeleniumSingleton ce;
	
	public RestaurantInfo info(String url) {
		try {
			return ce.getDetailRest(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Menu> menu(String url) {
		try {
			return ce.getRestMenu(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}