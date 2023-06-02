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
}