package com.sysco.qe.tests;

import com.sysco.qe.functions.Login;
import com.sysco.qe.util.TestBase;
import com.sysco.qe.util.TestFeatures;
import com.sysco.qe.util.TestModules;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void init(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", TestModules.PDPTEST + "-" + TestFeatures.REGRESSION_DAY3);
    }



    @Test(description = "QMetry TC ID1")
    public void loadHomePage(){
        Login.loadYourApplication();
        System.out.println("TC ID 1 done>>>>>>>>>>>>>>>>>>>>");
        //handle the cookies
        Login.declineCookie();
        softAssert.assertTrue(Login.isDeclineButtonClicked(), "Error on cookie handle!!");
        softAssert.assertAll();//this parameter is passed as an example.
    }

}