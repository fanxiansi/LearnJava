package com.company;

import com.company.Pages.Config;
import com.company.Pages.PageAllActivity;
import com.company.Pages.PageMyDraft;
import com.company.Pages.PagePrivateENote;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by George on 8/11/2017.
 */
public class RunNoteTest extends RunBase{

//    public RunNoteTest(){
//
//    }

    public void run() throws InterruptedException {

        //delete draft on the My Draft page
        deleteDraft();

//        checkNoteDrattUrlinjection();
//        checkAutoSaveNoteDraft();
//        checkAutoSaveNoteDraftEmptyTitle();
//        checkEditNoteDraft();
//        checkNoteDraftViewBuket();
//        checkCloseDraftByAccident();
//        checkCreateLinks();
//        checkNoteCreate();

    }

    //Create a new note ,The process should be Ok.
    public void checkNoteCreate() throws InterruptedException {
        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true, PagePrivateENote.url);
        PagePrivateENote note = new PagePrivateENote(driver);

        String title = "create a note for test"+_ts;
        note.createNote(title,"testing note","George","Flax",true);

        String description = "Check it in the HFlax's All Activity page.";
        Assert.assertTrue("note should be displayed in the HFlax's page",isNoteDraftExist(driver,description, PageAllActivity.url,title,false));
    }

    // @Test HFlax create draft and CC to Geroge.
    // CC User Geroge edit the draft content and publish it.
    // The note content should be displayed in entitiy's AllActivity page.
    public void checkCreateLinks() throws InterruptedException {

        //login and go to page url.
        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PagePrivateENote.url);
        PagePrivateENote notePE = new PagePrivateENote(driver);

        // create new note
        String draftTitle ="Test note draft Links [id=6]"+_ts;
        notePE.createNoteDraft(draftTitle,"","George","Flax",true);

        // view Draft in My Draft Page
        String description = "1.The new note draft should be displayed in Private Investment 2010 Fund's My Draft page.---"+draftTitle;
        Assert.assertTrue(isNoteDraftExist(driver,description, PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        description = "2.The new note draft should be displayed in PE's All Activity page.";

        // get Draft Edit url on the All Activity board
        String editUrl=getNoteDraftEditUrl(driver,PageAllActivity.url,draftTitle);

        // go to All Activity page check Draft
        description = "3.The note draft shouldn't be displayed in links entity (Flax,Holly) AllActivity page. ";
        Assert.assertFalse(isNoteDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle,false));

        // go to edit
        driver.get(editUrl);
        PagePrivateENote editNotePE = new PagePrivateENote(driver);
        String modifTitle="modifyed";
        editNotePE.setInputTitle(modifTitle);
        editNotePE.clickNoteButton();

        // check My Draft board
        description = "4.The published note shouldn't displayed in HFlax's My Draft page.";
        Assert.assertFalse(isNoteDraftExist(driver,description,PageMyDraft.url,draftTitle+modifTitle,true));

        // check ALL Activity board
        description = "5.The published note draft should be displayed in PE's All Activity page."+draftTitle;
        Assert.assertTrue(isNoteDraftExist(driver,description,PageAllActivity.url,draftTitle+modifTitle,true));

        description = "6.The published note draft should be displayed in links entity (Flax,Holly) AllActivity page. ";
        Assert.assertTrue(isNoteDraftExist(driver,description,PageAllActivity.urlFlax,draftTitle+modifTitle,true));

        afterEach(driver);

    }

    // @Test URL injection FB-32320
    public  void checkNoteDrattUrlinjection()throws InterruptedException {

        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PagePrivateENote.url);
        PagePrivateENote notePE = new PagePrivateENote(driver);

        // create note draft
        String draftTitle ="Note Draft URL injection [id=5]"+_ts;
        notePE.createNoteDraft(draftTitle,"","George","Flax",true);

        Thread.sleep(20* Config.timeOutSmall);

        // view note draft in My Draft page
        String description = "1.The new Note Draft should be displayed in bsg6 My Draft page.---"+draftTitle;
        Assert.assertTrue(isNoteDraftExist(driver,description,PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        description = "2.The new note draft should be displayed in bsg6's All Activity page.";
        Assert.assertTrue(isNoteDraftExist(driver,description,PageAllActivity.url,draftTitle,true));

        // from All Activity board get draft edit url
        String editDaftUrl= getNoteDraftEditUrl(driver,PageAllActivity.url,draftTitle);

        _utils.logout(driver);

        // login other no-CC user HFlax
        driver=_utils.login("HFlax",_defaultBsgPwd,false);

        // view note draft by edit url
        driver.get(editDaftUrl);
        notePE = new PagePrivateENote(driver);
        print("3.HFlax shouldn't be able to see the new note draft.");
        Assert.assertFalse(notePE.isSaveDarftButtonDisplay());

        afterEach(driver);
    }

    // @Test Close draft by accident,FB-31426
    public void checkCloseDraftByAccident() throws InterruptedException {

        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PagePrivateENote.url);
        PagePrivateENote notePE = new PagePrivateENote(driver);

        // create note draft
        String draftTitle ="Test Close note Draft by accident[id=5]"+_ts;
        notePE.setInputTitle(draftTitle);
        notePE.setSelectTopOfList();
        notePE.setInputCCUser("George");
        notePE.clickCloseButton();
        // close note

        print("1.The alert should be displayed by Clicking close note draft button.");
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Do you want to save note as a draft?"));
        driver.switchTo().alert().accept();
        Thread.sleep(60*Config.timeOutSmall);

        // check on the My Draft page
        String desc = "2.The auto-saving note draft should be display in My Draft page.";
        Assert.assertTrue(isNoteDraftExist(driver,desc,PageMyDraft.url,draftTitle,true));

        print("********3.Test Closed note successfully********");

        afterEach(driver);
    }

