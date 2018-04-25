package com.company.Pages;
import com.company.Pages.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by George on 12/7/2017.
 */
public class PageReportCent {
    private WebDriver _driver;

    static final String url= Config.appBaseUrl+"/portal/_bwid_50327/_twid_50329/_md_edit/_st_maximized#50329";

    //element region
    @FindBy(className ="c-multi-select__text")
    private WebElement subject;

    @FindBy(id="compose_formButtonNext")
    private WebElement button_next;
    //element region

    public PageReportCent(WebDriver driver)
    {
        this._driver = driver;
        PageFactory.initElements(driver,this);
    }
}
