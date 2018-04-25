package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by George on 7/18/2017.
 */
public class Utils {

    private WebDriver _driver;

//    public static String Host="http://172.16.32.100";
//
//    //timeout seting
//    public static int MID_TIME_OUT;
//    public static int SHORT_TIME_OUT;
//    public static int LONG_TIME_OUT;

    public  Utils(){
//        SHORT_TIME_OUT = 10;
//        MID_TIME_OUT = 30;
//        LONG_TIME_OUT =60;
    }

    //create selenium
    public WebDriver init_selenium(String url,boolean isNewDriver)
    {
        if(isNewDriver == true ) {
            _driver = new FirefoxDriver();
            _driver.manage().window().maximize();
        }
        _driver.get(url);
        return _driver;
    }

    public WebDriver login(String user,String pw,boolean isNewDriver){

        init_selenium(PageLogin.url,isNewDriver);

        PageLogin PageLogin = new PageLogin(_driver);
        WebDriver driver = PageLogin.login(user,pw);

        return driver;
    }

    public void logout(WebDriver driver) throws InterruptedException {
        PageLogin PageLogin = new PageLogin(driver);
        PageLogin.loginOut();
    }

    //String url = "http://172.16.32.100/backstop/crm/ManageContacts.action?display=&party_id=808345&viewType=summary";
    //get id reglax="(id=\d+)"  output  808345
    public String getReglaxCellValue(String source,String reglax)
    {
        Pattern pattern = Pattern.compile(reglax);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    //String url = "http://172.16.32.100/backstop/crm/ManageContacts.action?display=&party_id=808345&viewType=summary";
   //get id (808345)
    public String getEntityId(String source,String filed)
    {

        String allSplit[] = source.split("&");
        for (String cell :allSplit)
        {
            if (cell.contains(filed))
            {
                return cell.split("=")[1];
            }

        }
        return "";
    }

    public boolean isElementDisply(WebDriver driver, String tagName,String text) throws InterruptedException {
        List<WebElement> span = driver.findElements(By.tagName(tagName));
        for (WebElement e:span){
            if (e.getText().equals(text)){
                Actions action = new Actions(driver);
                action.moveToElement(e).perform();
                Thread.sleep(1000);
                return true;
            }
        }
        return  false;
    }

}
