package hw4;

import hw4.enums.MenuItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AbstractPage extends AbstractBase {

    @FindBy(className = "user-info")
    protected WebElement userInfoButton;

    @FindBy(xpath = "//a[contains(., 'Logout')]")
    protected WebElement logoutButton;

    protected LeftSideBar leftSideBar;

    protected AbstractPage(WebDriver driver) {
        super(driver);
        this.leftSideBar = new LeftSideBar(driver);
    }

    public void selectMenu(MenuItem item) {
        this.leftSideBar.clickMenuItem(item);
    }

    public LeftSideBar getLeftSideBar() {
        return leftSideBar;
    }

    public void logout() {
        userInfoButton.click();
        logoutButton.click();
    }

    public String getUsername() {
        return userInfoButton.getText();
    }
}
