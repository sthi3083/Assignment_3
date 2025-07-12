package com.sysco.qe.pages;
import com.sysco.qe.utils.PropertiesRead;
import com.syscolab.qe.core.ui.SyscoLabUI;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CartPage {

    //element locators
    //public static By cartemptyText = LocatorReader.by("cart.cartemptyText");
    //public static By cartemptyText = By.xpath("//p[@class='cart-empty__text']");
    public static By cartemptyText = By.xpath(PropertiesRead.get("cart.cartemptyText"));
    //public static By cartClearOption = By.xpath(PropertiesRead.get("cart.cartClearOption"));


//    public static void CheckEmptyCart(){
//        SyscoLabUI syscoLabUI = HomePage.getDriver();
//        String textEmptyCart = syscoLabUI.getText(cartemptyText);
//        if(textEmptyCart.contains("You haven't added anything to this basket.")){
//            System.out.println(">>>>>>>>> TC ID 2 DONE!  CART IS EMPTY!");
//        }else{
//            System.out.println(">>>>>>>>ERROR!!");
//        }
//    }

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

    public static void goToThePreviousPage(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        syscoLabUI.navigateBack();
    }


}
