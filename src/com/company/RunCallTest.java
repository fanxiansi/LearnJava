package com.company;
import com.company.Pages.PageAllActivity;
import com.company.Pages.PageHFlaxCall;
import com.company.Pages.PageMyDraft;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by George on 8/18/2017.
 */
public class RunCallTest extends RunBase{

    public void run() throws InterruptedException {
//        checkCallDraftViewBuket();
        checkAutoSaveCallDraft();
        checkAutoSaveCallDraftEmptyTitle();
        checkEditCallDraft();
        checkCallDrattUrlinjection();
        checkCallCreate();
    }

    public void checkCallCreate() throws InterruptedException {
        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true, PageHFlaxCall.url);
        PageHFlaxCall call = new PageHFlaxCall(driver);


        String title = "create a call for test"+_ts;
        call.createCall(title,"","George",true);

        String description = "Check it in the HFlax's All Activity page.";
        Assert.assertTrue("call should be displayed in the HFlax's page",isCallDraftExist(driver,description, PageAllActivity.urlFlax,title,false));
    }

    //@Test URL injection FB-32320
    public  void checkCallDrattUrlinjection()throws InterruptedException {

        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PageHFlaxCall.url);

        //go to call page
        PageHFlaxCall call = new PageHFlaxCall(driver);

        //create a call
        String draftTitle ="Test call draft URL injection [id=5]"+_ts;
        call.createCallDraft(draftTitle,"","George",true);
        print("*******Start to test URL injection*******");

        //check on the My Draft page
        String description = "1.The new call draft should be displayed in bsg6 My Draft page.---"+draftTitle;
        Assert.assertTrue(isCallDraftExist(driver,description, PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        print("2.The new call draft should be displayed in bsg6's All Activity page.");
        Assert.assertTrue(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));

        //get Draft edit url
        String editDaftUrl= getCallDraftEditUrl(driver,PageAllActivity.urlFlax,draftTitle);

        _utils.logout(driver);

        //login as on-cc user check right.
        driver=_utils.login("HFlax",_defaultBsgPwd,false);

        driver.get(editDaftUrl);
        call = new PageHFlaxCall(driver);

        print("3.HFlax shouldn't be able to see the new call draft.");
        Assert.assertFalse(call.isSaveDarftButtonDisplay());
        print("********End testing URL jinjection successfully.*******");

        afterEach(driver);
    }
    public  void checkEditCallDraft()throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PageHFlaxCall.url);

        //add PE entity's Draft
        PageHFlaxCall call = new PageHFlaxCall(driver);

        //create Draft
        String draftTitle ="Test call draft edit [id=4]"+_ts;
        call.createCallDraft(draftTitle,"","George",true);

        //check My Draft board
        print("********Start to test editing call draft******");
        String description = "1.The new call draft should be displayed in HFlax's My Draft page.---"+draftTitle;
        Assert.assertTrue(isCallDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description = "2.The new call draft should be displayed in HFlax's All Activity page.";
        Assert.assertTrue(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));

        //edit draft
        driver.get(getCallDraftEditUrl(driver,PageAllActivity.urlFlax,draftTitle));

        PageHFlaxCall editCall = new PageHFlaxCall(driver);

        //edit Draft
        String modifTitle="modifyed";
        editCall.setInputTitle(modifTitle);
        editCall.clickSaveDraftButton();

        //check My Draft board
        description = "3.The modifyed call draft should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isCallDraftExist(driver,description,PageMyDraft.url,draftTitle+modifTitle,true));

        //check ALL Activity board
        description = "4.The modifyed call draft should be displayed in HFlax's All Activity page."+draftTitle;
        Assert.assertTrue(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
        print("*******End testing CALL draft successfully*******");

        afterEach(driver);
    }

    //@CC User should be able to view the new draft in his Activity Draft page.
    //@Test notes drafts View Buket by different users such as Creator,CC User,No CC User,Super Admin.
    public  void checkCallDraftViewBuket()throws InterruptedException
    {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PageHFlaxCall.url);
        //add PE entity's Draft
        driver.get(PageHFlaxCall.url);
        PageHFlaxCall call = new PageHFlaxCall(driver);

        //create Draft
        String draftTitle ="Test different users bucket [id=1]"+_ts;
        call.createCallDraft(draftTitle,"","George",true);

        //check My Draft board
        print("*******Start to test CALL buket*******");
        String description = "1.Should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isCallDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description = "2.Should be displayed in HFlax's All Activity page."+draftTitle;
        Assert.assertTrue(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
//        _utils.logout(driver);
        afterEach(driver);

        //login with cc user's George
        driver=_utils.login("George",_otherDefaultPwd,true);

        //check My Draft board
        description = "3.CC User George should be able to see new Call in his My Draft page.";
        Assert.assertTrue(isCallDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description = "4.CC User George should be able to see the new call draft in his All Activity page.";
        Assert.assertTrue(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
//        _utils.logout(driver);
afterEach(driver);
        //login with no CC User's testRead
        driver=_utils.login("testRead",_otherDefaultPwd,true);

        //check My Draft board
        description = "5.No CC User testRead shouldn't be able to see the new call draft in his My Draft page.";
        Assert.assertFalse(isCallDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description = "6.No CC User testRead shouldn't be able to see the new call draft in his All Activity page.";
        Assert.assertFalse(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
//        _utils.logout(driver);
afterEach(driver);
        //check super admin bsg6
        driver=_utils.login("bsg6",_defaultBsgPwd,true);

        //check My Draft board
        description = "6.Super Admin bsg6 should be able to see the new call draft in his My Draft page.";
        Assert.assertTrue(isCallDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description = "7.Super Admin bsg6 should be able to see the new call draft in his All Activity page.";
        Assert.assertTrue(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));
        print("*******End testing User buket successfully.*******");

        afterEach(driver);
    }
    public  void checkAutoSaveCallDraft() throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PageHFlaxCall.url);
        //add PE entity's Draft
        driver.get(PageHFlaxCall.url);
        PageHFlaxCall call = new PageHFlaxCall(driver);

        //create Draft
        String draftTitle ="Test auto-saving drafts [id=2]"+_ts;
        call.createCallDraft(draftTitle,"","George",false);
        Thread.sleep(65000);

        //check My Draft board
        String description = "1.The auto-saving draft should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isCallDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description = "2.The auto-saving draft should be displayed in HFlax's All Activity page.---"+draftTitle;
        Assert.assertTrue(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));

        afterEach(driver);
    }
    //@Test notes drafts auto-saving without title.
    public void checkAutoSaveCallDraftEmptyTitle() throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PageHFlaxCall.url);
        //add PE entity's Draft
        driver.get(PageHFlaxCall.url);
        PageHFlaxCall call = new PageHFlaxCall(driver);
        String draftTitle ="Test auto-saving drafts [id=3]"+_ts;
        call.createCallDraft("","","George",false);

        Thread.sleep(65000);

        //check My Draft board
        String description = "1.The auto-saving draft shouldn't be displayed in HFlax's My Draft page(The draft has no title.).---"+draftTitle;
        Assert.assertFalse(isCallDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        //check ALL Activity board
        description = "2.The auto-saving draft shouldn't be displayed in HFlax's All Activity page because the draft has no title.";
        Assert.assertFalse(isCallDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));

        afterEach(driver);
    }
}