//    public void deleteDraft() throws InterruptedException {
//
//        WebDriver driver = beforeEach("bsg6",_defaultBsgPwd,true,PageMyDraft.url);
//        PageMyDraft draftPage = new PageMyDraft(driver);
//
//        draftPage.deleteAllDraf();
//
//        afterEach(driver);
//    }

    // @Test Edit notes drafts from All Activity Page.
    public  void checkEditNoteDraft()throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PagePrivateENote.url);
        PagePrivateENote notePE = new PagePrivateENote(driver);

        // create note
        String draftTitle ="Test note draft edit [id=4]"+_ts;
        notePE.createNoteDraft(draftTitle,"","George","",true);
        Thread.sleep(20*Config.timeOutSmall);

        // check My Draft board
        String des = "1.The new note draft should be displayed in HFlax's My Draft page.---"+draftTitle;
        Assert.assertTrue(isNoteDraftExist(driver,des,PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        des = "2.The new note draft should be displayed in HFlax's All Activity page.";
        Assert.assertTrue(isNoteDraftExist(driver,des,PageAllActivity.url,draftTitle,false));

        // get Draft Edit url
        driver.get(getNoteDraftEditUrl(driver,PageAllActivity.url,draftTitle));

        // edit note
        PagePrivateENote editNotePE = new PagePrivateENote(driver);
        String modifTitle="modifyed";
        editNotePE.setInputTitle(modifTitle);
        editNotePE.clickSaveDraftButton();
        Thread.sleep(20*Config.timeOutSmall);

        // check My Draft board
        des = "3.The modifyed note draft should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isNoteDraftExist(driver,des,PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        des = "4.The modifyed note draft should be displayed in HFlax's All Activity page."+draftTitle;
        Assert.assertTrue(isNoteDraftExist(driver,des,PageAllActivity.url,draftTitle,false));

        print("********4.Test edited note successfully.********");

        afterEach(driver);
    }

    // @Test notes drafts auto-saving without title.
    public void checkAutoSaveNoteDraftEmptyTitle() throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PagePrivateENote.url);
        PagePrivateENote notePE = new PagePrivateENote(driver);

        // create a draft note waitiing for auto-saving.
        String draftTitle ="Test auto-saving note drafts [id=3]"+_ts;
        notePE.setSelectTopOfList();
        notePE.setInputCCUser("George");
        Thread.sleep(60000);

        // check My Draft board
        String des = "1.The auto-saving note draft shouldn't be displayed in HFlax's My Draft page(The draft has no title.).---"+draftTitle;
        Assert.assertFalse(isNoteDraftExist(driver,des,PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        des = "2.The auto-saving note draft shouldn't be displayed in HFlax's All Activity page because the draft has no title.";
        Assert.assertFalse(isNoteDraftExist(driver,des,PageAllActivity.url,draftTitle,false));

        afterEach(driver);
    }

    // @Test notes drafts auto-saving.Fill with all required field.
    public  void checkAutoSaveNoteDraft() throws InterruptedException {

        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PagePrivateENote.url);
        PagePrivateENote notePE = new PagePrivateENote(driver);

        // create draft
        String draftTitle ="Test auto-saving note drafts [id=2]"+_ts;
        // notePE.createNoteDraft(draftTitle,"","George","",true);
        notePE.setInputTitle(draftTitle);
        notePE.setSelectTopOfList();
        notePE.setInputCCUser("George");

        // every 60s auto-save a Draft
        Thread.sleep(60000);

        // check My Draft board
        String des = "1.The auto-saving note draft should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isNoteDraftExist(driver,des,PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        des = "2.The auto-saving note draft should be displayed in HFlax's All Activity page.---"+draftTitle;
        Assert.assertTrue(isNoteDraftExist(driver,des,PageAllActivity.url,draftTitle,false));

        print("********Test auto-saving note successfully********");

        afterEach(driver);
    }

    // @Test notes drafts View Buket by different users such as Creator,CC User,No CC User,Super Admin.
    public  void checkNoteDraftViewBuket()throws InterruptedException
    {
        WebDriver driver = beforeEach("HFlax",_defaultBsgPwd,true,PagePrivateENote.url);
        PagePrivateENote notePE = new PagePrivateENote(driver);

        // create a Draft
        String draftTitle ="Test different users bucket [id=1]"+_ts;
        notePE.createNoteDraft(draftTitle,"","George","",true);
        Thread.sleep(50*Config.timeOutSmall);

        // check My Draft board
        String des = "1.The new note draft should be displayed in HFlax's My Draft page.";
        Assert.assertTrue(isNoteDraftExist(driver,des,PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        des = "2.The new note draft should be displayed in HFlax's All Activity page."+draftTitle;
        Assert.assertTrue(isNoteDraftExist(driver,des,PageAllActivity.url,draftTitle,false));

        _utils.logout(driver);

        // check super admin bsg6
        driver=_utils.login("bsg6",_defaultBsgPwd,false);

        // check My Draft board
        driver.get(PageMyDraft.url);
        des = "3.Super Admin bsg6 should be able to see the new draft in his My Draft page.";
        Assert.assertTrue(isNoteDraftExist(driver,des,PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        driver.get(PageAllActivity.url);
        des = "4.Super Admin bsg6 should be able to see the new draft in his All Activity page.";
        Assert.assertTrue(isNoteDraftExist(driver,des,PageAllActivity.url,draftTitle,false));

        _utils.logout(driver);

        // login with cc user's George
        driver=_utils.login("George",_otherDefaultPwd,false);

        // check My Draft board
        des = "5.CC User George should be able to see the new note draft in his My Draft page.";
        Assert.assertTrue(isNoteDraftExist(driver,des,PageMyDraft.url,draftTitle,true));
        // check ALL Activity board
        des = "6.CC User George should be able to see the new note draft in his All Activity page.";
        Assert.assertTrue(isNoteDraftExist(driver,des,PageAllActivity.url,draftTitle,false));

        _utils.logout(driver);

        // login with no CC User's testRead
        driver=_utils.login("testRead",_otherDefaultPwd,false);

        // check My Draft board
        des ="7.No CC User testRead shouldn't be able to see the new note draft in his My Draft page.";
        Assert.assertFalse(isNoteDraftExist(driver,des,PageMyDraft.url,draftTitle,true));

        // check ALL Activity board
        des = "8.No CC User testRead shouldn't be able to see the new note draft in his All Activity page.";
        Assert.assertFalse(isNoteDraftExist(driver,des,PageAllActivity.url,draftTitle,false));

        afterEach(driver);
    }
}
