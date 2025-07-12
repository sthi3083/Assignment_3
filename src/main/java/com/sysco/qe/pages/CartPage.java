package com.sysco.qe.pages;
import com.sysco.qe.utils.PropertiesRead;
import com.syscolab.qe.core.ui.SyscoLabUI;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CartPage {

    public static By cartemptyText = By.xpath(PropertiesRead.get("cart.cartemptyText"));
    public static By cartClearOption = By.xpath(PropertiesRead.get("cart.cartClearOption"));
    public static By qtyTxtField = By.xpath(PropertiesRead.get("cart.qtyTxtField"));
    public static By addmoreBtn = By.xpath(PropertiesRead.get("cart.addmoreBtn"));
    public static By trashcan = By.xpath(PropertiesRead.get("cart.trashcan"));
    public static By deleteconfirm = By.xpath(PropertiesRead.get("cart.deleteconfirm"));

    public static By code = By.xpath(PropertiesRead.get("cart.code"));

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

    public String getCartItemCode(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        return syscoLabUI.findElement(code).getAttribute("data-code");
    }

    public int getCartItemQty(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        String val = syscoLabUI.findElement(qtyTxtField).getAttribute("value");
        return Integer.parseInt(val);
    }

    public void updateCartQty(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        syscoLabUI.clickOnVisibleElement(addmoreBtn);
    }

    public void deleteProductFromCart(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        syscoLabUI.findElement(trashcan).click();
        syscoLabUI.findElement(deleteconfirm).click();
    }




}
