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
import pages.Registration;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginRegisteredUserTest {

    WebDriver driver;
    Registration registration;

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport(){

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/loginTest.html");
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


    @Test(priority = 1)
    public void LogInUser() throws IOException, InterruptedException {
        test = extent.createTest("LogInUser", "Test Passed");
        registration = new Registration(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        registration.clickAccount();
        registration.sendEmailLogIn("mohsinmsayyad@email.com");
        registration.sendPassLogIn("12345");
        registration.clickOnLogIn();
        Thread.sleep(6000);
        //System.out.println(registration.getWelcomeTxt());
        Thread.sleep(6000);
        String expected = "Log in";
        String actual = registration.getWelcomeTxt();
        Assert.assertEquals(actual,expected);


        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));


    }
    //Verify when passing incorrect Email and correct password to Login page
    @Test(priority = 2)
    public void verifyIncorrectEmailCorrectPass() throws InterruptedException {
        test = extent.createTest("verifyIncorrectEmailCorrectPass", "Test fail");
        registration = new Registration(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        registration.clickAccount();
        Thread.sleep(4000);
        registration.sendEmailLogIn("sayyadmohsin@email.com");
        registration.sendPassLogIn("12345");
        registration.clickOnLogIn();
        Thread.sleep(6000);
        System.out.println(registration.getErrorMessage());
        String expected = "Sorry! Please try that again.";
        String actual = registration.getErrorMessage();
        Assert.assertEquals(actual,expected);
    }
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}
