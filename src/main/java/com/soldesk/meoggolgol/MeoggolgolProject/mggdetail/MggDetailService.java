package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.soldesk.meoggolgol.MeoggolgolProject.crawling.SeleniumSingleton;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MggDetailService {

	private final MggDetailRepository mdr;
	private final SeleniumSingleton ce;
	
	// DB에 있는 먹자골목 정보 가져오기
	public SelectMgg getMggInfo(double lo, double la) {
        SelectMgg selectmgg = mdr.check(lo, la);
        
        if (selectmgg.getPARKNG_POSBL_AT().equals("유")) {
			selectmgg.setPARKNG_POSBL_AT("주차시설 있음");
		}
        else {
        	selectmgg.setPARKNG_POSBL_AT("주차시설 없음");
        }
        return selectmgg;
	}
	
	
	// 먹자골목 주변 시당정보 카카모api로 검색하기
    public ArrayList<Restaurant> searchRestaurants(double latitude, double longitude, int page, int pageSize) {
        String apiUrl = "https://dapi.kakao.com/v2/local/search/category.json";
        String apiKey = "770ee2bdd22b63d6a113a2cfef5259c1"; // REST API 키 성찬꺼
        String categoryCode = "FD6"; // 카카오맵 식당 카테고리 코드

        try {
            // URL 생성
            String urlString = apiUrl + "?category_group_code=" + categoryCode +
                    "&y=" + latitude + "&x=" + longitude +
                    "&page=" + page + "&size=" + pageSize;
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

            // JSON 파싱
            JSONParser jp = new JSONParser();
            JSONObject kakaoData = (JSONObject) jp.parse(data);
            JSONArray locs = (JSONArray) kakaoData.get("documents");
            JSONObject list = null;

            Restaurant rest = null;
            ArrayList<Restaurant> restlist = new ArrayList<>();

            // 응답 처리
            for (int i = 0; i < locs.size(); i++) {
                list = (JSONObject) locs.get(i);
                rest = new Restaurant(
                		i+(page*15),
                        list.get("place_name") + "",
                        list.get("road_address_name") + "",
                        list.get("category_name") + "",
                        list.get("phone") + "",
                        list.get("place_url") + "",
                        list.get("x") + "",
                        list.get("y") + "");
                restlist.add(rest);
            }
            // 연결 종료
            connection.disconnect();
            return restlist;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("넘어감");
            return null;
        }
        
    }
    
    // 이미지 클롤링
    public String searchImage(String imageurl) {
    	StringBuffer sb = new StringBuffer();
    	sb.append(imageurl);
    	sb.insert(4, "s");
    	String url = sb.toString();
    	try {
    		return ce.getImageURL(url);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return "error";
		}
    }
}