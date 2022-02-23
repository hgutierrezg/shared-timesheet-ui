package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSeleniumConfig {

    private WebDriver driver;

    public ChromeSeleniumConfig() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        driver = new ChromeDriver(options);
    }

    public void close() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
