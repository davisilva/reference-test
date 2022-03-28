package pages;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard {

    WebDriver driver;

    //Constructor that will be automatically called as soon as the object of the class is created
    public Dashboard(WebDriver driver) {
        this.driver=driver;
    }

    //Locators for the page title and the logout button
    By dateIcon = By.xpath("//button[@title = \"DATE/TIME\"]");
    By historicDate = By.className("icon-historic");
    By fromDate = By.xpath("//div[@class=\"date-control\" and @selection=\"from\"]");
    By toDate = By.xpath("//div[@class=\"date-control\" and @selection=\"to\"]");
    By fromPreviousMonth = By.xpath("(//div[@class='month-container']//button[@class=\"previous btn btn-text\"])[1]");
    By toPreviousMonth = By.xpath("(//div[@class='month-container']//button[@class=\"previous btn btn-text\"])[2]");
    By fromCurrentDate = By.xpath("(//span[@class=\"month\"])[1]");
    By toCurrentDate = By.xpath("(//span[@class=\"month\"])[2]");
    By fromHour = By.xpath("(//select[@name=\"hours\"])[2]");
    By toHour = By.xpath("(//select[@name=\"hours\"])[3]");
    By fromMinutes = By.xpath("(//select[@name=\"minutes\"])[2]");
    By toMinutes = By.xpath("(//select[@name=\"minutes\"])[3]");
    By fromDateText = By.xpath("//span[@class=\"date-range\"][1]");
    By toDateText = By.xpath("//span[@class=\"date-range\"][2]");
    By submitBtn = By.xpath("(//button[@class='btn btn-sm btn-primary'])[2]");
    By alertSuccess = By.xpath("//div[@class=\"show success\"]");
    By userIcon = By.xpath("//button[@drawer-toggle = \"#account-menu\"]");
    By userName = By.xpath("//span[contains(@title, 'Display Name')]");
    By userEmail = By.xpath("//span[contains(@title, 'Email')]");
    By lastLogin = By.xpath("//span[contains(@title, 'Last Login')]");
    By myFilesField = By.xpath("//span[contains(., 'My Files')]");
    By languageField = By.xpath("//span[contains(., 'Language')]");
    By userManualField = By.xpath("//span[contains(., 'User Manual')]");
    By licenseInfoField = By.xpath("//span[contains(., 'License Information')]");
    By logoutBtn = By.xpath("//button[@ng-click=\"signOut()\"]");
    By confirmLogoutBtn = By.xpath("//button[@ng-click=\"ok(true)\"]");
    By homepageBtn = By.xpath("//li[contains(@title, 'Set as Homepage')]");
    By dupDashboardBtn = By.xpath("(//li[contains(@title, 'Duplicate Dashboard')])[1]");
    By exportDataBtn = By.xpath("//li[contains(@title, 'Export Data')]");
    By firstAddIcon = By.xpath("(//*[name()=\"svg\" and @id=\"data-input-add-icon-id\"])[1]");
    By firstEditIcon = By.xpath("(//*[name()=\"svg\" and @title=\"Edit\"])[1]");
    By valueFieldAddData = By.id("value");
    By submitBtnAddData = By.xpath("//span[text() = \"Submit\"]");
    By dashboardName  = By.xpath("//h1[@ng-bind = \"dashboard.name\"]");
    By dupDashboardName = By.xpath("//input[@data-ng-model = \"name\"]");
    By dupDashboardShare = By.xpath("//input[@data-ng-model = \"share\"]");
    By dupDashboardOk  = By.xpath("//button[@ng-click = \"ok(name, share)\"]");

    //Method to click on User Icon button
    public void clickUserIcon() {
        driver.findElement(userIcon).click();
    }

    //Method to capture the user name
    public String getUserName() {
        String text = driver.findElement(userName).getText();
        return text;
    }

    //Method to capture the user name
    public String getUserEmail() {
        String text = driver.findElement(userName).getText();
        return text;
    }

    //Method to capture the last login
    public String getLastLogin() {
        String text = driver.findElement(lastLogin).getText();
        return text;
    }

    //Method to capture the user name
    public String getFromDateText() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String text = driver.findElement(fromDateText).getText();
        return text;
    }

    //Method to capture the user name
    public String getToDateText() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String text = driver.findElement(toDateText).getText();
        return text;
    }

    //Method to capture the user name
    public String getAlertSuccessText() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertSuccess));
        String text = driver.findElement(alertSuccess).getText();
        return text;
    }


    //Method to click on Logout button
    public void clickLogout() {
        driver.findElement(logoutBtn).click();
    }

    //Method to change date
    public void changeDate(String fromMonth, String fromYear, int fromDay, int fromHours, int fromMinute,
                           String toMonth, String toYear, int toDay, int toHours, int toMinute) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateIcon));
        driver.findElement(dateIcon).click();
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(historicDate));
        driver.findElement(historicDate).click();
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate));
        driver.findElement(fromDate).click();
        while (!(driver.findElement(fromCurrentDate).getText()).contains(fromMonth + fromYear)){
            driver.findElement(fromPreviousMonth).click();
        }
        By fromDayDate = By.xpath(String.format("(//div[@class=\"day\" and text()=%d])[1]",fromDay));
        driver.findElement(fromDayDate).click();
        Select selectFromHour= new Select(driver.findElement(fromHour));
        selectFromHour.selectByValue(String.valueOf(fromHours));
        Select selectFromMinute= new Select(driver.findElement(fromMinutes));
        selectFromMinute.selectByValue(String.valueOf(fromMinute));
        driver.findElement(toDate).click();
        while (!(driver.findElement(toCurrentDate).getText()).contains(toMonth + toYear)){
            driver.findElement(toPreviousMonth).click();
        }
        By toDayDate = By.xpath(String.format("(//div[@class=\"day\" and text()=%d])[2]",toDay));
        driver.findElement(toDayDate).click();
        Select selectToHour = new Select(driver.findElement(toHour));
        selectToHour.selectByValue(String.valueOf(toHours));
        Select selectToMinute= new Select(driver.findElement(toMinutes));
        selectToMinute.selectByValue(String.valueOf(toMinute));
        driver.findElement(submitBtn).click();
    }

    public String addSingularMaterial(String material, int value) {
        By materialAddIcon = By.xpath(String.format("(//td[contains(., '%s')]/../td/../td[6]/div/*[name()=\"svg\"])[1]",
                material));
        driver.findElement(materialAddIcon).click();
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        String formattedDate = myDateObj.format(myFormatObj);
        driver.findElement(valueFieldAddData).sendKeys(String.valueOf(value));
        driver.findElement(submitBtnAddData).click();
        return formattedDate;
    }

    public int getValueMaterial(String material) {

        By valueField = By.xpath(String.format("(//td[contains(., '%s')]/../td/../td[5])[1]",
                material));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(valueField));
        String text = driver.findElement(valueField).getText();
        return Integer.parseInt(text);
    }

    public String getDateMaterial(String material) {
        By dateField = By.xpath(String.format("(//td[contains(., '%s')]/../td/../td[4])[1]",
                material));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
        String text = driver.findElement(dateField).getText();
        return text;
    }

    public void goToMyFilesPage() throws InterruptedException {
        driver.findElement(userIcon).click();
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(myFilesField));
        driver.findElement(myFilesField).click();
    }

    public void duplicateDashboard(String name, boolean share) throws InterruptedException {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(dupDashboardBtn));
        driver.findElement(dupDashboardBtn).click();
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(dupDashboardName));
        driver.findElement(dupDashboardName).sendKeys(name);
        if (share) {
            driver.findElement(dupDashboardShare).click();
        }
        driver.findElement(dupDashboardOk).click();
    }

    public String getDashboardName() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardName));
        String text = driver.findElement(dashboardName).getText();
        return text;
    }

}