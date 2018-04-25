package com.company;
import com.company.Config;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
/**
 * Created by George on 10/19/2017.
 */
public class PageFundSummaryBook {

    private WebDriver _driver;

    static final String url= Config.appBaseUrl+"/crm/bookSelector.jsp?subjectType=fund&subjectId=9872";

    //element region
    @FindBy(name ="showProfile")
    private WebElement check_profile;
    @FindBy(id="run-button")
    private WebElement button_run;
    //element region

    public PageFundSummaryBook(WebDriver driver)
    {
        this._driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickProfile() throws InterruptedException {
        check_profile.click();
        Thread.sleep(1000);
    }

    public void clickRun() throws InterruptedException {
        button_run.click();
        Thread.sleep(30000);
    }

    public void AcceptBook(){
        _driver.switchTo().alert().dismiss();
    }

}
