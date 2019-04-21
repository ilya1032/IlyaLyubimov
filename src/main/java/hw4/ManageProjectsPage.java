package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageProjectsPage extends AbstractPage {

    @FindBy(xpath = "//fieldset/button")
    private WebElement createProjectButton;

    protected ManageProjectsPage(WebDriver driver) {
        super(driver);
    }

    public void createProject() {
        createProjectButton.click();
    }
}
