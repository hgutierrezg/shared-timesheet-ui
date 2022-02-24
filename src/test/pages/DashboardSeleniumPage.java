package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardSeleniumPage {

    @FindBy(id = "timesheetFormId")
    private WebElement timesheetFormSection;

    @FindBy(id = "employeeMenuId")
    private WebElement employeeMenuId;

    @FindBy(id = "externalManagerId")
    private WebElement externalManagerId;

    @FindBy(id = "startDate")
    private WebElement startDate;

    @FindBy(id = "endDate")
    private WebElement endDate;

    @FindBy(id = "client")
    private WebElement client;

    @FindBy(id = "addBtnId")
    private WebElement addBtnId;

    @FindBy(id = "displayErrorId")
    private WebElement displayErrorId;

    @FindBy(id = "timesheetListSectionId")
    private WebElement timesheetListSectionId;

    private WebDriverWait wait;

    public DashboardSeleniumPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,Duration.ofSeconds(500L));
    }

    public void findEmployeeMenu() {
        employeeMenuId.click();
    }

    public void findExternalManagerMenu() {
        externalManagerId.click();
    }

    public boolean displayFormControls() {
        try {
            return timesheetFormSection.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean addTimesheetAsEmployee() {
        if (timesheetFormSection.isDisplayed()) {
            addDateTime(startDate, "09252021");
            addDateTime(endDate, "10022021");
            client.sendKeys("TestClient");
        }
        addBtnId.submit();
       wait.until(ExpectedConditions.presenceOfElementLocated(By.id("timesheetListSectionId")));
       return timesheetListSectionId.isDisplayed();
    }

    private void addDateTime(WebElement startDate, String date) {
        startDate.sendKeys(date);
        startDate.sendKeys(Keys.TAB);
        startDate.sendKeys("0245PM");
    }

    public boolean addTimesheetWithInvalidDates() {
        startDate.sendKeys("invalidDate2");
        endDate.sendKeys("invalidDate2");
        return addBtnId.isEnabled();
    }

    public boolean displayErrorWhenEmptyClient() {
        if (timesheetFormSection.isDisplayed()) {
            addDateTime(startDate, "09252021");
            addDateTime(endDate, "10022021");
            client.sendKeys("");
        }
        addBtnId.submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("displayErrorId")));
        return displayErrorId.isDisplayed();
    }
}
