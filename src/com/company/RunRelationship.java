package com.company;

import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.sql.Timestamp;

/**
 * Created by George on 9/29/2017.
 */
public class RunRelationship extends RunBase{



    public void run() throws InterruptedException {
        viewEmployeeRelationship();
        personEntity();
        viewFundSumaryBook();
        viewPersonSumaryBook();
        viewAssetGropEmialList();
AddNewPerson();
    }

    public void AddNewPerson() throws InterruptedException {
        WebDriver driver = beforeEach("bsg85",_defaultBsgPwd,true,PageAddPerson.url);
        PageAddPerson person = new PageAddPerson(driver);
        person.inputFirstName("A");
        person.inputLastName("Test"+_ts);
        person.inputCompanyName("ABC Company");

        //scroll to bottom
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        person.clickSavePerson();
        Assert.assertTrue(person.isDisplayedRelationship("is employee of"));

    }

    private   void viewEmployeeRelationship()throws InterruptedException {
        //WebDriver driver=_utils.login("bsg85",_defaultBsgPwd,true);
//        WebDriver driver =beforeEacheTest();
        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PageAddPerson.url);
        //Thread.sleep(100* Config.timeOutSmall);
//.url);
//        PageHFlaxCall call = new PageHFlaxCall(driver);
//        driver.get(PageAddPerson.url);
        PageAddPerson person = new PageAddPerson(driver);
        person.inputCompanyName("ABC Company");
        person.inputFirstName("Fan");
        person.inputLastName("George2");
        person.clickSavePerson();
        Assert.assertTrue(person.isDisplayedRelationship("is employee of"));
        person.deleteRelationshipType();
        Thread.sleep(6000);
        person.deletePerson(_utils.getEntityId(driver.getCurrentUrl(),"party_id"));
        driver.quit();
    }

  //  private  void testOrganizationAndHoldingRelationship(){
  //      WebDriver driver=_utils.login("bsg85",_defaultBsgPwd,true);
   // }
    //1.Fund summary book test FB-32709
    private void viewFundSumaryBook() throws InterruptedException{
        //WebDriver driver=_utils.login("bsg6",_defaultBsgPwd,true);
        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PageFundSummaryBook.url);
//        WebDriver driver =beforeEacheTest();
//        driver.get(PageFundSummaryBook.url);
        PageFundSummaryBook FundSummary = new PageFundSummaryBook(driver);
        Thread.sleep(1000);
        FundSummary.clickProfile();
        FundSummary.clickRun();
        //FundSummary.AcceptBook();
        Assert.assertFalse(isErrorMessage(driver,"report this"));
        driver.quit();
    }

    private boolean isErrorMessage(WebDriver driver,String TextName)
    {
        try {
            driver.findElement(By.linkText(TextName)).isDisplayed();
        }catch (NoSuchElementException e){
            print("No Error Page displayed!");
            return false;
        }
        return true;

    }

    //2.view Holly Flax Summary book FB-32614
    private void viewPersonSumaryBook() throws InterruptedException{
        //WebDriver driver=_utils.login("bsg6",_defaultBsgPwd,true);
//        WebDriver driver = beforeEacheTest();
        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PagePersonSummaryBook.url);
//        driver.get(PagePersonSummaryBook.url);
        PagePersonSummaryBook personSummary = new PagePersonSummaryBook(driver);
        Thread.sleep(1000);
        personSummary.clickProfile();
        personSummary.clickRun();
        personSummary.AcceptBook();
        Assert.assertFalse(isErrorMessage(driver,"report this"));
        driver.quit();
    }


    //3. view Send Email
    private void viewAssetGropEmialList() throws InterruptedException {
//        WebDriver driver = beforeEacheTest();
        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PageEmailCenterFirst.url);
        driver.get(PageEmailCenterFirst.url);
        PageEmailCenterFirst email = new PageEmailCenterFirst(driver);
        try {
            email.clickAssetGroups();
            email.setInputAsset("George Asset Group");
            Thread.sleep(5000);
            email.setInput_relationships("receives K-1 for");
            Thread.sleep(5000);
            email.setStartDate("6/1/2017");
            email.setEndDate("6/1/2018");
            Thread.sleep(6000);
            email.clickStart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PageEmailCenterSencond secondPage = new PageEmailCenterSencond(driver);
        secondPage.inputSubject("test receipents list");
        secondPage.clickNextButton();
        Thread.sleep(6000);
        //should be create 2 receiptents.
        Assert.assertTrue(driver.findElement(By.id("contact-list-total")).getText().contains("2"));
        driver.get(PageViewDetailEmail.url);
        PageViewDetailEmail detailEmail = new PageViewDetailEmail(driver);
        Assert.assertTrue(detailEmail.checkExisted("George Asset Group B"));
        Assert.assertTrue(detailEmail.checkExisted("George Asset Group D"));
    }

    private void personEntity() throws InterruptedException {
        //WebDriver driver=_utils.login("bsg6",_defaultBsgPwd,true);
//        WebDriver driver=beforeEacheTest();
        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PageEntityGeorge.url);
//        driver.get(PageEntityGeorge.url);
        PageEntityGeorge George = new PageEntityGeorge(driver);
        Thread.sleep(6000);
        //方法一：
        //1、操作垂直滚动条，向下移动500像素
        String setscroll = "document.documentElement.scrollTop=" + "500";
        //2、水平滚动条，向右移动500像素
        //String setscroll = "document.documentElement.scrollLeft=" + "500";

        JavascriptExecutor jse=(JavascriptExecutor) driver;
        jse.executeScript(setscroll);
        Thread.sleep(5000);
        if (_utils.isElementDisply(driver,"nav","receives statements for")){
            print("receives statements for existed");
            Actions action = new Actions(driver);
        }
        if (_utils.isElementDisply(driver,"nav","is employee of"))
        {
            System.out.println("is emplyee of existed");
        }
        if (_utils.isElementDisply(driver,"nav","test delivery")){
            System.out.println("test delivery existed");
        }
        if (_utils.isElementDisply(driver,"nav","primary contact for")){
            System.out.println("primary contact for existed");
        }
        if (_utils.isElementDisply(driver,"nav","owns account")){
            System.out.println("owns account existed");
        }
        //George.relationshipIsDisplyed();
    }
}
