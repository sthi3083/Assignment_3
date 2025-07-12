package com.sysco.qe.functions;

import com.sysco.qe.pages.HomePage;
import com.sysco.qe.pages.ProductsPage;

public class Products {
    static ProductsPage productsPage = new ProductsPage();
    static HomePage homePage = new HomePage();

    public static void BrowseProductsOnCutleryRange(){
        productsPage.BrowseProductsOnCutleryRange();
        homePage.sleep();
    }
    public static boolean IsmainCatClicked(){
        return productsPage.IsmainCatClicked();
    }

    public static void clickOnRandomProduct(){
        productsPage.clickOnRandomProduct();
        homePage.sleep();
    }

    public static boolean IsRandomProductClicked(){
        return productsPage.IsRandomProductClicked();
    }

}
