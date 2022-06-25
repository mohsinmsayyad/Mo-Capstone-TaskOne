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
import org.testng.annotations.*;
import pages.SearchProduct;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SearchProductsPageTest {


    WebDriver driver;


    SearchProduct searchProduct;


    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;


    @BeforeSuite
    public void setUpReport(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/searchReport.html");
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


    @BeforeMethod
    public void openBrowser() throws IOException {
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.get("https://www.alexandnova.com/");
    }


    //Search Products on Home page
    @Test(priority = 1)
    public void SearchProductOnHomePage() throws IOException {
        test = extent.createTest("SearchProductOnHomePage", "Test Passed");
        searchProduct = new SearchProduct(driver);
        searchProduct.sendKeySearchPr("baby shoes");
        searchProduct.clickSearchButton();

        System.out.println(searchProduct.getSearchText());
        String actual = "baby shoes";
        String expected = searchProduct.getSearchText();
        Assert.assertEquals(actual,expected);


        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
    }
/*
    //EmptySearch
    @Test(priority = 2)
    public void emptySearch() throws IOException {
        test = extent.createTest("emptySearch", "Test Passed");
        searchProduct = new SearchProduct(driver);
        searchProduct.clickSearchButton();

        System.out.println(searchProduct.getEmptySearchText());
        String actual = "You haven't performed a search. " +
                "Use the search box at the top of the page to search for a product.";
        String expected = searchProduct.getEmptySearchText();
        Assert.assertEquals(actual,expected);


        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
    }
*/


    //Without entering anything in the search box click on the Search button.
   @Test(priority = 2)
     public void emptySearch() throws IOException {
        test = extent.createTest("emptySearch", "Test Passed");
        searchProduct = new SearchProduct(driver);
        searchProduct.clickSearchButton();

        System.out.println(searchProduct.getEmptySearchText());
        String actual = "You haven't performed a search. Use the search box at the top of the page to search for a product.";
        String expected = searchProduct.getEmptySearchText();
        Assert.assertEquals(actual,expected);


        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
        driver.quit();
    }
}
