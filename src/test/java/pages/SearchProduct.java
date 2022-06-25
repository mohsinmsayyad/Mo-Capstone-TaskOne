package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchProduct {
    WebDriver driver;


    By searchProduct = By.id("ispbxii_0");


    By clickSearch = By.className("header-search-button");


    By resultText = By.xpath("//*[@id=\"keyboard-nav-3\"]/h1/span[2]");
    //By resultText = By.linkText("Search results for ");


    By emptySearchText = By.xpath("//*[@id=\"keyboard-nav-3\"]/div[2]/p");
    //By emptySearchText = By.xpath("/html/body/div[2]/section/header/div[2]/div[4]/form/input[2]");
    ///html/body/div[2]/section/header/div[2]/div[4]/form/input[2]


    public SearchProduct(WebDriver driver) {
        this.driver = driver;
    }


    public void sendKeySearchPr(String prodName){
        driver.findElement(searchProduct).sendKeys(prodName);
    }
    public void clickSearchButton(){
        driver.findElement(clickSearch).click();
    }
    public String getSearchText(){
        return driver.findElement(resultText).getText();
    }


    public String getEmptySearchText(){
        return driver.findElement(emptySearchText).getText();
    }
}