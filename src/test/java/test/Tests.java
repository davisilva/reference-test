package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.LoginPage;
import pages.MyFilesPage;

import java.util.Random;

public class Tests {
    WebDriver driver;
    LoginPage login;
    Dashboard dashboard;
    MyFilesPage myFilesPage;

    @BeforeSuite
    public void startTestSuite() {
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://reference-test.intellisense.io/#!/id/dashboards/1471");
        login = new LoginPage(driver);
        login.login("menna+testproject@intellisense.io", "AutMaNewTest1#");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Should change the date")
    @Story("Test date change")
    @Test
    public void test1() throws InterruptedException {
        dashboard = new Dashboard(driver);
        dashboard.changeDate("Nov", " 2020", 2, 11, 35,
                "Feb"," 2022", 14, 12, 35);
        String expectedFromDate = "Nov 2, 2020 11:35 AM -03";
        String expectedToDate = "Feb 14, 2022 12:35 PM -03";
        Thread.sleep(5000);
        Assert.assertEquals(dashboard.getFromDateText(), expectedFromDate);
        Assert.assertEquals(dashboard.getToDateText(), expectedToDate);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Should add singular material")
    @Story("Test add singular material")
    @Test
    public void test2() throws InterruptedException {
        Random rand = new Random();
        int value = rand.nextInt(100);
        dashboard = new Dashboard(driver);
        String material = "COST_(INSTRUMENTATION)";
        String date = dashboard.addSingularMaterial(material, value);
        Thread.sleep(5000);
        Assert.assertEquals(dashboard.getDateMaterial(material), date);
        Assert.assertEquals(dashboard.getValueMaterial(material), value);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Should upload data to Intellisense.io")
    @Story("Test upload data")
    @Test
    public void test3() throws InterruptedException {
        dashboard = new Dashboard(driver);
        dashboard.goToMyFilesPage();
        myFilesPage = new MyFilesPage(driver);
        String file = "Davisa Testing.jpg";
        String location = "C:\\Users\\davi.silva\\IdeaProjects\\ReferenceTest\\src\\main\\resources\\";
        myFilesPage.uploadData(location, file, "Making tests with Selenium");
        String date = "a few seconds ago";
        Assert.assertEquals(myFilesPage.getDateData(file), date);
        Assert.assertEquals(myFilesPage.getNameData(file), file);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Should duplicate the dashboard")
    @Story("Test duplicate dashboard")
    @Test
    public void test4() throws InterruptedException {
        driver.get("https://reference-test.intellisense.io/#!/id/dashboards/1471");
        dashboard = new Dashboard(driver);
        String name = "Dashboard Davi Testing";
        dashboard.duplicateDashboard(name, true);
        Thread.sleep(5000);
        Assert.assertEquals(dashboard.getDashboardName(), name);
    }

    @AfterSuite
    public void endTestSuite() {
        driver.quit();
    }
}