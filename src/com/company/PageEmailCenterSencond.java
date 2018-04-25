package com.company;
import com.company.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
/**
 * Created by George on 10/19/2017.
 */
public class PageEmailCenterSencond {

    private WebDriver _driver;

    static final String url= Config.appBaseUrl+"/crm/MailMerge.do?ACTION=merge";

    //Element region
    @FindBy(id ="email-subject")
    private WebElement subject;
    @FindBy(id="compose_formButtonNext")
    private WebElement button_next;
    //Element region


    public PageEmailCenterSencond(WebDriver driver)
    {
        this._driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void inputSubject(String str) throws InterruptedException {
        subject.sendKeys(str);
        Thread.sleep(1000);
    }

    public void clickNextButton(){
        button_next.click();
    }
}
