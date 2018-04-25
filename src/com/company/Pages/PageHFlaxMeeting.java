package com.company.Pages;

/**
 * Created by George on 8/18/2017.
 */

import com.company.Pages.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageHFlaxMeeting {

    public static final String url = Config.appBaseUrl+"/crm/ManageMeetings.do?ACTION=NEW&fromCalendar=true&partyId=789993&type=Face%20to%20Face&otherEntityType=undefined&startDate=2017-8-18%2010:0&stopDate=2017-8-18%2011:0";

    private WebDriver driver;

    //element region
    @FindBy(id="show-restriction-button")
    private WebElement closeButton;

    @FindBy(id="Regarding")
    private WebElement inputRegarding;

    @FindBy(id="addEditMeetingTitle")
    private WebElement inputTitle;

    @FindBy(id="noteAuthor")
    @CacheLookup
    private WebElement selectAuthor;

    @FindBy(id="activitySubtype")
    @CacheLookup
    private WebElement selectTemplate;

    @FindBy(id="s2id_autogen1")
    @CacheLookup
    private WebElement inputlinks;

    @FindBy(css = "input.default")
    @CacheLookup
    private WebElement inputCCUser;

    @FindBy(id = "buttonSaveDraft")
    @CacheLookup
    private WebElement draftButton;

    @FindBy(id ="saveMeeting")
    private WebElement saveButton;

    private WebElement inputContent;
    //element region

    public boolean isSaveDarftButtonDisplay()
    {
        boolean rt=false;
        try {
            rt=draftButton.isDisplayed();
        }catch (Exception e){
            System.out.println("tite do not exist!");
            return false;}
        return rt;
    }

    public PageHFlaxMeeting(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //create a draft note
    public void createMeetingDraft(String title,String content,String ccUser,boolean isClickSaveDraft) {

        this.setInputTitle(title);

        if (!ccUser.isEmpty()){
            this.setInputCCUser(ccUser);
        }

        if(!content.isEmpty()) {
            this.setInputContent(content);
        }
        if(isClickSaveDraft) {
            this.clickSaveDraftButton();
        }
    }

    //create a note
    public void createMeeting(String title,String content,String ccUser,boolean isClickSave) {

        this.setInputTitle(title);

        if (!ccUser.isEmpty()){
            this.setInputCCUser(ccUser);
        }

        if(!content.isEmpty()) {
            this.setInputContent(content);
        }
        if(isClickSave) {
            this.clickSaveButton();
        }
    }

    public void setInputTitle(String title)
    {
        inputTitle.sendKeys(title);
    }

    public void setInputContent(String content)
    {
        driver.switchTo().frame("noteBody_ifr");
        inputContent = driver.findElement(By.id("tinymce"));
        inputContent.sendKeys(content);
        driver.switchTo().parentFrame();
    }

    public void setInputCCUser(String ccUser)
    {
        inputCCUser.click();
        driver.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[2]")).click();
    }

    public void clickSaveDraftButton(){
        draftButton.click();
    }
    public void clickSaveButton(){
        saveButton.click();
    }
    public void clickCloseButton(){
        closeButton.click();
    }

}
