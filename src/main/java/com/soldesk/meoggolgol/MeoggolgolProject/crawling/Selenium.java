package com.soldesk.meoggolgol.MeoggolgolProject.crawling;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class Selenium {
	 private WebDriver driver;

	    //private static final String url = "https://www.naver.com/";
	    public Selenium() {
	        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	        //크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)

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

<<<<<<< HEAD:src/main/java/com/soldesk/meoggolgol/MeoggolgolProject/crawling/CrawlingExample.java
	        try {
	        	getImageURL(url);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//	        driver.close();	//탭 닫기
//	        driver.quit();	//브라우저 닫기
=======

>>>>>>> origin/weseonggu:src/main/java/com/soldesk/meoggolgol/MeoggolgolProject/crawling/Selenium.java
	    }

		/**
	     * data가져오기
	     */
	    public String getImageURL(String url) throws Exception {
	    	Duration duration = Duration.ofSeconds(10);
	    	if(driver == null) {
	    		System.out.println("드라이버 없음");
	    		throw new IllegalStateException("Driver is not initialized. Call initializeDriver() first.");
	    	}
	        driver.get(url);    //브라우저에서 url로 이동한다.
//	        System.out.println("시작");
	        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
//	        Thread.sleep(5000); //브라우저 로딩될때까지 잠시 기다린다.

	        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present")));
	        
	        WebElement test = driver.findElement(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present"));
	        String test2 = test.getAttribute("style");
	        String[] test3 = test2.split(" ");
//	        System.out.println(test3[1].substring(5,(test3[1].length()-3)));
	        // findElement (끝에 s없음) 는 해당되는 선택자의 첫번째 요소만 가져온다
//	        driver.close();	//탭 닫기
//	        driver.quit();
	        return test3[1].substring(5,(test3[1].length()-3));
	    }
}
