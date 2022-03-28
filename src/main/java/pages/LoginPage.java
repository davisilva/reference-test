package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    //Constructor that will be automatically called as soon as the object of the class is created
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locator for email field
    By email = By.id("emailAddress");

    //Locator for password field
    By pswd = By.id("password");

    //Locator for login button
    By loginBtn = By.className("MuiButton-label");


    //Method to login
    public void login(String user, String pass) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(email));
        driver.findElement(email).sendKeys(user);
        driver.findElement(pswd).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
}