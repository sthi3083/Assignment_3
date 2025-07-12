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
}
