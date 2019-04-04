package hw3;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PageObjectVoid {

    private WebDriver driver;
    private WebElement userNameTextField;
    private WebElement passwordTextField;
    private WebElement loginButton;

    public PageObjectVoid(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        userNameTextField = driver.findElement(By.id("username"));
        userNameTextField.sendKeys(username);
        loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        loginButton.click();
        passwordTextField = driver.findElement(By.id("password"));
        passwordTextField.sendKeys(password);
        loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        loginButton.click();
    }

    public void createNewProject() {
        driver.findElement(By.linkText("Manage")).click();

        //open Create New Project page
        driver.findElement(By.linkText("Manage Projects")).click();
        driver.findElement(By.xpath("//fieldset/button")).click();

        //check fields on Create Project menu
        try {
            driver.findElement(By.cssSelector("#project-name[name='name']"));
            driver.findElement(By.cssSelector("#project-status[name='status']"));
            driver.findElement(By.cssSelector("#project-inherit-global[name='inherit_global']"));
            driver.findElement(By.cssSelector("#project-view-state[name='view_state']"));
            driver.findElement(By.cssSelector("#project-description[name='description']"));
        } catch (NoSuchElementException e) {
            takeScreenshot("_project_menu_error");
        }

        driver.findElement(By.cssSelector("#project-name[name='name']"))
                .sendKeys("Project by Ilya Lyubimov");
        driver.findElement(By.cssSelector("#project-status[name='status']"))
                .findElement(By.cssSelector("[value='10']")).click();
        driver.findElement(By.cssSelector("#project-description[name='description']"))
                .sendKeys("Project by Ilya Lyubimov "
                        + new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime()));
        driver.findElement(By.cssSelector("[value='Add Project']")).click();
    }

    public void createNewUser(String username, String realname, String email, String password) {
        driver.findElement(By.linkText("Manage")).click();

        //open Create New User page
        driver.findElement(By.linkText("Manage Users")).click();
        driver.findElement(By.linkText("Create New Account")).click();

        //check fields on Create Project menu
        try {
            driver.findElement(By.id("user-username"));
            driver.findElement(By.id("user-realname"));
            driver.findElement(By.id("email-field"));
            driver.findElement(By.id("user-password"));
            driver.findElement(By.id("user-verify-password"));
            driver.findElement(By.id("user-access-level"));
            driver.findElement(By.id("user-enabled"));
            driver.findElement(By.id("user-protected"));
        } catch (NoSuchElementException e) {
            takeScreenshot("_create_user_menu_error");
        }

        driver.findElement(By.id("user-username"))
                .sendKeys(username);
        driver.findElement(By.id("user-realname"))
                .sendKeys(realname);
        driver.findElement(By.id("email-field"))
                .sendKeys(email);
        driver.findElement(By.id("user-password"))
                .sendKeys(password);
        driver.findElement(By.id("user-verify-password"))
                .sendKeys(password);

        driver.findElement(By.xpath("//input[@value='Create User']")).click();
    }

    public void checkLeftBar() {
        try {
            driver.findElement(By.cssSelector(".nav.nav-list"));
        } catch (NoSuchElementException e) {
            takeScreenshot("left_bar_error");
        }
    }

    public void logout() {
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[contains(., 'Logout')]")).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getUserName() {
        return driver.findElement(By.className("user-info")).getText();
    }

    private void takeScreenshot(String errorName) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/"
                    + new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime())
                    + errorName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
