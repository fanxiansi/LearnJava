package com.company;

import com.company.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Key;
import java.util.List;

/**
 * Created by George on 7/19/2017.
 * add Draft Notes Page.
 */
public class PagePrivateENote {

    static final String url = Config.appBaseUrl+"/crm/note.do?otherId=12792&otherClassName=PortfolioFundBean&isMultipleNote=undefined&isFromEmail=undefined";

    //element region
    private WebDriver driver;

    @FindBy(id="show-restriction-button")
    private WebElement closeButton;

    @FindBy(id ="occurredDate")
    @CacheLookup
    private WebElement inputDate;//"7/19/2017"

    @FindBy(id="NoteRegarding")
    @CacheLookup
    private WebElement inputRegarding;

    @FindBy(id="addNoteTitle")
    @CacheLookup
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

    @FindBy(name="isImportant")
    @CacheLookup
    private WebElement selectTopOfList;

    @FindBy(id = "buttonSaveDraft")
    @CacheLookup
    private WebElement draftButton;

    @FindBy(id = "saveNote1" )
    private WebElement noteButton;

    private WebElement inputContent;
    //element region

    //create a draft note
    public void createNoteDraft(String title,String content,String ccUser,String linkUser,boolean isTop) throws InterruptedException {
        try{
            this.setInputTitle(title);
            if (isTop) {
                this.setSelectTopOfList();
            }

            if (!ccUser.isEmpty()){
                this.setInputCCUser(ccUser);
            }

            if(!linkUser.isEmpty()) {
                this.setInputlinks(linkUser);
            }

            if(!content.isEmpty()) {
                this.setInputContent(content);
            }

            this.clickSaveDraftButton();
            }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //create a note
    public void createNote(String title,String content,String ccUser,String linkUser,boolean isTop) throws InterruptedException {
        try{
            this.setInputTitle(title);
            if (isTop) {
                this.setSelectTopOfList();
            }

            if (!ccUser.isEmpty()){
                this.setInputCCUser(ccUser);
            }

            if(!linkUser.isEmpty()) {
                this.setInputlinks(linkUser);
            }

            if(!content.isEmpty()) {
                this.setInputContent(content);
            }

            this.clickNoteButton();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isSaveDarftButtonDisplay()
    {
        boolean rt=false;
        try {
            rt=draftButton.isDisplayed();
        }catch (Exception e){
            System.out.println("tite do not exist!");
            return false;
        }

        return rt;
    }

    public PagePrivateENote(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
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

    public void setInputlinks(String entityName) throws InterruptedException {
        inputlinks.sendKeys(entityName);
        Thread.sleep(2000);
        inputlinks.sendKeys(Keys.TAB);

    }
    public void setInputDate(String date){
        inputDate.sendKeys(date);
    }

    public void setInputCCUser(String ccUser)
    {
//        inputCCUser.sendKeys(ccUser);
        inputCCUser.click();
        driver.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[2]")).click();
    }

    public void setSelectTopOfList(){
        selectTopOfList.click();
    }

    public void clickSaveDraftButton(){
        draftButton.click();
    }

    public void clickCloseButton(){
        closeButton.click();
    }

    public void clickNoteButton(){
        noteButton.click();
    }
}
