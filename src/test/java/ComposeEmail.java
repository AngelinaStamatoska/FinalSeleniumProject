import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ComposeEmail {
    public static void compose(WebDriver driver){
        WebElement recipients = driver.findElement(By.name("to"));
        WebElement subject = driver.findElement(By.name("subjectbox"));
        WebElement mainContent = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));

        recipients.sendKeys("");

        //Click on the CC button to enter my email as CC recipient
        WebElement ccButton = driver.findElement(By.xpath("//span[contains(text(),'Cc')]"));
        ccButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("cc")));
        WebElement cc = driver.findElement(By.name("cc"));
        cc.sendKeys("");

        //Populate the other fields
        subject.sendKeys("Final Selenium Project");
        mainContent.sendKeys("We are just starting with Selenium");
    }
}
