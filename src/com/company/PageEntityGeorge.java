package com.company;
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
public class PageEntityGeorge {

    private WebDriver _driver;

    static final String url=Config.appBaseUrl+"/crm/ManageContacts.action?display=&viewType=summary&party_id=807257";

    public PageEntityGeorge(WebDriver driver)
    {
        this._driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean relationshipIsDisplyed() {
        if ( _driver.findElement(By.xpath("//*[text()='receives statements for']")).isDisplayed()){
            System.out.println("receives statements for existed");
        }
        if (_driver.findElement(By.xpath("//nav[@data-rel-desc='is employee of']")).isDisplayed()){
            System.out.println("is emplyee of existed");
        }
        if (_driver.findElement(By.xpath("//nav[@data-rel-desc='test delivery']")).isDisplayed()){
            System.out.println("test delivery existed");
        }
        if (_driver.findElement(By.xpath("nav[@data-rel-desc='primary contact for']")).isDisplayed()){
            System.out.println("primary contact for existed");
        }
        if (_driver.findElement(By.xpath("nav[@data-rel-desc='owns account']")).isDisplayed()){
            System.out.println("owns account existed");
        }
        return true;
    }
}
