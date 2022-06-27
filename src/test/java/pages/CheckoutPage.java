package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CheckoutPage {

    WebDriver driver;


    By home = By.xpath("//*[@id=\"navigation-home\"]");


    By checkout = By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/div/div/div[2]/button/span");


    By giftCart = By.xpath("//*[@id=\"checkout_reduction_code\"]");
    By email = By.name("checkout[email]");
    //By email = By.id("checkout_email");
    By firstName = By.id("checkout_shipping_address_first_name");
    By lastName = By.id("checkout_shipping_address_last_name");
    By address = By.id("checkout_shipping_address_address1");
    By city = By.id("checkout_shipping_address_city");
    By zip = By.xpath("//*[@id=\"checkout_shipping_address_zip\"]");
    By continueToShip = By.id("continue_button");
    By continueToPay = By.xpath("//*[@id=\"continue_button\"]");
    //By cardOptions = By.xpath("//header/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/span[2]/*[1]");
    //By shopPay = By.cssSelector("div.content:nth-child(4) div.wrap div.main header.main__header div.shown-if-js:nth-child(4) div.dynamic-checkout div.dynamic-checkout__content div.dynamic-checkout__buttons div.shopify-cleanslate:nth-child(2) div._38ksdFFHosgt4hh6EjDuLm._3mgStMpRn3ZERlUsxma1Bc ul._1axiYDNHVzBHv3h8UhmWtr li.kEwctmM5pguv6XkPR8mx6:nth-child(1) > div._1LP9NPTft85QosIXd3_zOV._3G6VJhJYno-AX3-X38f1TA._2zarRkvJ2j83NID3Q3t0Ix._3xia0N5Q9Mk6-VTLoOX95a");
    //By shopPay = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[4]/div[2]/label/span");
    //html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[4]/div[2]/label/span


    By creditCard = By.id("number");
    By nameOnCard = By.xpath("/html/body/form/input[2]");
    //By nameOnCard = By.xpath("//head/meta[4]");
    //By nameOnCard = By.id("name");
    By expirationDate = By.id("expiry");
    By securityCode = By.id("verification_value");

    By cardOptions = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[1]/div[2]/label");
    //("//body/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/fieldset[1]/div[1]/div[2]/div[1]/ul[1]/li[1]");
    By payNow = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[5]/div[1]/button");
    //html/body/div/div/div/main/div[1]/div/form/div[5]/div[1]/button
    //html/body/div/div/div/main/div[1]/div/form/div[5]/div[1]
    //*[@id="continue_button"]

    By paymentMessage = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[1]/div[2]/label");
    //By paymentMessage = By.xpath("//h2[@id='main-header']");
    //By paymentMessage = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/div/p/text");
    //By paymentMessage = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/div/p");

    By iFrameCard = By.xpath("//iframe[contains(@id,'card-fields-number-')]");
    By iFrameName = By.xpath("//iframe[contains(@id,'card-fields-name-')]");
    By iFrameDate = By.xpath("//iframe[contains(@id,'card-fields-expiry-')]");
    By iFrameCode = By.xpath("//iframe[contains(@id,'card-fields-verification_value-')]");


    //*[@id="card-fields-expiry-o21p9t31wti00000-scope-www.alexandnova.com"]
    //*[@id="card-fields-name-gzkfr9tbk5000000-scope-www.alexandnova.com"]
    //*[@id="card-fields-verification_value-sw6kznu9wv000000-scope-www.alexandnova.com"]


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage() {
        driver.findElement(home).click();
    }

    public void clickCheckout() {
        driver.findElement(checkout).click();
    }

    public void giftCartBoxCheck() {
        if (driver.findElement(giftCart).isEnabled()) {
            System.out.println("the edit box is enabled");
            assert true;
        } else {
            System.out.println("the edit box is not enabled");
        }
    }


    public void setSendEmail(String Email) {
        driver.findElement(email).sendKeys(Email);
    }


    public void setSendFirstName(String FName) {
        driver.findElement(firstName).sendKeys(FName);
    }


    public void setSendLastName(String LName) {
        driver.findElement(lastName).sendKeys(LName);
    }


    public void setSendAddress(String add) {
        driver.findElement(address).sendKeys(add);
    }


    public void setSendCity(String ct) {
        driver.findElement(city).sendKeys(ct);
    }


    public void setSendZip(String zipCode) {
        driver.findElement(zip).sendKeys(zipCode);
    }


    public void clickContinue() {
        driver.findElement(continueToShip).click();
    }


    public void clickContinueToPay() {


        driver.findElement(continueToPay).click();
    }


    public void assertVisaCard() {
        //String shopPayOption = driver.findElement(shopPay).getText();
        String shopPayOption = driver.findElement(cardOptions).getText();
        System.out.println(shopPayOption);
        if (shopPayOption.contains("VISA")) {
        //if (shopPayOption.contains("Shop Pay")) {
            assert true;
        } else {
            assert false;
        }
    }


    public void sendCreditCardNumber(String CardNum) {
        driver.findElement(creditCard).sendKeys(CardNum);
        //driver.switchTo().defaultContent();
        driver.switchTo().parentFrame();
    }


    public void sendName(String name) {
        driver.findElement(nameOnCard).sendKeys(name);
        //driver.switchTo().defaultContent();
        driver.switchTo().parentFrame();
    }


    public void sendExpDate(String dateInCard) {
        driver.findElement(expirationDate).sendKeys(dateInCard);
        //driver.switchTo().defaultContent();
        driver.switchTo().parentFrame();
    }
    public void sendSecurityCode(String code) {
        driver.findElement(securityCode).sendKeys(code);
        //driver.switchTo().defaultContent();
        //driver.switchTo().parentFrame();
    }
    public void clickOnPayNow(){
        driver.findElement(payNow).click();
    }
    public void getErrorMessage(){
        String error = driver.findElement(paymentMessage).getText() ;
        System.out.println(error);


        if(error.contains("Your payment details couldn’t be verified.")){
            assert true;
        }
        else{
            assert false;
        }
    }
    public void switchFrameCard()
    {
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        int numberOfTags = elements.size();
        System.out.println("iframes: " +numberOfTags);
        driver.switchTo().frame(driver.findElement(iFrameCard));


    }
    public void switchFrameName()
    {
        driver.switchTo().frame(driver.findElement(iFrameName));



    }
    public void switchFrameDate()
    {
        driver.switchTo().frame(driver.findElement(iFrameDate));


    }
    public void switchFrameCode()
    {
        driver.switchTo().frame(driver.findElement(iFrameCode));


    }
    public void scrollDown(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,44");
    }
}
