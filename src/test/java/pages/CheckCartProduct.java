package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckCartProduct {

    WebDriver driver;

    By total = By.xpath("//*[@id='shopify-section-cart']/section/form/table/tbody/tr/td[4]/span/span");

    By remove = By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/table/tbody/tr/td[1]/a[2]");

    By empty = By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div[1]/div/div[2]/div[2]/a/span[2]");





    public CheckCartProduct(WebDriver driver) {
        this.driver = driver;
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }
    public String assertTotal(){
        return driver.findElement(total).getText();
    }
    public void removeProduct(){
        driver.findElement(remove).click();
    }
    public String assertEmptyCart(){
        return driver.findElement(empty).getText();
    }

}
