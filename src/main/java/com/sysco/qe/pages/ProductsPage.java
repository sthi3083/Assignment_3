package com.sysco.qe.pages;

import com.sysco.qe.utils.PropertiesRead;
import com.syscolab.qe.core.ui.SyscoLabUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProductsPage {
    public static By mainCategory = By.xpath(PropertiesRead.get("products.mainCategory"));
    public static By randomProduct = By.xpath(PropertiesRead.get("products.randomProduct"));

    public boolean mainCatClicked = false;
    public boolean randomProdClicked = false;

    public void BrowseProductsOnCutleryRange(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        syscoLabUI.clickOnVisibleElement(mainCategory);
        mainCatClicked = true;
    }

    public boolean IsmainCatClicked() {
        return mainCatClicked;
    }

    public void clickOnRandomProduct(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        List<WebElement> links = syscoLabUI.findElements(randomProduct);
        int count = links.size();
        if(count == 0){
            throw new IllegalStateException("No products availble!!!!!!!!!!!");
        }
        int idx = ThreadLocalRandom.current().nextInt(count);
        WebElement chosenProd = links.get(idx);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>SELCTED :"+idx+ chosenProd.getAttribute("href"));
        chosenProd.click();
        syscoLabUI.sleep(4);
        randomProdClicked = true;
    }

    public boolean IsRandomProductClicked() {
        return randomProdClicked;
    }
}
