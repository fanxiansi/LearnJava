package com.company;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Timestamp;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        // write your code here

        RunNoteTest noteTest = new RunNoteTest();
        RunCallTest callTest = new RunCallTest();
        RunMeetingTest meetingTest = new RunMeetingTest();
        RunRelationship relationship = new RunRelationship();

        noteTest.run();
        callTest.run();
        meetingTest.run();

//        relationship.run();
    }
}
