package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageUsersPage extends AbstractPage {

    @FindBy(linkText = "Create New Account")
    private WebElement createUserButton;

    protected ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public void createUser() {
        createUserButton.click();
    }

}
