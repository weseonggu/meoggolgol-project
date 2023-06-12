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
import org.springframework.stereotype.Component;

import com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage.Menu;
import com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage.RestaurantInfo;

import jakarta.annotation.PreDestroy;

@Component
public class SeleniumSingleton {
    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public String getImageURL(String url) throws Exception {
        Duration duration = Duration.ofSeconds(10);
        WebDriver driver = getDriver();

        try {
            driver.get(url);
            WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present")));
            WebElement tag = driver.findElement(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present"));
            String[] property = tag.getAttribute("style").split(" ");
            return property[1].substring(5, (property[1].length() - 3));
        } finally {
            if (driver != null) {
                driver.quit();
                driverThreadLocal.remove();
            }
        }
    }
    
    public RestaurantInfo getDetailRest(String url) throws Exception{
    	Duration duration = Duration.ofSeconds(10);
        WebDriver driver = getDriver();
        
        try {
        	driver.get(url);// 드라이버에 주수 초기화
	        // 사이트가 특정 정보까지 로딩 될기를 최대 10초 기다림
	        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
	        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential")));
	        RestaurantInfo restaurantInfo = new RestaurantInfo();
	        // 지도 url
	        WebElement tag = driver.findElement(By.cssSelector("li a.link_place"));
	        String mapURL = tag.getAttribute("href");
	        restaurantInfo.setMapURL(mapURL);
	        
	        // 현재 영업상태
	        List<WebElement> titOperationElements = driver.findElements(By.cssSelector("div.location_present strong.tit_operation span"));

	        if (!titOperationElements.isEmpty()) {
	            WebElement tit_operation = titOperationElements.get(0);
	            restaurantInfo.setOperation(tit_operation.getText());
	        } else {
	            WebElement operation = driver.findElement(By.cssSelector("ul.list_caution li"));
	            restaurantInfo.setOperation(operation.getText());
	        }
	        
	        // 영업시간
	        List<WebElement> hours = driver.findElements(By.cssSelector("div.location_present ul.list_operation li"));
	        String businessHour = "";
	        if (hours.size() > 0) {
	        	for (int i = 0; i < hours.size(); i++) {
	                WebElement webElement = hours.get(i);
	                businessHour = businessHour + webElement.getText();
	                if (i != hours.size() - 1) {
	                    businessHour = businessHour + ",";
	                }
	            }
	        	businessHour = businessHour.replaceAll("\r\n", "").replace("더보기", "").replaceAll("\n", "");
	        	restaurantInfo.setBusinessHours(businessHour);
			}
	        
	        // 예약, 바달, 포장에 대해서 가능, 불가능
	        List<WebElement> locationDetail = driver.findElements(By.cssSelector("span.ico_delivery"));

	        if (locationDetail.size() > 0) {
	            WebElement icoDeliveryElement = locationDetail.get(0);
	            WebElement parentDiv = icoDeliveryElement.findElement(By.xpath("../.."));
	            WebElement divElement = parentDiv.findElement(By.cssSelector("div.location_detail"));
	            restaurantInfo.setLocationDetail(divElement.getText());
	        } else {
	            restaurantInfo.setLocationDetail(null);
	        }
	        
	        // 시설 정보
	        List<WebElement> info = driver.findElements(By.cssSelector("ul.list_facility li span.color_g"));
	        String facilityInfos = "";
	        if (info.size() > 0) {
				for (int i = 0; i < info.size(); i++) {
					WebElement webElement = info.get(i);
					facilityInfos = facilityInfos + webElement.getText();
					if (i != info.size() - 1) {
						facilityInfos = facilityInfos + ",";
					}
				}
			}
	        restaurantInfo.setFacilityInfos(facilityInfos);
	        
	        return restaurantInfo;
		} finally {
			if (driver != null) {
                driver.quit();
                driverThreadLocal.remove();
            }
	    }
    }
    
    public ArrayList<Menu> getRestMenu(String url) throws Exception{
    	Duration duration = Duration.ofSeconds(10);
        WebDriver driver = getDriver();
        
        try {
        	driver.get(url);// 드라이버에 주수 초기화
	        // 사이트가 특정 정보까지 로딩 될기를 최대 10초 기다림
	        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
	        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential")));
	        
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
	        	menu.setPrice(data2[1].split(" ")[0]); 
	        	restMenu.add(menu);
	        }
	        System.out.println(restMenu);
	        return restMenu;
		} finally {
			if (driver != null) {
                driver.quit();
                driverThreadLocal.remove();
            }
	    }
    }

    private WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            options.addArguments("--lang=ko");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("headless");
            options.addArguments("--blink-settings=imagesEnabled=false");
            options.setCapability("ignoreProtectedModeSettings", true);

            driver = new ChromeDriver(options);
            driverThreadLocal.set(driver);
        }

        return driver;
    }

    @PreDestroy
    public void destroyDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}

