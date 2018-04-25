package com.company.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
/**
 * Created by George on 10/17/2017.
 */
public class PageAddHolding {
    private  WebDriver _driver;

    //element region
    @FindBy(id ="product")
    private WebElement inputProduct;
    @FindBy(id ="holdingName")
    private WebElement inputHoldingName;
    @FindBy(id="illiquidHoldingType")
    private WebElement radioHoldingType;
    @FindBy(id="peFund")
    private WebElement inputInvested;
    @FindBy(id="addHolding")
    private WebElement buttonAddHolding;
    //element region

    public PageAddHolding(WebDriver driver)
    {
        this._driver = driver;
        PageFactory.initElements(driver,this);
    }
}
