package com.sysco.qe.pages;

import com.sysco.qe.utils.PropertiesRead;
import com.syscolab.qe.core.ui.SyscoLabUI;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SelectedProductPage {
    public static By addBtn = By.xpath(PropertiesRead.get("p.addBtn"));
    public static By qtyinput = By.xpath(PropertiesRead.get("p.qtyinput"));

    private static final By name = By.xpath(PropertiesRead.get("p.name"));
    private static final By itemcode = By.xpath(PropertiesRead.get("p.itemcode"));

    private String savedName;
    private String savedCode;

    public boolean addBtnClicked = false;

    public void saveProductDetails(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        this.savedName = syscoLabUI.findElement(name).getText().trim();
        this.savedCode = syscoLabUI.findElement(itemcode).getText().trim();
    }

    public void printSavedDetails(){
        System.out.println("________________________");
        System.out.println(">> SAVED DETAILS >>");
        System.out.println("________________________");
        System.out.println("   NAME:  "+ savedName);
        System.out.println("   ITEM CODE:  "+ savedCode);
    }

    public String getSavedName(){ return savedName; }
    public String getSavedCode(){ return savedCode; }

    public boolean isAddBtnVisible(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        return syscoLabUI.findVisibleElement(addBtn) != null;
    }
    public void setQty(int qty){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        WebElement quantity = syscoLabUI.findElement(qtyinput);
        quantity.clear();
        quantity.sendKeys(String.valueOf(qty));
    }

    public void clickAddBtn(){
        SyscoLabUI syscoLabUI = HomePage.getDriver();
        syscoLabUI.clickOnVisibleElement(addBtn);
        addBtnClicked = true;
    }

    public boolean isAddBtnClicked() {
        return addBtnClicked;
    }



}
