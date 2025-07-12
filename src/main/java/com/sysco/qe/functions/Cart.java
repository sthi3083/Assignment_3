package com.sysco.qe.functions;
import com.sysco.qe.pages.CartPage;
import com.sysco.qe.pages.HomePage;

public class Cart {
    static HomePage homePage = new HomePage();
    static CartPage cartPage = new CartPage();

//    public static void CheckEmptyCart(){
//        cartPage.CheckEmptyCart();
//        homePage.sleep();
//    }

    public static boolean isCartEmpty(){
        return cartPage.isCartEmpty();
    }

    public static void goToThePreviousPage(){
        cartPage.goToThePreviousPage();
        homePage.sleep();
    }

    public static boolean isCartClearOptionVisible(){
        return cartPage.isCartClearOptionVisible();
    }

    public static void clearCartItems(){
        cartPage.clearCartItems();
        homePage.sleep();
    }

    public static boolean isCartClearClicked(){
        return cartPage.isCartClearClicked();
    }
}
