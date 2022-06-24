package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import library.SelectBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AddToCart;
import pages.CheckCartProduct;
import pages.SearchProduct;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ProductAddToCartTest {



    WebDriver driver;


    AddToCart addToCart;
    SearchProduct searchProduct;


    CheckCartProduct checkCartProduct;


    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;


    @BeforeSuite
    public void setUpReport(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/productAddReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "MoSayyad.home-server.local");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "MoSayyad");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting Google download pictures report");
        htmlReporter.config().setReportName("Google Search and Download Pictures Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }


    @BeforeTest
    public void openBrowser() throws IOException {
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.get("https://www.alexandnova.com/");
    }




    //Verify the prices must show up for products on the product page
    @Test(enabled = false)
    public void verifyPrice() throws IOException {
        test = extent.createTest("verifyPrice", "Test Passed");
        searchProduct = new SearchProduct(driver);
        searchProduct.sendKeySearchPr("baby shoes");
        searchProduct.clickSearchButton();
        addToCart = new AddToCart(driver);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
        System.out.println(addToCart.getProductPrice());
        String expected = addToCart.getProductPrice();
        String actual = "$19.95";
        Assert.assertEquals(actual,expected);


    }
    //Add Product to the cart and verify if product Is added to cart page
    @Test(priority = 1)
    public void addProductToCart() throws InterruptedException, IOException {
        test = extent.createTest("addProductToCart", "Test Passed");
        addToCart = new AddToCart(driver);
        addToCart.clickSelectProduct();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        addToCart.selectSize();
        addToCart.selectColor();
        addToCart.clearQuantity();
        addToCart.addQuantity("2");
        addToCart.addToCart();
        Thread.sleep(3000);
        System.out.println(addToCart.successfullyAddToCart());
        String expected = "Holly Oversized Denim Fur Hooded Winter Coat Jacket - 12-18 Months / Blue has been successfully added to your cart. Feel free to continue shopping or check out.";
        String actual = addToCart.successfullyAddToCart();
        Assert.assertEquals(actual,expected);


        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));


    }
    //Refresh the page and verify if items are still present in the cart
    @Test(priority = 2)
    public void refreshAndCheck() throws InterruptedException, IOException {
        test = extent.createTest("refreshAndCheck", "Test Passed");
        addToCart = new AddToCart(driver);
        addToCart.clickCart();
        Thread.sleep(3000);
        checkCartProduct = new CheckCartProduct(driver);
        checkCartProduct.refreshPage();
        addToCart.assertConfirmationProduct();


        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
    }
    //Increase the quantity of the product and verify if it is showing up in cart
    @Test(priority = 3)
    public void addedToCartQuantityCheck() throws InterruptedException {
        test = extent.createTest("addedToCartQuantityCheck", "Test Passed");
        addToCart = new AddToCart(driver);
        addToCart.changeProductQuantity("3");
        addToCart.updateCartQuantity();
        Thread.sleep(3000);
        System.out.println(addToCart.getFinalQuantity());
        String actual = addToCart.getFinalQuantity();
        String expected = "3";
        Assert.assertEquals(actual,expected);
    }
    //Verify Quantity of the products matches with amount displayed in cart
    @Test(priority = 4)
    public void productsValueEqualsTotalPrice() throws InterruptedException {
        test = extent.createTest("productsValueEqualsTotalPrice", "Test Passed");
        checkCartProduct = new CheckCartProduct(driver);
        System.out.println(checkCartProduct.assertTotal());
        Thread.sleep(3000);
        String expected = "$299.85 USD";
        String actual = checkCartProduct.assertTotal();
        Assert.assertEquals(expected, actual);
    }
    //Remove Product from cart: Verify that the Product should be removed from the cart and the Cart icon should show 0 items.
    @Test(priority = 5)
    public void emptyCart() throws InterruptedException {
        test = extent.createTest("emptyCart", "Test Passed");
        Thread.sleep(4000);
        checkCartProduct.removeProduct();
        Thread.sleep(4000);
        System.out.println(checkCartProduct.assertEmptyCart());
        String expected = "0";
        String actual = checkCartProduct.assertEmptyCart();
        Assert.assertEquals(actual, expected);
    }


    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }


}
