package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.Selenium;

@Service
public class MggDetailService {
    private final MggDetailRepository mdr;

    public MggDetailService(MggDetailRepository mdr) {
        this.mdr = mdr;
    }

    public ArrayList<Restaurant> searchRestaurants(double latitude, double longitude) {
        String apiUrl = "https://dapi.kakao.com/v2/local/search/category.json";
        String apiKey = "770ee2bdd22b63d6a113a2cfef5259c1"; // REST API 키
        String categoryCode = "FD6"; // 카카오맵 식당 카테고리 코드

        try {
            // URL 생성
            String urlString = apiUrl + "?category_group_code=" + categoryCode +
                    "&y=" + latitude + "&x=" + longitude;
            URL url = new URL(urlString);

            // HTTP 연결 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "KakaoAK " + apiKey);

            // 응답 읽기
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String data = br.readLine();

			JSONParser jp = new JSONParser();
			JSONObject kakaoData = (JSONObject) jp.parse(data);
			JSONArray locs = (JSONArray) kakaoData.get("documents");
			JSONObject list = null;
            
			Restaurant rest = null;
			ArrayList<Restaurant> restlist = new ArrayList<>();
			
			// 응답 처리
			for (int i = 0; i < locs.size(); i++) {
				list = (JSONObject) locs.get(i);
				rest = new Restaurant(list.get("place_name")+"", list.get("road_address_name")+"", list.get("category_name")+"", list.get("phone")+"", list.get("place_url")+"", list.get("x")+"", list.get("y")+"");
				restlist.add(rest);
				searchImage(list.get("place_url")+"");
			}
			System.out.println(restlist);
			
            // 연결 종료
            connection.disconnect();
            
            return restlist;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void searchImage(String imageurl) {
    	StringBuffer sb = new StringBuffer();
    	sb.append(imageurl);
    	sb.insert(4, "s");
    	String urltest = sb.toString();
    	try {
    		Selenium sel = new Selenium();

    		String url = urltest;

    		sel.useDriver(url);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	
    }
}