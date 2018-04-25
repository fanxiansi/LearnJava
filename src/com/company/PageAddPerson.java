package com.company;

import com.company.Config;
import com.company.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by George on 9/29/2017.
 */
public class PageAddPerson {

    private  WebDriver driver;

    private WebDriverWait wait;

    //element region
    @FindBy(name ="firstName")
    private WebElement firstName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(name="companyName")
    private  WebElement companyName;

    @FindBy(id="savePerson")
    private WebElement saveButton;

    @FindBy(id="isEmployeeCheckbox")
    private WebElement isEmployeeCheckbox;
    //element region

    static final String url= Config.appBaseUrl+"/crm/ManageContacts.do?ACTION=NEW_PERSON";

    //action method
    public PageAddPerson(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,Config.timeOutMid);
    }

    public void inputFirstName(String fName)
    {
        firstName.sendKeys(fName);
    }

    public void inputLastName(String lName)
    {
        lastName.sendKeys(lName);
    }

    public void inputCompanyName(String cName) throws InterruptedException {

        companyName.sendKeys(cName);

        //until element can able to click
        wait.until(ExpectedConditions.elementToBeClickable(isEmployeeCheckbox));

    }

    public void clickSavePerson() throws InterruptedException {

        saveButton.click();
        Thread.sleep(Config.timeOutMid);
    }

    public boolean isDisplayedRelationship(String relationshipType) throws InterruptedException {

        List<WebElement> span = driver.findElements(By.tagName("nav"));

        for (WebElement e:span){
            if (e.getText().equals(relationshipType)){
                Actions action = new Actions(driver);
                action.moveToElement(e).perform();

                Thread.sleep(1000);

                return true;
            }
        }
       return  false;
    }

    public void deleteRelationshipType() throws InterruptedException {
        try {

            WebElement ele =  driver.findElement(By.xpath("//a[contains(@id,'second')]"));

            if (ele.isDisplayed()){
                ele.click();

                Alert alert = driver.switchTo().alert();

                alert.accept();
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void deletePerson(String id){

        driver.get(Config.appBaseUrl+"/crm/ManageContacts.do?ACTION=DELETE&id="+id);
    }
}
