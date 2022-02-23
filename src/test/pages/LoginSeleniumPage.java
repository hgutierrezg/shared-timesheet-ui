package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSeleniumPage {

    private WebDriver driver;
    private static final String URL = "http://localhost:8080/shared-timesheet";

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "loginBtnId")
    private WebElement loginBtnId;

    public LoginSeleniumPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doLogin() {
        this.driver.navigate()
                .to(URL);
        username.sendKeys("testUsername");
        password.sendKeys("testPassword");
        loginBtnId.click();
    }
}