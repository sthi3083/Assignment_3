package com.sysco.qe.functions;

import com.sysco.qe.pages.HomePage;
import com.sysco.qe.pages.SelectedProductPage;

public class SelectedProduct {

    static HomePage homePage = new HomePage();
    static SelectedProductPage selectedProductPage = new SelectedProductPage();

    public static boolean isAddBtnVisible(){
        return selectedProductPage.isAddBtnVisible();
    }

    public static void saveProductDetails(){
        selectedProductPage.saveProductDetails();
        homePage.sleep();
    }

    public static void printSavedDetails(){
        selectedProductPage.printSavedDetails();
        homePage.sleep();
    }

    public static void setQty(int qty){
        selectedProductPage.setQty(qty);
        homePage.sleep();
    }

    public static void clickAddBtn(){
        selectedProductPage.clickAddBtn();
        homePage.sleep();
    }

    public static boolean isAddBtnClicked(){
        return selectedProductPage.isAddBtnClicked();
    }

}
