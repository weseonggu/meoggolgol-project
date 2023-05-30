package com.soldesk.meoggolgol.MeoggolgolProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeleniumService {
	
    public void useDriver(String url) {
    	Selenium sel = new Selenium();
        sel.getDriver().get(url);
        try {
            Thread.sleep(1000);
            //html 콘솔창에 모두 띄우기
            WebElement test = sel.getDriver().findElement(By.cssSelector("#kakaoContent > div#mArticle > div.cont_essential > div > div > a > span.bg_present"));
            String test2 = test.getAttribute("style");
            String[] test3 = test2.split(" ");
            
            System.out.println(test3[1].substring(5,(test3[1].length()-3)));
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        sel.getDriver().quit();
    }
}
