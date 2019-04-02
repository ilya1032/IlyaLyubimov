package hw2;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddUserTest {

    private static WebDriver webDriver;


    //set up path to OS compatible chrome driver
    @BeforeClass
    public void setUpDriver() {
        String currentOSDriver = "";

        if (SystemUtils.IS_OS_LINUX)
            currentOSDriver = "unix/chromedriver";

        if (SystemUtils.IS_OS_WINDOWS)
            currentOSDriver = "windows/chromedriver.exe";

        if (SystemUtils.IS_OS_MAC)
            currentOSDriver = "macOS/chromedriver";

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                + "/src/test/resources/webdriver/chrome/" + currentOSDriver);
    }

    @Test
    public void addUserTest() {

        //navigate to mantis.tiulp.in
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://mantis.tiulp.in/login_page.php");

        //Check title
        assertEquals(webDriver.getTitle(), "MantisBT");

        //perform login

        webDriver.findElement(By.id("username")).sendKeys("administrator");
        webDriver.findElement(By.xpath("//input[@value='Login']")).click();

        webDriver.findElement(By.id("password")).sendKeys("rootroot");
        webDriver.findElement(By.xpath("//input[@value='Login']")).click();

        //check login
        assertEquals(webDriver.findElement(
                By.xpath("//span[@class='user-info']")).getText(),
                "administrator");

        //check left side menu
        try {
            webDriver.findElement(By.cssSelector(".nav.nav-list"));
        } catch (NoSuchElementException e) {
            File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/"
                        + new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime())
                        + "_left_menu_error"));
                fail();
            } catch (IOException e1) {
                e1.printStackTrace();
                fail();
            }
        }

        webDriver.findElement(By.linkText("Manage")).click();

        //check Manage MantisBT opened
        assertEquals(webDriver.getTitle(), "Manage - MantisBT");

        //open Create New Project page
        webDriver.findElement(By.linkText("Manage Users")).click();
        webDriver.findElement(By.linkText("Create New Account")).click();

        //check fields on Create Project menu
        try {
            webDriver.findElement(By.id("user-username"));
            webDriver.findElement(By.id("user-realname"));
            webDriver.findElement(By.id("email-field"));
            webDriver.findElement(By.id("user-password"));
            webDriver.findElement(By.id("user-verify-password"));
            webDriver.findElement(By.id("user-access-level"));
            webDriver.findElement(By.id("user-enabled"));
            webDriver.findElement(By.id("user-protected"));
        } catch (NoSuchElementException e) {
            File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/"
                        + new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime())
                        + "_create_project_menu_error"));
                fail();
            } catch (IOException e1) {
                e1.printStackTrace();
                fail();
            }
        }

        webDriver.findElement(By.id("user-username"))
                .sendKeys("ilya1032");
        webDriver.findElement(By.id("user-realname"))
                .sendKeys("Ilya");
        webDriver.findElement(By.id("email-field"))
                .sendKeys("ilya@gmail.com");
        webDriver.findElement(By.id("user-password"))
                .sendKeys("123456");
        webDriver.findElement(By.id("user-verify-password"))
                .sendKeys("123456");

        webDriver.findElement(By.xpath("//input[@value='Create User']")).click();

        //perform logout
        webDriver.findElement(By.className("user-info")).click();
        webDriver.findElement(By.xpath("//a[contains(., 'Logout')]")).click();

        //Check title
        assertEquals(webDriver.getTitle(), "MantisBT");

        //perform login

        webDriver.findElement(By.id("username")).sendKeys("ilya1032");
        webDriver.findElement(By.xpath("//input[@value='Login']")).click();

        webDriver.findElement(By.id("password")).sendKeys("123456");
        webDriver.findElement(By.xpath("//input[@value='Login']")).click();

        //check login
        assertEquals(webDriver.findElement(
                By.xpath("//span[@class='user-info']")).getText(),
                "ilya1032");

    }

    @AfterClass
    public void dropDownDriver() {
        webDriver.close();
    }
}