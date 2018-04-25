package com.company.Pages;

/**
 * Created by George on 1/15/2018.
 */
public class Config {
    //for page modify app address.
    static final String appOnlyIpUrl="http://172.16.32.111:8080";
    static final String appBaseUrl =appOnlyIpUrl+"/backstop";

    //mill second 1000 =1 second.
    public static int timeOutSmall=1000;
    public static int timeOutMid=60000;
    public static int timeOutLog=120000;
}
