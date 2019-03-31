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

public class mantisTests {

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
    public void mantisTest() {

        //navigate to mantis.tiulp.in
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://mantis.tiulp.in/login_page.php");

        //Check title
        assertEquals(webDriver.getTitle(), "MantisBT");

        //perform login

        webDriver.findElement(By.id("username")).sendKeys("administrator");
        // TODO Локатор может быть более коротким
        webDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]")).click();

        webDriver.findElement(By.id("password")).sendKeys("rootroot");
        // TODO Локатор может быть более коротким
        webDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();

        //check login
        // TODO Локатор может быть более коротким
        assertEquals(webDriver.findElement(
                By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[3]/a/span")).getText(),
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
        webDriver.findElement(By.linkText("Manage Projects")).click();
        webDriver.findElement(By.xpath("//fieldset/button")).click();

        //check fields on Create Project menu
        try {
            webDriver.findElement(By.cssSelector("#project-name[name='name']"));
            webDriver.findElement(By.cssSelector("#project-status[name='status']"));
            webDriver.findElement(By.cssSelector("#project-inherit-global[name='inherit_global']"));
            webDriver.findElement(By.cssSelector("#project-view-state[name='view_state']"));
            webDriver.findElement(By.cssSelector("#project-description[name='description']"));
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

        webDriver.findElement(By.cssSelector("#project-name[name='name']"))
                .sendKeys("Project by Ilya Lyubimov");
        webDriver.findElement(By.cssSelector("#project-status[name='status']"))
                .findElement(By.cssSelector("[value='10']")).click();
        webDriver.findElement(By.cssSelector("#project-description[name='description']"))
                .sendKeys("Project by Ilya Lyubimov "
                        + new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime()));
        webDriver.findElement(By.cssSelector("[value='Add Project']")).click();

        //perform logout
        webDriver.findElement(By.className("user-info")).click();
        webDriver.findElement(By.xpath("//a[contains(., 'Logout')]")).click();

    }

    @AfterClass
    public void dropDownDriver() {
        webDriver.close();
    }
}
