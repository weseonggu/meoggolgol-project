package com.soldesk.meoggolgol.MeoggolgolProject.crawling;

import java.time.Duration;

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

@Component
@Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
	        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

	        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present")));
	        
	        WebElement test = driver.findElement(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present"));
	        String test2 = test.getAttribute("style");
	        String[] test3 = test2.split(" ");
	        return test3[1].substring(5,(test3[1].length()-3));
	    }
}
