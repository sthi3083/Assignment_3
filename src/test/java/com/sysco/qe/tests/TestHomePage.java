package com.sysco.qe.tests;

import com.sysco.qe.functions.Cart;
import com.sysco.qe.functions.Home;
import com.sysco.qe.functions.Products;
import com.sysco.qe.functions.SelectedProduct;
import com.sysco.qe.util.TestBase;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestHomePage extends TestBase {

    //init
    @BeforeSuite(alwaysRun = true)
    public void init(ITestContext iTestContext) {
        iTestContext.setAttribute(QCENTER_FEATURE, "Regression_Day3 - PDPTest");
    }

    @Test(priority = 1, description = "QMetry TCID01 - Navigate to the Cart From the HomePage")
    public void homepage(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 01 >>");
        Home.declineCookie();
        softAssert.assertTrue(Home.isDeclineButtonClicked(), " !! ERROR ON COOKIE HANDLE !!");
        Home.clickCartIcon();
        softAssert.assertTrue(Home.isCartClicked(), "!! ERROR ON CART CLICK !!");
        softAssert.assertAll();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test(priority = 2, description = "QMetry TCID02 - Check Cart", dependsOnMethods = "homepage")
    public void checkCart(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 02 >>");
        if(Cart.isCartClearOptionVisible()){
            Cart.clearCartItems();
            softAssert.assertTrue(Cart.isCartClearClicked(), "!! CART CLEAR OPTION ERROR !!");
        } else {
            softAssert.assertTrue(Cart.isCartEmpty(), "!! CART IS NOT EMPTY. SOMETHING WENT WRONG !!");
        }
        softAssert.assertAll();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test(priority = 3, description = "QMetry TCID03 - Browse Product from Home Page Categories", dependsOnMethods = "checkCart")
    public void browseProducts(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 03 >>");
        Cart.goToThePreviousPage();
        Products.BrowseProductsOnCutleryRange();
        softAssert.assertTrue(Products.IsmainCatClicked(), " !! ERROR ON CUTLERY CATEGORY CLICK !!");
        softAssert.assertAll();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test(priority = 4, description = "QMetry TCID04 - Click On A Random Product and Save Details", dependsOnMethods = "browseProducts")
    public void clickRandomProduct(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 04 >>");
        Products.clickOnRandomProduct();
        softAssert.assertTrue(Products.IsRandomProductClicked(), "!! ERROR ON RANDOM PRODUCT CLICK !!");
        SelectedProduct.isAddBtnVisible();
        if (SelectedProduct.isAddBtnVisible()){
            System.out.println(">> Add button is visible <<");
        } else {
            System.out.println("!! ADD BUTTON IS NOT VISIBLE !!");
        }

        SelectedProduct.saveProductDetails();
        SelectedProduct.printSavedDetails();
        softAssert.assertAll();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test(priority = 5, description = "QMetry TCID05 - Add Selected Product to the Cart", dependsOnMethods = "clickRandomProduct")
    public void addProdToCart(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 05 >>");
        SelectedProduct.setQty(3);
        SelectedProduct.clickAddBtn();
        softAssert.assertTrue(SelectedProduct.isAddBtnClicked(), "!! ERROR ON ADD BTN CLICK !!");
        Home.clickCartIcon();
        softAssert.assertTrue(Home.isCartClicked(), "!! ERROR ON CART CLICK !!");
        softAssert.assertAll();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test(priority = 6, description = "QMetry TCID06 - Check Cart For Added Product Details And Verify", dependsOnMethods = "addProdToCart")
    public void CheckCartDetails(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 06 >>");
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
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test(priority = 7, description = "QMetry TCID07 - Update Cart and Verify", dependsOnMethods = "CheckCartDetails")
    public void updCartProduct(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 07 >>");
        Cart.updateCartQty();
        int expectedUpdateQty = 4;
        int actualUpdQty = Cart.getCartItemQty();
        softAssert.assertEquals(actualUpdQty,expectedUpdateQty, "!! CART QTY SHOULD BE 4. SOMETHING WENT WRONG !!" );
        softAssert.assertAll();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test(priority = 8, description = "QMetry TCID08 - Delete Product From Cart and Verify", dependsOnMethods = "updCartProduct")
    public void dltProdFromCart(){
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 08 >>");
        Cart.deleteProductFromCart();
        if(Cart.isCartEmpty()){
            System.out.println(" << Cart is empty now >>");
        } else {
            System.out.println(" !! CART IS NOT EMPTY YET. SOMETHING WENT WRONG !!");
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Test(priority = 9, description = "QMetry TCID09 - Add Product Using Quick Add Option From Cart and Verify", dependsOnMethods = "dltProdFromCart")
    public void prodQuickAddFromCart() throws InterruptedException {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" << STARTING TC 09 >>");
        String savedcode = SelectedProduct.getSavedCode();
        Cart.addProdCodeQADD(savedcode, 1);
        Cart.addProdQADDBtn();
        softAssert.assertEquals(Cart.getCartItemCode(), savedcode);
        softAssert.assertEquals(Cart.getCartItemQty(), 1);
        softAssert.assertAll();
        System.out.println("-----------------------------------------------------------------------------------");
    }





}