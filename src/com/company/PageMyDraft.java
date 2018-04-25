package com.company;

import com.company.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by George on 7/20/2017.
 */
public class PageMyDraft {

    static final String url= Config.appBaseUrl+"/crm/ManageDraft.do?ACTION=display";

    private WebDriver driver;

    private WebElement titleAndDescription;

    public  PageMyDraft ( WebDriver dr)
    {
        driver = dr;
    }

    public boolean CheckText(String draftitle){

        try {

            titleAndDescription = driver.findElement(By.partialLinkText(draftitle));
        }catch (Exception e){

            System.out.println("tite do not exist!");
            return false;
        }

        boolean isTrue = titleAndDescription.isDisplayed();
        return isTrue;
    }

    public void deleteAllDraf() throws InterruptedException {

        List<WebElement> elementList=driver.findElements(By.linkText("Delete"));

        int num = elementList.size();

        for (int i=0;i<num;i++){
            driver.findElement(By.linkText("Delete")).click();
            driver.switchTo().alert().accept();
            Thread.sleep(10*Config.timeOutSmall);
        }
    }

}
