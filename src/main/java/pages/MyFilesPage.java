package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyFilesPage {

    WebDriver driver;

    //Constructor that will be automatically called as soon as the object of the class is created
    public MyFilesPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locator for email field
    By browseBtn = By.xpath("//input[@ng-hide=\"upload.uploading || upload.messaging\"]");

    //Locator for password field
    By uploadMsg = By.xpath("//textarea[@ng-model=\"upload.message\"]");

    //Locator for login button
    By uploadBtn = By.xpath("//li[@type=\"submit\"]");


    //Method to login
    public void uploadData(String location, String file, String msg) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(browseBtn));
        driver.findElement(browseBtn).sendKeys(location+file);
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadMsg));
        driver.findElement(uploadMsg).sendKeys(msg);
        driver.findElement(uploadBtn).click();
    }

    public String getDateData(String file) {
        By dateField = By.xpath(String.format("//span[contains(., '%s')]/../../../div[2]/small",
                file));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(dateField));
        String text = this.driver.findElement(dateField).getText();
        return text;
    }

    public String getNameData(String file) {
        By nameField = By.xpath(String.format("(//span[contains(., '%s')])[1]",
                file));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(nameField));
        String text = this.driver.findElement(nameField).getText();
        return text;
    }
}