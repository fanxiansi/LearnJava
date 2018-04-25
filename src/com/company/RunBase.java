package com.company;

import org.openqa.selenium.WebDriver;

import java.sql.Timestamp;

/**
 * Created by George on 1/24/2018.
 */
public class RunBase {
    public Utils _utils;
    public Timestamp _ts;
    public String _defaultBsgPwd;
    public String _otherDefaultPwd;

    public RunBase(){

        _ts=new Timestamp(System.currentTimeMillis());
        _defaultBsgPwd="rup3rt";
        _otherDefaultPwd="root123";
        _utils=new Utils();
    }

    public void print(String content){
        System.out.println(content);
    }


    public WebDriver beforeEach(String user, String pwd, boolean isNewDriver, String url){

        WebDriver dr =_utils.login(user,pwd,isNewDriver);

        dr.get(url);
        return  dr;
    }

    public  void afterEach(WebDriver dr) throws InterruptedException {
        _utils.logout(dr);
        dr.quit();
    }

    public  String getNoteDraftEditUrl(WebDriver dr,String url,String draftTitle) throws InterruptedException {

        dr.get(url);

        Thread.sleep(20*Config.timeOutSmall);

        PageAllActivity PageAllActivity = new PageAllActivity(dr);

        if(PageAllActivity.CheckText(draftTitle)) {
            return PageAllActivity.getEditPageUrl(draftTitle);
        }
        //return edit draft's url
        return "";

    }

    public boolean isNoteDraftExist(WebDriver dr,String description,String url,String draftTitle,boolean isMyDraftPage) throws InterruptedException {

        print(description);

        dr.get(url);

        Thread.sleep(20*Config.timeOutSmall);

        if (isMyDraftPage){
            PageMyDraft draftPage = new PageMyDraft(dr);
            return draftPage.CheckText(draftTitle);
        }
        else{
            PageAllActivity PageAllActivity = new PageAllActivity(dr);
            return PageAllActivity.CheckText(draftTitle);
        }

    }

    public void deleteDraft() throws InterruptedException {

        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PageMyDraft.url);
        PageMyDraft draftPage = new PageMyDraft(driver);

        draftPage.deleteAllDraf();

        afterEach(driver);
    }

    public  String getMeetingDraftEditUrl(WebDriver dr,String url,String draftTitle) throws InterruptedException {

        dr.get(url);

        Thread.sleep(20*Config.timeOutSmall);

        PageAllActivity PageAllActivity = new PageAllActivity(dr);

        if(PageAllActivity.CheckText(draftTitle)) {
            return PageAllActivity.getEditCallUrl(draftTitle);
        }
        //return edit draft's url
        return "";

    }

    public boolean isMeetingDraftExist(WebDriver dr,String description,String url,String draftTitle,boolean isMyDraftPage) throws InterruptedException {

        print(description);

        dr.get(url);

        Thread.sleep(20*Config.timeOutSmall);

        if (isMyDraftPage){
            PageMyDraft draftPage = new PageMyDraft(dr);
            return draftPage.CheckText(draftTitle);
        }
        else{
            PageAllActivity PageAllActivity = new PageAllActivity(dr);
            return PageAllActivity.CheckText(draftTitle);
        }

    }

    public  String getCallDraftEditUrl(WebDriver dr,String url,String draftTitle) throws InterruptedException {

        dr.get(url);

        Thread.sleep(20*Config.timeOutSmall);

        PageAllActivity PageAllActivity = new PageAllActivity(dr);

        if(PageAllActivity.CheckText(draftTitle)) {
            return PageAllActivity.getEditCallUrl(draftTitle);
        }
        //return edit draft's url
        return "";

    }

    public boolean isCallDraftExist(WebDriver dr,String description,String url,String draftTitle,boolean isMyDraftPage) throws InterruptedException {

        print(description);

        dr.get(url);

        Thread.sleep(20*Config.timeOutSmall);

        if (isMyDraftPage){
            PageMyDraft draftPage = new PageMyDraft(dr);
            return draftPage.CheckText(draftTitle);
        }
        else{
            PageAllActivity PageAllActivity = new PageAllActivity(dr);
            return PageAllActivity.CheckText(draftTitle);
        }

    }
}
