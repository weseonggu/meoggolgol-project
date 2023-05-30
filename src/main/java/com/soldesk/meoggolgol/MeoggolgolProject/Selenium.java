package com.soldesk.meoggolgol.MeoggolgolProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class Selenium {
	private WebDriver driver;
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver"; // Properties 설정
    public static String WEB_DRIVER_PATH = "C:/chromedriver.exe"; // WebDriver 경로
//    public static String WEB_DRIVER_PATH = "/home/ubuntu/chromedriver"; // WebDriver 경로
    
    //생성자
    public Selenium() {
    	System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // webDriver 옵션 설정.
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

        // weDriver 생성.
        driver = new ChromeDriver(options);
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

    }
 
//    //ChromeDriver 연결
//    private void chrome() {
//        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//
//        // webDriver 옵션 설정.
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
//        options.addArguments("--lang=ko");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--disable-popup-blocking");       //팝업안띄움
//        options.addArguments("headless");                       //브라우저 안띄움
//        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
//        options.setCapability("ignoreProtectedModeSettings", true);
//
//        // weDriver 생성.
//        driver = new ChromeDriver(options);
////        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//    }
//
//    public void useDriver(String url) {
//        driver.get(url) ;
//
//        try {
//            Thread.sleep(1000);
//            //html 콘솔창에 모두 띄우기
//            WebElement test = driver.findElement(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present"));
//            String test2 = test.getAttribute("style");
//            String[] test3 = test2.split(" ");
//            
//            System.out.println(test3[1].substring(5,(test3[1].length()-3)));
//            
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        quitDriver();
//    }
//
//    private void quitDriver() {
//        driver.quit(); // webDriver 종료
//    }
}
