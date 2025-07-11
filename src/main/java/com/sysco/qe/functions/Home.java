package com.sysco.qe.functions;

import com.sysco.qe.pages.HomePage;

public class Home {
    static HomePage homePage = new HomePage();

    public static void loadHomePage() {
        homePage.loadHomePage();
        homePage.sleep();
    }

    public static void declineCookie(){
        homePage.declineCookie();
        homePage.sleep();
    }

    public static boolean isDeclineButtonClicked(){
        return homePage.isDeclineButtonClicked();
    }

    public static void clickCartIcon(){
        homePage.clickCartIcon();
        homePage.sleep();
    }

    public static boolean isCartClicked(){
        return homePage.isCartClicked();
    }

    public static void quitApplication() {
        homePage.quitApplication();
    }
}
