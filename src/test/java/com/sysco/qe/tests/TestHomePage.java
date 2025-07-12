package com.sysco.qe.tests;

import com.sysco.qe.functions.Cart;
import com.sysco.qe.functions.Home;
import com.sysco.qe.functions.Products;
import com.sysco.qe.functions.SelectedProduct;
import com.sysco.qe.util.TestBase;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestHomePage extends TestBase {

    @BeforeSuite(alwaysRun = true)
    public void init(ITestContext iTestContext) {
        iTestContext.setAttribute(QCENTER_FEATURE, "Regression_Day3 - PDPTest");
    }

    @Test(priority = 1, description = "QMetry TCID01 - Navigate to the Cart From the HomePage")
    public void homepage(){
        Home.declineCookie();
        softAssert.assertTrue(Home.isDeclineButtonClicked(), "Error on cookie handle!!");
        Home.clickCartIcon();
        softAssert.assertTrue(Home.isCartClicked(), "Error on cart click!!");
        softAssert.assertAll();
    }

//    @Test(priority = 2, description = "QMetry TCID02 - Check Cart", dependsOnMethods = "homepage")
//    public void checkCart(){
//        softAssert.assertTrue(Cart.isCartEmpty(), "Cart is not Empty!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        softAssert.assertAll();
//    }

    @Test(priority = 2, description = "QMetry TCID02 - Check Cart", dependsOnMethods = "homepage")
    public void checkCart(){
        if(Cart.isCartClearOptionVisible()){
            Cart.clearCartItems();
            softAssert.assertTrue(Cart.isCartClearClicked(), "!! CART CLEAR OPTION ERROR !!");
        } else {
            softAssert.assertTrue(Cart.isCartEmpty(), "!! CART IS NOT EMPTY. SOMETHING WENT WRONG !!");
        }
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "QMetry TCID03 - Browse Product from Home Page Categories", dependsOnMethods = "checkCart")
    public void browseProducts(){
        Cart.goToThePreviousPage();
        Products.BrowseProductsOnCutleryRange();
        softAssert.assertTrue(Products.IsmainCatClicked(), "Error on Cutlery Category Click!!");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "QMetry TCID04 - Click On A Random Product and Save Details", dependsOnMethods = "browseProducts")
    public void clickRandomProduct(){
        Products.clickOnRandomProduct();
        softAssert.assertTrue(Products.IsRandomProductClicked(), "Error on Random Product Click!!");
        SelectedProduct.isAddBtnVisible();
        if (SelectedProduct.isAddBtnVisible()){
            System.out.println(">> Add button is visible <<");
        } else {
            System.out.println("!! ADD BUTTON IS NOT VISIBLE !!");
        }

        SelectedProduct.saveProductDetails();
        SelectedProduct.printSavedDetails();
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "QMetry TCID05 - Add Selected Product to the Cart", dependsOnMethods = "clickRandomProduct")
    public void addProdToCart(){

        SelectedProduct.setQty(3);
        SelectedProduct.clickAddBtn();
        softAssert.assertTrue(SelectedProduct.isAddBtnClicked(), "Error on Add Button Click!!");
        Home.clickCartIcon();
        softAssert.assertTrue(Home.isCartClicked(), "Error on cart click!!");
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "QMetry TCID06 - Check Cart For Added Product Details And Verify", dependsOnMethods = "addProdToCart")
    public void CheckCartDetails(){
        String expectedItemCode = SelectedProduct.getSavedCode();
        int expectedQty = 3;

        String actualItemCodeInCart = Cart.getCartItemCode();
        int actualQtyInCart = Cart.getCartItemQty();

        softAssert.assertEquals(actualItemCodeInCart, expectedItemCode, "!! CART ITEM CODE IS NOT MATCHING FOR THE ITEM ADDED !!");
        softAssert.assertEquals(actualQtyInCart,expectedQty, "!! CART QTY SHOULD BE SAME AS ADDED QTY. SOMETHING WENT WRONG !!" );
        softAssert.assertAll();

        //log what we have in cart now
        System.out.println("--------------------------------------------------");
        System.out.println("---------------------CART DETAILS-----------------");
        System.out.println("--------------------------------------------------");
        System.out.println(">> CART ITEM: "+ actualItemCodeInCart);
        System.out.println(">> CART ITEM QTY: "+ actualQtyInCart);
        System.out.println("--------------------------------------------------");
    }

    @Test(priority = 7, description = "QMetry TCID07 - Update Cart and Verify", dependsOnMethods = "CheckCartDetails")
    public void updCartProduct(){

        Cart.updateCartQty();
        int expectedUpdateQty = 4;
        int actualUpdQty = Cart.getCartItemQty();
        softAssert.assertEquals(actualUpdQty,expectedUpdateQty, "!! CART QTY SHOULD BE 4. SOMETHING WENT WRONG !!" );
        softAssert.assertAll();
    }

    @Test(priority = 8, description = "QMetry TCID08 - Delete Product From Cart and Verify", dependsOnMethods = "updCartProduct")
    public void dltProdFromCart(){
        Cart.deleteProductFromCart();
        if(Cart.isCartEmpty()){
            System.out.println(" << Cart is empty now >>");
        } else {
            System.out.println(" !! CART IS NOT EMPTY YET. SOMETHING WENT WRONG !!");
        }
    }




}