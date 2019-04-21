package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;

public class CreateProjectPage extends AbstractPage {

    @FindBy(id = "project-name")
    private WebElement projectNameField;

    @FindBy(id = "project-description")
    private WebElement projectDescriptionField;

    @FindBy(css = "[value ='Add Project']")
    private WebElement createProjectButton;

    protected CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    public void createProject(Properties properties) {
        projectNameField.sendKeys(properties.getProperty("mantis.project_name"));
        projectDescriptionField.sendKeys(properties.getProperty("mantis.project_description"));
        createProjectButton.click();
    }
}
