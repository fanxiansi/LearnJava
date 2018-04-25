package com.company.Pages;
import com.company.Pages.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * Created by George on 8/3/2017.
 */
public class PageAllActivity {
    //product url for test.
    public static final String url= Config.appBaseUrl+"/fundaccounting/portfolio/PrivateEquityFundProduct.action?displayAllActivity=&fundId=12792";

    //perosn Hlax Flax url for view
    public static final String urlFlax=Config.appBaseUrl+"/crm/ManageContacts.action?display=&viewType=activities&party_id=789993";

    //note url for edit action
    private String baseEditNoteUrl=Config.appBaseUrl+"/crm/note.do?ACTION=edit&noteId=";

    //call url for edit action
    private String baseEditCallUrl=Config.appBaseUrl+"/crm/ManageMeetings.do?ACTION=LOAD&id=";

    private WebDriver driver;

    private WebElement titleAndDescription;

    public  PageAllActivity ( WebDriver dr)
    {
        this.driver = dr;
    }

    //Check whether a specific draft appears.
    public boolean CheckText(String draftitle){

        try {
            titleAndDescription = driver.findElement(By.partialLinkText(draftitle));
        }catch (Exception e1)
        {
            System.out.println("Title is not exist in All Activity page.!");
            return false;
        }

        boolean isTrue = titleAndDescription.isDisplayed();
        return isTrue;
    }

    //Get specific note draft edit url with it's ID by title
    public String getEditPageUrl(String drafTitle){

        String draftId="";
        try{
            titleAndDescription = driver.findElement(By.partialLinkText(drafTitle));
            String[] firstSplit=titleAndDescription.getAttribute("href").split("noteId=");
            draftId=firstSplit[1].split("'")[0];
        }catch (Exception e)
        {
            System.out.println("Tile is not exist in All Activity Page.");
        }

        if (draftId.isEmpty())
        {
            System.out.println("Tile is not exist in All Activity Page.");
            return draftId;
        }

        return baseEditNoteUrl+draftId;

    }
    //get specific call draft edit url with it's ID by title
    public String getEditCallUrl(String drafTitle){

        String draftId="";
        try{
            titleAndDescription = driver.findElement(By.partialLinkText(drafTitle));
            String[] firstSplit=titleAndDescription.getAttribute("href").split("id=");
            draftId=firstSplit[1].split("'")[0];
        }catch (Exception e)
        {
            System.out.println("Tile is not exist in All Activity Page.");
        }

        if (draftId.isEmpty())
        {
            System.out.println("Tile is not exist in All Activity Page.");
            return draftId;
        }

        return baseEditCallUrl+draftId;
    }

}