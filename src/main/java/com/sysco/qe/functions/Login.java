package com.sysco.qe.functions;

import com.sysco.qe.pages.LoginPage;

public class Login {
    static LoginPage loginPage = new LoginPage();

    public static void loadYourApplication() {
        //this parameter is passed as an example.
        loginPage.loadYourApplication();
        loginPage.sleep();
    }

    public static void declineCookie(){
        loginPage.declineCookie();
        loginPage.sleep();
    }

    public static boolean isDeclineButtonClicked(){
        return loginPage.isDeclineButtonClicked();
    }

    public static void quitApplication() {
        loginPage.quitApplication();
    }
}
