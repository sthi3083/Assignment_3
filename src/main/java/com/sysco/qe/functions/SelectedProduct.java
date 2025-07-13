package com.sysco.qe.functions;

import com.sysco.qe.pages.HomePage;
import com.sysco.qe.pages.SelectedProductPage;

public class SelectedProduct {
    static SelectedProductPage selectedProductPage = new SelectedProductPage();

    public static boolean isAddBtnVisible(){
        return selectedProductPage.isAddBtnVisible();
    }

    public static void saveProductDetails(){
        selectedProductPage.saveProductDetails();

    }

    public static void printSavedDetails(){
        selectedProductPage.printSavedDetails();

    }

    public static void setQty(int qty){
        selectedProductPage.setQty(qty);

    }

    public static void clickAddBtn(){
        selectedProductPage.clickAddBtn();

    }

    public static boolean isAddBtnClicked(){
        return selectedProductPage.isAddBtnClicked();
    }

    public static String getSavedCode(){
        return selectedProductPage.getSavedCode();
    }

    public static String getSavedName(){
        return selectedProductPage.getSavedName();
    }

}
