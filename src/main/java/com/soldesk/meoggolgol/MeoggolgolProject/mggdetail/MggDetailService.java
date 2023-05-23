package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class MggDetailService {
    private final MggDetailRepository mdr;

    public MggDetailService(MggDetailRepository mdr) {
        this.mdr = mdr;
    }

    public void searchRestaurants(double latitude, double longitude) {
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 응답 처리
            System.out.println(response.toString());// 콘솔 출력 test

            // 연결 종료
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}