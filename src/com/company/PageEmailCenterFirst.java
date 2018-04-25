
package com.company;
import com.company.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by George on 10/19/2017.
 */
public class PageEmailCenterFirst {

    private WebDriver _driver;

    //email cent first page url
    static final String url= Config.appBaseUrl+"/crm/MailMerge.do?ACTION=merge";

    //element region
    @FindBy(id ="RelatedAssetGroups")
    private WebElement radio_asset_groups;

    @FindBy(id ="RelatedAssetGroupsByRepresentative")
    private WebElement radio_asset_groups_by_representative;

    @FindBy(id="asset-group-relationships")
    private WebElement input_relationships;

    @FindBy(id="start_formButtonNext")
    private WebElement button_start;

    @FindBy(id="pName_assetGroupId")
    private WebElement input_asset;

    @FindBy(id="assetGroup_5761")
    private WebElement assetA;

    @FindBy(id="assetGroup_5801")
    private WebElement assetB;

    @FindBy(id="assetGroup_5803")
    private WebElement assetC;

    @FindBy(id="assetGroup_5805")
    private WebElement assetD;

    @FindBy(id="assetGroup_5807")
    private WebElement assetE;

    @FindBy(name="SelectMergeTypeHandler.enteredStartDateByAsset")
    private  WebElement startDate;

    @FindBy(name="SelectMergeTypeHandler.enteredEndDateByAsset")
    private  WebElement endDate;

    @FindBy(id="asset-group-relationships")
    private Select relationshipSelect;
    //end element region

    //action methoud
    public void clickRelationshipSelect(String str){
    relationshipSelect.selectByIndex(5);
    }

    public void setStartDate(String str)
    {
        startDate.sendKeys(str);
    }

    public void setEndDate(String str)
    {
        endDate.sendKeys(str);
    }

    public void setInput_relationships(String str){

        List<WebElement> optionList =   _driver.findElements(By.xpath("//select[@id='asset-group-relationships']/option"));
        for (WebElement e:optionList){
            if (e.getText().equals(str)){
                e.click();
            }
        }
    }

    public void setInputAsset(String str) throws InterruptedException {

        input_asset.sendKeys(str);
        Thread.sleep(Config.timeOutSmall);
        assetA.click();

        input_asset.sendKeys(str);
        Thread.sleep(Config.timeOutSmall);
        assetB.click();

        input_asset.sendKeys(str);
        Thread.sleep(Config.timeOutSmall);
        assetC.click();

        input_asset.sendKeys(str);
        Thread.sleep(Config.timeOutSmall);
        assetD.click();

        input_asset.sendKeys(str);
        Thread.sleep(Config.timeOutSmall);
        assetE.click();
    }

    public PageEmailCenterFirst(WebDriver driver)
    {
        this._driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickAssetGroups() throws InterruptedException {
        radio_asset_groups.click();
        Thread.sleep(Config.timeOutSmall);
    }

    public void clickStart() throws InterruptedException {
        button_start.click();
        Thread.sleep(30*Config.timeOutSmall);
    }

    public void clickAssetGroupsByRepresentative(){
        radio_asset_groups_by_representative.click();
    }
}

