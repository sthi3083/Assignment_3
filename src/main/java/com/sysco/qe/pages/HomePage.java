package com.sysco.qe.pages;
import com.sysco.qe.common.Constants;
import com.sysco.qe.utils.PropertiesRead;
import com.syscolab.qe.core.ui.SyscoLabUI;
import com.syscolab.qe.core.ui.web.SyscoLabWUI;
import org.openqa.selenium.By;

public class HomePage{

    public static SyscoLabUI syscoLabUI;

    //element locators
    public static By declineBtn = By.xpath(PropertiesRead.get("home.declineBtn"));
    public static By cartIcon = By.xpath(PropertiesRead.get("home.cartIcon"));

    //public static By declineBtn = By.xpath("//button[@id='onetrust-reject-all-handler']");
    //public static By cartIcon = By.xpath("//a[@href='/cart']");
    private boolean declineBtnClicked = false;
    public boolean cartClicked = false;

    //open homepage url
    public static void loadHomePage() {
        syscoLabUI = new SyscoLabWUI(Constants.BROWSER);
        syscoLabUI.maximizeWindow();
        syscoLabUI.navigateTo(Constants.APP_URL);
        syscoLabUI.setTimeOut(45);
    }

    public static SyscoLabUI getDriver(){
        return syscoLabUI;
    }

    //handle cookies
    public void declineCookie() {
        syscoLabUI.clickOnVisibleElement(declineBtn);
        declineBtnClicked = true;
        syscoLabUI.sleep(5);
    }

    //verify cookies handling
    public boolean isDeclineButtonClicked() {
        return declineBtnClicked;
    }

    //navigate to the cart
    public void clickCartIcon(){
        //syscoLabUI.switchToDefaultFrame();
        syscoLabUI.clickOnVisibleElement(cartIcon);
        cartClicked = true;
        syscoLabUI.sleep(3);
    }

    //verify navigation to the cart
    public boolean isCartClicked() {
        return cartClicked;
    }

    //teardown
    public void quitApplication() {
        if (syscoLabUI != null)
            syscoLabUI.quit();
    }

    //sleep
    public void sleep() {
        //SyscoLabUI ui = HomePage.getDriver();
        syscoLabUI.sleep(10);
    }
}
