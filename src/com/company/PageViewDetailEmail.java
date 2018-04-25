package com.company;
import com.company.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by George on 10/19/2017.
 */

public class PageViewDetailEmail {
    private WebDriver _driver;

    static final String url= Config.appBaseUrl+"/utility/merge/showMergeContacts.jsp";

    public PageViewDetailEmail(WebDriver driver)
    {
        this._driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean checkExisted(String str) throws InterruptedException {
      List<WebElement> tableValue =_driver.findElements(By.tagName("td"));
        for (WebElement e:tableValue){
            if (e.getText().equals(str)){
                return true;
            }
        }
    return false;
    }
}