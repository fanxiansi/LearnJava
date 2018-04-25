package com.company;

import com.company.Pages.PageAllActivity;
import com.company.Pages.PageHFlaxMeeting;
import com.company.Pages.PageMyDraft;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;

public class RunMeetingTest extends RunBase{

    public void run() throws InterruptedException {

        checkMeetingDraftViewBuket();
        checkAutoSaveMeetingDraft();
        checkAutoSaveMeetingDraftEmptyTitle();
        checkEditMeetingDraft();
        checkMeetingDrattUrlinjection();
        checkMeetingCreate();
    }


    public void checkMeetingCreate() throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true, PageHFlaxMeeting.url);
        PageHFlaxMeeting meeting = new PageHFlaxMeeting(driver);


        String title = "create a meeting for test"+_ts;
        meeting.createMeeting(title,"","George",true);

        String description = "Check it in the HFlax's All Activity page.";
        Assert.assertTrue("note should be displayed in the HFlax's page",isMeetingDraftExist(driver,description, PageAllActivity.urlFlax,title,false));
        afterEach(driver);
    }

    //@Test URL injection FB-32320
    public  void checkMeetingDrattUrlinjection()throws InterruptedException {

        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PageHFlaxMeeting.url);
        PageHFlaxMeeting meeting = new PageHFlaxMeeting(driver);

        //create Draft
        String draftTitle ="Test Meeting draft URL injection [id=5]"+_ts;
        meeting.createMeetingDraft(draftTitle,"","George",true);

        print("********Start to test URL injection arrording FB-32320********");

        //check on the My Draft page
        String description = "1.The new meeting draft should be displayed in bsg6 My Draft page.---"+draftTitle;
        Assert.assertTrue(isMeetingDraftExist(driver,description, PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description = "2.The new meeting draft should be displayed in bsg6's All Activity page.";

        Assert.assertTrue(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));

        //edit draft
        String editUrl = getMeetingDraftEditUrl(driver,PageAllActivity.urlFlax,draftTitle);

        _utils.logout(driver);

        //login no cc user
        driver=_utils.login("HFlax",_defaultBsgPwd,false);
        driver.get(editUrl);

        meeting = new PageHFlaxMeeting(driver);
        print("3.HFlax shouldn't be able to see the new meeting draft.");
        Assert.assertFalse(meeting.isSaveDarftButtonDisplay());

        print("********Testing URL injection is successed********");

        afterEach(driver);
    }

    public  void checkEditMeetingDraft()throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PageHFlaxMeeting.url);
        PageHFlaxMeeting meeting = new PageHFlaxMeeting(driver);

        //create a new darft
        print("********Start to test Editing meeting darft.********");
        String draftTitle ="Test meeting draft edit [id=4]"+_ts;
        meeting.createMeetingDraft(draftTitle,"","George",true);

        //check My Draft board
        String description = "1.The new meeting draft should be displayed in HFlax's My Draft page.---"+draftTitle;
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description ="2.The new meeting draft should be displayed in HFlax's All Activity page.";
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));

        //edit draft
        String editUrl = getMeetingDraftEditUrl(driver,PageAllActivity.urlFlax,draftTitle);

        driver.get(editUrl);
        PageHFlaxMeeting editCall = new PageHFlaxMeeting(driver);

        String modifTitle="modifyed";
        editCall.setInputTitle(modifTitle);
        editCall.clickSaveDraftButton();

        //check My Draft board
        description = "3.The modifyed meeting draft should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageMyDraft.url,draftTitle+modifTitle,true));
        
        //check ALL Activity board
        description = "4.The modifyed meeting draft should be displayed in HFlax's All Activity page."+draftTitle;
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
        
        print("********Editing meeting darft test successfully********");
        afterEach(driver);
    }

    //@CC User should be able to view the new draft in his Activity Draft page.
    //@Test notes drafts View Buket by different users such as Creator,CC User,No CC User,Super Admin.
    public  void checkMeetingDraftViewBuket()throws InterruptedException
    {

        //add PE entity's Draft
        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PageHFlaxMeeting.url);
        PageHFlaxMeeting meeting = new PageHFlaxMeeting(driver);

        //create Draft
        String draftTitle ="Test different users bucket [id=1]"+_ts;
        meeting.createMeetingDraft(draftTitle,"","George",true);

        //check My Draft board
        print("********Start meeting buket testing********");
        String description = "1.Should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description ="2.Should be displayed in HFlax's All Activity page."+draftTitle;
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
//        _utils.logout(driver);
        afterEach(driver);
        driver =beforeEach("George",_otherDefaultPwd,true,PageHFlaxMeeting.url);
        //login with cc user's George
