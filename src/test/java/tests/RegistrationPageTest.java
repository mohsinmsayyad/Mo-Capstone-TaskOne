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

public class RegistrationPageTest {

    WebDriver driver;
    Registration registration;

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport(){

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/registrationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Nabila.home-server.local");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Nabila");
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
    //TC 001 New User Registration page
    @Test(priority = 1)
    public void NewUserRegistrationPage() throws InterruptedException, IOException {
        test = extent.createTest("NewUserRegistrationPage", "Test Passed");
        registration = new Registration(driver);
        Thread.sleep(4000);
        registration.clickAccount();
        //Thread.sleep(3000);
        registration.clickRegister();
        System.out.println(registration.getTextLogIn());
        String expected = "Sign up";
        String actual = registration.getTextLogIn();
        Assert.assertEquals(actual,expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screanShots/image.png"));
    }
    //TC 002 Verify Register New User.
    @Test(enabled = false)
    public void verifyRegistrationUserTest() throws IOException {
        test = extent.createTest("verifyRegistrationUserTest", "Test Passed");
        registration = new Registration(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        registration.sendFirstName("nabi1");
        registration.sendLastName("azz");
        registration.sendEmail("nab3@email.com");
        registration.sendPassword("12345");
        registration.clickRegistration();
        System.out.println(registration.getTextLogPage());
        String expected1 = "Sign up";
        String actual1 = registration.getTextLogPage();
        Assert.assertEquals(actual1,expected1);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
    }
    //TC 003 Email validation
    @Test(priority = 2)
    public void emailValidation() throws IOException, InterruptedException {
        test = extent.createTest("emailValidation", "Test fail");
        registration = new Registration(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        registration.clickAccount();
        registration.clickRegister();
        Thread.sleep(4000);
        registration.sendFirstName("nab");
        registration.sendLastName("azz");
        registration.sendEmail("nabemailcom");
        registration.sendPassword("12345");
        registration.clickRegistration();
        System.out.println(registration.getErrorMessage());
        String expected = "Sorry! Please try that again.";
        String actual = registration.getErrorMessage();
        Assert.assertEquals(actual,expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
    }
    //TC 004 Required / mandatory fields
    @Test(priority = 3)
    public void RequiredMandatoryFields() throws InterruptedException {
        test = extent.createTest("emailValidation", "Test fail");
        registration = new Registration(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //registration.clickAccount();
        registration.clickRegister();
        Thread.sleep(4000);
        registration.clickRegistration();
        Thread.sleep(4000);
        String expected = "Sorry! Please try that again.";
        String actual = registration.getErrorMessage();
        Assert.assertEquals(actual,expected);
    }

    /*@Test(priority = 4)
    public void negativeTestingForMandatoryFields() throws InterruptedException, IOException {
        test = extent.createTest("emailValidation", "Test fail");
        registration = new Registration(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //registration.clickAccount();
        //registration.clickRegister();
        Thread.sleep(4000);
        registration.sendFirstName("");
        registration.sendLastName("");
        registration.sendEmail("");
        registration.sendPassword("");
        registration.clickRegistration();
        System.out.println(registration.getErrorMessage());
        Thread.sleep(6000);
        String expected = "Sorry! Please try that again.";
        String actual = registration.getErrorMessage();
        Assert.assertEquals(actual,expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
    }*/

    //TC 005 Verify if the password required rules are not satisfied in the password
    @Test(priority = 4, enabled = false)
    public void negativeTestingForLogin() throws InterruptedException, IOException {
        registration = new Registration(driver);
        registration.clickAccount();
        registration.sendEmailLogIn("nab3@email.com");
        registration.sendPassLogIn("1234");
        Thread.sleep(6000);
        registration.clickOnLogIn();
        System.out.println(registration.getWrongPassMessage());
        String expected = "Sorry! Please try that again.";
        String actual = registration.getWrongPassMessage();
        Assert.assertEquals(actual,expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/resources/screenShots/image.png"));
    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}
