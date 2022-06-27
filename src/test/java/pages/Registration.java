package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Registration {



    WebDriver driver;


    By account = By.id("customer_login_link");
    By register = By.xpath("//*[@id=\"keyboard-nav-3\"]/div[2]/div[3]/a");
    By firstName = By.id("ispbxii_1");
    By lastName = By.id("ispbxii_2");
    By className = By.xpath("//h1[@class='page-title']");
    By email = By.id("ispbxii_3");
    By password = By.xpath("//*[@id=\"create_customer\"]/div[4]/input");
    By registration = By.xpath("//*[@id=\"create_customer\"]/div[5]/input");

    By logText = By.xpath("//*[@id=\"keyboard-nav-3\"]/h1"); //*[@id="keyboard-nav-3"]/h1
    By logInEmail = By.id("ispbxii_1");
    By passLogIn = By.name("customer[password]");
    By logInUser = By.xpath("//*[@id=\"customer_login\"]/div[3]/input");
    By welcomeText = By.xpath("//*[@id=\"keyboard-nav-3\"]/h1");
    //By errorMessageText = By.xpath("//*[@id=\"customer_login\"]/p");
    By errorMessageText = By.xpath("//*[@id=\"ispbxii_1\"]");
    By wrongPassword = By.xpath("//*[@id=\"customer_login\"]/p");

    public Registration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickAccount(){
        driver.findElement(account).click();
    }
    public void clickRegister(){
        driver.findElement(register).click();

    }
    public String getTextLogIn(){
        return driver.findElement(className).getText();
    }
    public void sendFirstName(String firstN){
        driver.findElement(firstName).sendKeys(firstN);
    }
    public void sendLastName(String lastN){
        driver.findElement(lastName).sendKeys(lastN);
    }
    public void sendEmail(String M){
        driver.findElement(email).sendKeys(M);
    }
    public void sendPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }
    public void clickRegistration(){
        driver.findElement(registration).click();

    }
    public String getTextLogPage(){
        return driver.findElement(logText).getText();
    }
    public void sendEmailLogIn(String EM){
        driver.findElement(logInEmail).sendKeys(EM);
    }
    public void sendPassLogIn(String PW){
        driver.findElement(passLogIn).sendKeys(PW);
    }
    public void clickOnLogIn(){
        driver.findElement(logInUser).click();
    }
    public String getWelcomeTxt(){
        return driver.findElement(welcomeText).getText();
    }
    public String getErrorMessage(){
        return driver.findElement(errorMessageText).getText();
    }
    public String getWrongPassMessage(){
        return driver.findElement(wrongPassword).getText();
    }
}


