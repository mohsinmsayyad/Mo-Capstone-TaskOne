package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCart {
    WebDriver driver;


    By productPrice = By.xpath("//*[@id=\"isp_search_results_container\"]/li[1]/div[2]/div[2]/span");
    By selectProduct = By.xpath("//*[@id=\"shopify-section-section-2\"]/section/div/article[1]/div/p/a");
    By size = By.xpath("//*[@id=\"bcpo-select-option-0\"]/div[1]/label");
    By color = By.xpath("//*[@id=\"bcpo-select-option-1\"]/div[2]/label");
    By quantity = By.xpath("//*[@id=\"ispbxii_1\"]");
    By cart = By.xpath("//*[@id=\"product_form_2496766902329\"]/div[4]/input");
    By addToCartMessage = By.xpath("//*[@id=\"product_form_2496766902329\"]/div[5]");


    By selectCart = By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div[1]/div/div[2]/div[2]/a");
    //*[@id="shopify-section-header"]/section/header/div[1]/div/div[2]/div[2]/a
    //*[@id="shopify-section-header"]/section/header/div[2]/div[4]/a


    By confirmationText = By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/table/tbody/tr/td[1]/div/span[1]/a");


    By increaseQuantity = By.id("updates_30882862858293");
    By updateCart = By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/div/div/div[2]/input");


    By cartQuantity = By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div[1]/div/div[2]/div[2]/a/span[2]");






    public AddToCart(WebDriver driver) {


        this.driver = driver;
    }


    public String getProductPrice(){


        return driver.findElement(productPrice).getText();
    }


    public void clickSelectProduct(){
        driver.findElement(selectProduct).click();
    }
    public void selectSize(){


        driver.findElement(size).click();
    }
    public void selectColor(){


        driver.findElement(color).click();
    }
    public void clearQuantity(){
        driver.findElement(quantity).clear();
        //driver.findElement(quantity).sendKeys(Q);


    }
    public void addQuantity(String Q){


        driver.findElement(quantity).sendKeys(Q);
    }
    public void addToCart(){


        driver.findElement(cart).click();
    }
    public String successfullyAddToCart(){


        return driver.findElement(addToCartMessage).getText();
    }
    public void clickCart(){


        driver.findElement(selectCart).click();
    }


    public void assertConfirmationProduct(){
        String text = driver.findElement(confirmationText).getText();
        System.out.println(driver.findElement(confirmationText).getText());
        if(text.contains("Holly Oversized Denim Fur Hooded")){
            assert true;
        }
        else{
            System.out.println("the assertion is not passing");
        }
    }
    public void changeProductQuantity(String quantity){
        driver.findElement(increaseQuantity).clear();
        driver.findElement(increaseQuantity).sendKeys(quantity);




    }
    public void updateCartQuantity(){
        driver.findElement(updateCart).click();


    }
    public String getFinalQuantity(){
        return driver.findElement(cartQuantity).getText();
    }
}

