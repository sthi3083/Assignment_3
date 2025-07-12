package com.sysco.qe.functions;
import com.sysco.qe.pages.CartPage;
import com.sysco.qe.pages.HomePage;

public class Cart {
    static HomePage homePage = new HomePage();
    static CartPage cartPage = new CartPage();

    public static boolean isCartEmpty(){
        return cartPage.isCartEmpty();
    }

    public static void goToThePreviousPage(){
        cartPage.goToThePreviousPage();
    }

    public static boolean isCartClearOptionVisible(){
        return cartPage.isCartClearOptionVisible();
    }

    public static void clearCartItems(){
        cartPage.clearCartItems();
    }

    public static boolean isCartClearClicked(){
        return cartPage.isCartClearClicked();
    }

    public static String getCartItemCode(){
        return cartPage.getCartItemCode();
    }

    public static int getCartItemQty(){
        return cartPage.getCartItemQty();
    }

    public static void updateCartQty(){
        cartPage.updateCartQty();
    }

    public static void deleteProductFromCart(){
        cartPage.deleteProductFromCart();
    }
}
