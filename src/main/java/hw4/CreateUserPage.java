package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;

public class CreateUserPage extends AbstractPage {

    @FindBy(id = "user-username")
    private WebElement usernameField;

    @FindBy(id = "user-realname")
    private WebElement realnameField;

    @FindBy(id = "email-field")
    private WebElement emailField;

    @FindBy(id = "user-password")
    private WebElement passwordField;

    @FindBy(id = "user-verify-password")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@value='Create User']")
    private WebElement createUserButton;

    protected CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public void createUser(Properties properties) {
        usernameField.sendKeys(properties.getProperty("mantis.user"));
        realnameField.sendKeys(properties.getProperty("mantis.realname"));
        passwordField.sendKeys(properties.getProperty("mantis.password"));
        confirmPasswordField.sendKeys(properties.getProperty("mantis.password"));
        emailField.sendKeys(properties.getProperty("mantis.email"));
        createUserButton.click();
    }
}
