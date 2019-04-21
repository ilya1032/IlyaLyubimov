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

public class AddProjectTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private MyViewPage myViewPage;
    private ManagePage managePage;
    private ManageProjectsPage manageProjectsPage;
    private CreateProjectPage createProjectPage;

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
    public void createProjectTest() {

        loginPage.login(properties.getProperty("mantis.admin_username"), properties.getProperty("mantis.admin_password"));
        myViewPage = new MyViewPage(this.driver);
        assertEquals(properties.getProperty("mantis.view_title"), myViewPage.getTitle());
        assertNotNull(myViewPage.getLeftSideBar());
        myViewPage.selectMenu(MenuItem.MANAGE);

        managePage = new ManagePage(this.driver);
        assertEquals(properties.getProperty("mantis.manage_title"), managePage.getTitle());
        managePage.clickManageMenuItem(ManageMenuItem.MANAGE_PROJECTS);

        manageProjectsPage = new ManageProjectsPage(this.driver);
        assertEquals(properties.getProperty("mantis.manage_projects_title"), manageProjectsPage.getTitle());
        manageProjectsPage.createProject();

        createProjectPage = new CreateProjectPage(this.driver);
        createProjectPage.createProject(properties);

    }

    @AfterMethod(alwaysRun = true)
    public void dropDown() {
        driver.close();
    }
}
