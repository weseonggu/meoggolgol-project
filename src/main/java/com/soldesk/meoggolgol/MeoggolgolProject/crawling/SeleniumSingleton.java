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
import org.springframework.stereotype.Component;

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

