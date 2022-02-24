import config.ChromeSeleniumConfig;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginSeleniumPage;

import pages.DashboardSeleniumPage;

public class SeleniumTest {

    private ChromeSeleniumConfig chromeSeleniumConfig;
    private LoginSeleniumPage loginSeleniumPage;
    private DashboardSeleniumPage dashboardSeleniumPage;

    @BeforeSuite
    public void setUp() {
        chromeSeleniumConfig = new ChromeSeleniumConfig();
        dashboardSeleniumPage = new DashboardSeleniumPage(chromeSeleniumConfig.getDriver());
        loginSeleniumPage = new LoginSeleniumPage(chromeSeleniumConfig.getDriver());
    }

    @AfterSuite
    public void tearDown() {
        chromeSeleniumConfig.close();
    }

    @BeforeTest
    public void beforeEach() {
        loginSeleniumPage.doLogin();
    }

    @Test
    public void givenUserInLoginPage_whenEnterCredentials_thenNewRecordIsAdded() {
        dashboardSeleniumPage.findEmployeeMenu();

        Assert.assertTrue(dashboardSeleniumPage.displayFormControls());
        Assert.assertTrue(dashboardSeleniumPage.addTimesheetAsEmployee());
    }

    @Test
    public void givenUserInLoginPage_whenEnterCredentials_thenExternalManagerDoesNotCreate() {
        dashboardSeleniumPage.findExternalManagerMenu();

        Assert.assertFalse(dashboardSeleniumPage.displayFormControls());
    }

    @Test
    public void givenUserInDashboardPage_whenUserAddsInvalidValidDate_thenAddButtonNotEnabled() {
        dashboardSeleniumPage.findEmployeeMenu();

        Assert.assertTrue(dashboardSeleniumPage.displayFormControls());
        Assert.assertFalse(dashboardSeleniumPage.addTimesheetWithInvalidDates());
    }

    @Test
    public void givenUserInDashboardPage_whenUserAddsInvalidClient_thenErrorIsDisplayed() {
        dashboardSeleniumPage.findEmployeeMenu();

        Assert.assertTrue(dashboardSeleniumPage.displayFormControls());
        Assert.assertTrue(dashboardSeleniumPage.displayErrorWhenEmptyClient());
    }
}
