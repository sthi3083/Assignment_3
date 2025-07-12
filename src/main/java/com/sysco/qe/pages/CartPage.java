package com.sysco.qe.pages;
import com.sysco.qe.utils.PropertiesRead;
import com.syscolab.qe.core.ui.SyscoLabUI;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CartPage {

    public static By cartemptyText = By.xpath(PropertiesRead.get("cart.cartemptyText"));
    public static By cartClearOption = By.xpath(PropertiesRead.get("cart.cartClearOption"));
    public boolean cartcleared = false;

    public boolean isCartEmpty(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        try{
            String actualMsg = syscoLabUI.getText(cartemptyText);
            String expectedMsg = "You haven't added anything to this basket.";
            return actualMsg.equals(expectedMsg);
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isCartClearOptionVisible(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        return syscoLabUI.findVisibleElement(cartClearOption) != null;
    }

    public void clearCartItems(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        syscoLabUI.clickOnVisibleElement(cartClearOption);
        cartcleared = true;
    }

    public boolean isCartClearClicked(){
        return cartcleared;
    }

    public void goToThePreviousPage(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        syscoLabUI.navigateBack();
    }



}
