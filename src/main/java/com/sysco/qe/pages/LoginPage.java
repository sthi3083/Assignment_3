package com.sysco.qe.pages;

import com.sysco.qe.common.Constants;
import com.syscolab.qe.core.ui.SyscoLabUI;
import com.syscolab.qe.core.ui.web.SyscoLabWUI;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LoginPage {

    static SyscoLabUI syscoLabUI;
    public static By declineBtn = By.xpath("//button[@id='onetrust-reject-all-handler']");
    private boolean declineBtnClicked = false;

    /**
     * Execute test on Chrome browser
     */
    public static void loadYourApplication() {
        syscoLabUI = new SyscoLabWUI(Constants.BROWSER);
        syscoLabUI.maximizeWindow();
        syscoLabUI.navigateTo(Constants.APP_URL);
        syscoLabUI.setTimeOut(45);
    }

    public void declineCookie() {
        syscoLabUI.clickOnVisibleElement(declineBtn);
        declineBtnClicked = true;
        syscoLabUI.sleep(5);
    }

    public boolean isDeclineButtonClicked() {
        return declineBtnClicked;
    }

    public void quitApplication() {
        if (syscoLabUI != null)
            syscoLabUI.quit();
    }

    public void sleep() {
        syscoLabUI.sleep(10);
    }
}
