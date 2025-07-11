package com.sysco.qe.tests;

import com.sysco.qe.functions.Home;
import com.sysco.qe.util.TestBase;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestHomePage extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void init(ITestContext iTestContext) {
        iTestContext.setAttribute(QCENTER_FEATURE, "Regression_Day3 - PDPTest");
    }

    @Test(description = "QMetry TC ID 01")
    public void loadHomePage(){
        Home.declineCookie();
        softAssert.assertTrue(Home.isDeclineButtonClicked(), "Error on cookie handle!!");

        Home.clickCartIcon();
        Home.isCartClicked();
        softAssert.assertTrue(Home.isCartClicked(), "Error on cart click!!");
        softAssert.assertAll();
    }

}