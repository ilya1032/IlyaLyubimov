package hw3;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class AddUserTest {

    private static WebDriver webDriver;
    private PageObjectVoid pageObjectVoid;

    @BeforeSuite(alwaysRun = true)
    public void setUpDriver() {
        ChromeDriverManager.chromedriver().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void initDriver() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        webDriver.get("https://mantis.tiulp.in/login_page.php");
        pageObjectVoid = new PageObjectVoid(webDriver);
    }

    @Test
    public void addUserTest() {

        //Check title
        assertEquals(pageObjectVoid.getPageTitle(), "MantisBT");

        //perform login
        pageObjectVoid.login("administrator", "rootroot");

        //check login
        assertEquals(pageObjectVoid.getUserName(), "administrator");

        pageObjectVoid.checkLeftBar();

        pageObjectVoid.createNewUser("ilya1032", "Ilya", "ilya@gmail.com", "123456");

        //perform logout
        pageObjectVoid.logout();

        //Check title
        assertEquals(pageObjectVoid.getPageTitle(), "MantisBT");

        //perform login
        pageObjectVoid.login("ilya1032", "123456");

        //check login
        assertEquals(pageObjectVoid.getUserName(), "ilya1032");

    }

    @AfterClass
    public void dropDownDriver() {
        webDriver.close();
    }
}