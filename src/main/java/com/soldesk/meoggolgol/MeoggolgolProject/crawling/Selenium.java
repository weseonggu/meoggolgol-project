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

	public Selenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		options.addArguments("--lang=ko");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--blink-settings=imagesEnabled=false");
		options.setCapability("ignoreProtectedModeSettings", true);

		driver = new ChromeDriver(options);
	}

	/**
	 * data가져오기
	 */
	public String getImageURL(String url) throws Exception {
	    Duration duration = Duration.ofSeconds(10);

	    if (driver == null) {
	        System.out.println("드라이버 없음");
	        throw new IllegalStateException("Driver is not initialized. Call initializeDriver() first.");
	    }

	    try {
	        driver.get(url);
	        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);
	        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present")));
	        WebElement test = driver.findElement(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present"));
	        String test2 = test.getAttribute("style");
	        String[] test3 = test2.split(" ");

	        return test3[1].substring(5, (test3[1].length() - 3));
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