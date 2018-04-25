package com.company.Pages;

import com.company.Pages.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by George on 7/18/2017.
 */
public class PageLogin {
    private  WebDriver driver;

    private WebDriverWait wait;


    //element region
    @FindBy(id ="j_username")
    private WebElement username;

    @FindBy(id = "passwordInput")
    private WebElement passwd;

    @FindBy(id="login-button")
    private  WebElement loginButton;

    @FindBy(id="headerSearchBox")
    @CacheLookup
    private WebElement searchBox;

    @FindBy(id="profile_dropdown_username")
    private WebElement userButton;

    @FindBy(id="log_out_link")
    private WebElement logout;
    //element region

    static final String url= Config.appOnlyIpUrl;

    public PageLogin(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,Config.timeOutMid);
    }

    public void inputUserName(String uName)
    {
        username.sendKeys(uName);
    }

    public void inputPasswd(String upasswd)
    {
        passwd.sendKeys(upasswd);
    }

    public boolean checkElement()
    {
        return searchBox.isDisplayed();
    }

    public void clickButton(){
        loginButton.click();
    }

    public WebDriver login(String user,String passwd)
    {
        this.inputUserName(user);
        this.inputPasswd(passwd);
        this.clickButton();
        return  driver;
    }

    public void loginOut() throws InterruptedException {
        this.driver.get(this.url);

        //until button display ,user click the button.
        wait.until(ExpectedConditions.elementToBeClickable(userButton));
        userButton.click();

        //until button display ,user click the button.
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        logout.click();
    }
}
