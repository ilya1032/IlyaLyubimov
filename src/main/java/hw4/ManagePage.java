package hw4;

import hw4.enums.ManageMenuItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ManagePage extends AbstractPage {

    @FindBy(xpath = "//ul[contains(@class, 'tabs')]//li")
    public List<WebElement> menuItems;

    protected ManagePage(WebDriver driver) {
        super(driver);
    }

    public void clickManageMenuItem(ManageMenuItem item) {
        for (WebElement menuItem : menuItems) {
            if (menuItem.getText().trim().equals(item.getItemName())) {
                menuItem.click();
                break;
            }
        }
    }

}
