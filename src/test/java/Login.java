import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Login {
    public void doLogin(WebDriver driver) {
        WebElement signInButton = driver.findElement(By.xpath("//a[contains(text(),'Sign in')]"));
        if(signInButton.isDisplayed()) {
            signInButton.click();
            // Get email field and populate it
            WebElement emailField = driver.findElement(By.name("identifier"));
            emailField.sendKeys("");

            // Wait for next button to appear and then get it and click it
            WebElement nextButton = driver.findElement(By.id("identifierNext"));
            nextButton.click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Get password field and populate it
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("");
            // Wait for next button to appear and then get it and click it
            WebElement nextClick = driver.findElement(By.id("passwordNext"));
            nextClick.click();
        }
    }
}
