package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class ViewIssuePage extends AbstractPage {

    @FindBy(id = "show_priority_filter")
    private WebElement priorityFilterButton;

    @FindBy(xpath = "//select[contains(@name, 'priority')]")
    private WebElement priorityDropDownMenu;

    @FindBy(id = "show_severity_filter")
    private WebElement severityFilterButton;

    @FindBy(xpath = "//select[contains(@name, 'severity')]")
    private WebElement severityDropDownMenu;

    @FindBy(id = "show_status_filter")
    private WebElement statusFilterButton;

    @FindBy(xpath = "//select[contains(@name, 'status')]")
    private WebElement statusDropDownMenu;

    @FindBy(id = "do_filter_by_date_filter")
    private WebElement dateFilterButton;

    @FindBy(xpath = "//select[contains(@name, 'start_year')]")
    private WebElement startYearDropDownMenu;

    @FindBy(xpath = "//select[contains(@name, 'start_month')]")
    private WebElement startMonthDropDownMenu;

    @FindBy(xpath = "//select[contains(@name, 'start_day')]")
    private WebElement startDayDropDownMenu;

    @FindBy(xpath = "//select[contains(@name, 'end_year')]")
    private WebElement endYearDropDownMenu;

    @FindBy(xpath = "//select[contains(@name, 'end_month')]")
    private WebElement endMonthDropDownMenu;

    @FindBy(xpath = "//select[contains(@name, 'end_day')]")
    private WebElement endDayDropDownMenu;

    @FindBy(xpath = "//input[@value = 'Apply Filter']")
    private WebElement applyFilterButton;

    @FindBy(xpath = "//table[@id='buglist']/tbody/tr[1]")
    private WebElement issueRecord;

    protected ViewIssuePage(WebDriver driver) {
        super(driver);
    }

    public void filterIssues(Properties properties) {

        priorityFilterButton.click();
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(priorityDropDownMenu));
        Select prioritySelect = new Select(priorityDropDownMenu);
        prioritySelect.selectByVisibleText(properties.getProperty("mantis.issue_priority"));

        severityFilterButton.click();
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(severityDropDownMenu));
        Select severitySelect = new Select(severityDropDownMenu);
        severitySelect.selectByVisibleText(properties.getProperty("mantis.issue_severity"));

        statusFilterButton.click();
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(statusDropDownMenu));
        Select statusSelect = new Select(statusDropDownMenu);
        statusSelect.selectByVisibleText(properties.getProperty("mantis.issue_status"));

        dateFilterButton.click();
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(startDayDropDownMenu));
        Select startDaySelect = new Select(startDayDropDownMenu);
        startDaySelect.selectByVisibleText(properties.getProperty("mantis.issue_start_day"));
        Select startMonthSelect = new Select(startMonthDropDownMenu);
        startMonthSelect.selectByVisibleText(properties.getProperty("mantis.issue_start_month"));
        Select startYearSelect = new Select(startYearDropDownMenu);
        startYearSelect.selectByVisibleText(properties.getProperty("mantis.issue_start_year"));

        Select endDaySelect = new Select(endDayDropDownMenu);
        endDaySelect.selectByVisibleText(properties.getProperty("mantis.issue_end_day"));
        Select endMonthSelect = new Select(endMonthDropDownMenu);
        endMonthSelect.selectByVisibleText(properties.getProperty("mantis.issue_end_month"));
        Select endYearSelect = new Select(endYearDropDownMenu);
        endYearSelect.selectByVisibleText(properties.getProperty("mantis.issue_end_year"));

        applyFilterButton.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(issueRecord));
    }

    public WebElement getIssueRecord() {
        return issueRecord;
    }
}
