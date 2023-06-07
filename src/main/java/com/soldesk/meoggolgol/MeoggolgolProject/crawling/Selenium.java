package com.soldesk.meoggolgol.MeoggolgolProject.crawling;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage.FacilityInfo;
import com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage.Menu;
import com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage.RestaurantInfo;

@Component
@Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Selenium {
	private WebDriver driver;


	public Selenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

	        //driver = new ChromeDriver();
	        ChromeOptions options = new ChromeOptions();
	        options.setHeadless(true);
	        options.addArguments("--lang=ko");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--disable-gpu");
	        options.addArguments("--remote-allow-origins=*");
	        options.addArguments("--disable-popup-blocking");       //팝업안띄움
	        options.addArguments("headless");                       //브라우저 안띄움
	        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
	        options.setCapability("ignoreProtectedModeSettings", true);
	        driver = new ChromeDriver(options);
	}

	public String getImageURL(String url) throws Exception {
	    Duration duration = Duration.ofSeconds(10);

	    if (driver == null) {
	        System.out.println("드라이버 없음");
	        throw new IllegalStateException("Driver is not initialized. Call initializeDriver() first.");
	    }
	    try {
	        driver.get(url);// 드라이버에 주수 초기화
	        // 사이트가 특정 정보까지 로딩 될기를 최대 10초 기다림
	        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
	        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present")));
	        WebElement tag = driver.findElement(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present"));
	        String[] property = tag.getAttribute("style").split(" ");
	        return property[1].substring(5, (property[1].length() - 3));
	    } finally {
	        if (driver != null) {
	            try {
	                driver.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            try {
	                driver.quit();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public RestaurantInfo getMapURL(String url) throws Exception {
	    Duration duration = Duration.ofSeconds(10);

	    if (driver == null) {
	        System.out.println("드라이버 없음");
	        throw new IllegalStateException("Driver is not initialized. Call initializeDriver() first.");
	    }
	    try {
	        driver.get(url);// 드라이버에 주수 초기화
	        // 사이트가 특정 정보까지 로딩 될기를 최대 10초 기다림
	        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
	        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential")));
	        RestaurantInfo restaurantInfo = new RestaurantInfo();
	        // 지도 url
	        WebElement tag = driver.findElement(By.cssSelector("li a.link_place"));
	        String mapURL = tag.getAttribute("href");
	        System.out.println(mapURL);
	        restaurantInfo.setMapURL(mapURL);
	        
	        // 현재 영업상태
	        WebElement tit_operation = driver.findElement(By.cssSelector("div.location_present strong.tit_operation span"));
	        System.out.println(tit_operation.getText());
	        restaurantInfo.setOperation(tit_operation.getText());
	        
	        // 식당 메뉴
	        List<WebElement> liElements = driver.findElements(By.cssSelector("ul.list_menu li"));
	        String data;
	        String[] data2;
	        ArrayList<Menu> restMenu = new ArrayList<>();
	        for(WebElement liElement : liElements) {
	        	Menu menu = new Menu();
	        	// 각 li 태그의 데이터를 추출하거나 처리하는 작업을 수행합니다.
	        	data = liElement.getAttribute("textContent").replaceAll("\\s+", " ");
	        	data2 = data.split(" 가격: ");
	        	menu.setName(data2[0].substring(4));
	        	menu.setPrice(data2[1]); 
	        	restMenu.add(menu);
	        }
	        System.out.println(restMenu);
	        restaurantInfo.setRestMenu(restMenu);
	        
	        // 예약, 바달, 포장에 대해서 가능, 불가능
	        List<WebElement> parent = driver.findElements(By.cssSelector("div.placeinfo_default div.location_detail"));
	        if (parent.size() >= 5) {
	        	WebElement loctaionDetail = parent.get(4);
	        	System.out.println(loctaionDetail.getText());
	        	restaurantInfo.setLocationDetail(loctaionDetail.getText());
			} else {
				System.out.println("null");
				restaurantInfo.setLocationDetail(null);
			}
	        
	        // 시설 정보
	        List<WebElement> info = driver.findElements(By.cssSelector("ul.list_facility li span.color_g"));
	        ArrayList<FacilityInfo> facilityInfos = new ArrayList<>();
	        for (WebElement webElement : info) {
	        	FacilityInfo facilityInfo = new FacilityInfo();
	        	facilityInfo.setInfo(webElement.getAttribute("textContent"));
	        	facilityInfos.add(facilityInfo);
			}
	        System.out.println(facilityInfos);
	        restaurantInfo.setFacilityInfos(facilityInfos);
	        
	        return restaurantInfo;
	    } finally {
	        if (driver != null) {
	            try {
	                driver.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            try {
	                driver.quit();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	
}