//        driver=_utils.login("George",_otherDefaultPwd,false);

        //check My Draft board
        description = "3.CC User George should be able to see the new meeting draft in his My Draft page.";
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description ="4.CC User George should be able to see the new meeting draft in his All Activity page.";
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));

        afterEach(driver);
//        _utils.logout(driver);
        driver =beforeEach("testRead",_defaultBsgPwd,true,PageHFlaxMeeting.url);
        //login with no CC User's testRead
//        driver=_utils.login("testRead",_otherDefaultPwd,false);

        //check My Draft board
        description ="5.No CC User testRead shouldn't be able to see the new meeting draft in his My Draft page.";
        Assert.assertFalse(isMeetingDraftExist(driver,description,PageMyDraft.url,draftTitle,true));
        //check ALL Activity board
        description ="6.No CC User testRead shouldn't be able to see the new meeting draft in his All Activity page.";
        Assert.assertFalse(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
//        _utils.logout(driver);
        afterEach(driver);
        driver =beforeEach("bsg6",_defaultBsgPwd,true,PageHFlaxMeeting.url);
        //check super admin bsg6
//        driver=_utils.login("bsg6",_defaultBsgPwd,false);

        //check My Draft board
        description = "6.Super Admin bsg6 should be able to see the new draft in his My Draft page.";
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageMyDraft.url,draftTitle,true));
        //check ALL Activity board
        driver.get(PageAllActivity.urlFlax);
        description ="7.Super Admin bsg6 should be able to see the new draft in his All Activity page.";
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
        
        print("********Testing meeting buckt successfully*******");
        afterEach(driver);
    }

    public  void checkAutoSaveMeetingDraft() throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PageHFlaxMeeting.url);

        //add PE entity's Draft
        PageHFlaxMeeting meeting = new PageHFlaxMeeting(driver);

        //create a Draft

        String draftTitle ="Test auto-saving drafts [id=2]"+_ts;
        meeting.createMeetingDraft(draftTitle,"","George",false);
        Thread.sleep(60000);

        //check My Draft board
        print("*******Start to test auto-saving meeting********");
        String description = "1.The auto-saving draft should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageMyDraft.url,draftTitle,true));
        //check ALL Activity board
        description ="2.The auto-saving draft should be displayed in HFlax's All Activity page.---"+draftTitle;
        Assert.assertTrue(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
        
        print("*******Test auto-saving successfully*******");

        afterEach(driver);
    }

    //@Test notes drafts auto-saving without title.
    public void checkAutoSaveMeetingDraftEmptyTitle() throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PageHFlaxMeeting.url);
        //add PE entity's Draft
        PageHFlaxMeeting call = new PageHFlaxMeeting(driver);
        String draftTitle ="Test auto-saving drafts [id=3]"+_ts;
        call.setInputCCUser("George");
        Thread.sleep(60000);

        //check My Draft board
        print("*******Start to test meeting draft auto-saving without title*******");
        String description = "1.The auto-saving draft shouldn't be displayed in HFlax's My Draft page(The draft has no title.).---"+draftTitle;
        Assert.assertFalse(isMeetingDraftExist(driver,description,PageMyDraft.url,draftTitle,true));
        //check ALL Activity board
        description ="2.The auto-saving draft shouldn't be displayed in HFlax's All Activity page because the draft has no title.";
        Assert.assertFalse(isMeetingDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
        print("******End testing meeting draft auto-saving without title successfully.*******");

        afterEach(driver);

    }
}
