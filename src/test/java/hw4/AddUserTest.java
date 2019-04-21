package hw4;

import hw4.enums.ManageMenuItem;
import hw4.enums.MenuItem;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class AddUserTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private MyViewPage myViewPage;
    private ManagePage managePage;
    private ManageUsersPage manageUsersPage;
    private CreateUserPage createUserPage;

    private Properties properties;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        ChromeDriverManager.chromedriver().setup();
        FileInputStream propertiesFile = new FileInputStream("src/test/resources/hw4.properties");
        properties = new Properties();
        properties.load(propertiesFile);
    }

    @BeforeMethod(alwaysRun = true)
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(properties.getProperty("mantis.url"));
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginTest() {
        assertEquals(properties.getProperty("mantis.main_title"), loginPage.getPageTitle());

        loginPage.login(properties.getProperty("mantis.admin_username"), properties.getProperty("mantis.admin_password"));
    }

    @Test
    public void createUserTest() {

        loginPage.login(properties.getProperty("mantis.admin_username"), properties.getProperty("mantis.admin_password"));
        myViewPage = new MyViewPage(this.driver);
        assertEquals(properties.getProperty("mantis.view_title"), myViewPage.getTitle());
        assertNotNull(myViewPage.getLeftSideBar());
        myViewPage.selectMenu(MenuItem.MANAGE);

        managePage = new ManagePage(this.driver);
        assertEquals(properties.getProperty("mantis.manage_title"), managePage.getTitle());
        managePage.clickManageMenuItem(ManageMenuItem.MANAGE_USERS);

        manageUsersPage = new ManageUsersPage(this.driver);
        manageUsersPage.createUser();

        createUserPage = new CreateUserPage(this.driver);
        createUserPage.createUser(properties);
        createUserPage.logout();

        loginPage.login(properties.getProperty("mantis.user"), properties.getProperty("mantis.password"));
        assertEquals(properties.getProperty("mantis.user"), myViewPage.getUsername());
    }

    @AfterMethod(alwaysRun = true)
    public void dropDown() {
        loginPage.logout();
        driver.close();
    }

}
