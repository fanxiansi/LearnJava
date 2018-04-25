
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
public class PagePersonSummaryBook {
    private WebDriver _driver;

    //element region
    static final String url= Config.appBaseUrl+"/crm/bookSelector.jsp?subjectType=party&subjectId=789993";

    @FindBy(name ="showContact")
    private WebElement check_profile;

    @FindBy(id="run-button")
    private WebElement button_run;
    //element region

    public PagePersonSummaryBook(WebDriver driver)
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
        //  String str = _driver.switchTo().alert().getText();
        //   System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+str);
        _driver.switchTo().alert().dismiss();
    }
}