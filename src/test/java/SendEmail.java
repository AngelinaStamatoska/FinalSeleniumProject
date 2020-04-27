import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SendEmail {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeSuite
    public void beforeAllTests() {
        driver = ConfiguredDriver.configure("http://google.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement englishTranslateButton = driver.findElement(By.xpath("//*[text()='English']"));

        if(englishTranslateButton.isDisplayed())
        {
            englishTranslateButton.click();
        }
    }

    @BeforeClass
    public void doLogin(){
        Login loginObject = new Login();
        loginObject.doLogin(driver);
    }

    @Test(priority = 0)
    public void accessGmailApp() {
        WebElement googleApps = driver.findElement(By.xpath("//*[@id='gbwa']/div/a"));
        googleApps.click();
        // Select the gmail link from the dropdown apps menu
        WebElement gmailSpan = driver.findElement(By.xpath("//*[contains(text(),'Gmail')]"));
        WebElement parentLink = gmailSpan.findElement(By.xpath("./.."));
        parentLink.click();
    }

    @Test(priority = 1)
    public void clickComposeAndSendEmail() {
        //Click on the button that displays the Compose email Popup
        WebElement composeButton = driver.findElement(By.xpath("//*[contains(text(),'Compose')]"));
        composeButton.click();
        ComposeEmail.compose(driver);
        //Send the email
        WebElement sendButton = driver.findElement(By.className("dC"));
        sendButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 2)
    public void closeConfirmMessage() {
        //verify that 'Message sent' alert is shown and hide it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Message sent.')]")));
        WebElement closeAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bBe")));
        closeAlert.click();
    }

    @Test(priority = 3)
    public void accessSentEmails(){
        //redirect the user to sent emails
        driver.navigate().to("https://mail.google.com/mail/u/0/?ogbl#sent");

        driver.navigate().refresh();
    }

    @Test(priority = 4)
    public void accessLastEmail(){
        //click on the first email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zA")));
        WebElement lastSentEmail = driver.findElement(By.className("zA"));

        lastSentEmail.click();
    }

    @Test(priority = 5)
    public void verifyData() {
        //verify that expected email recipient and cc are in the opened email
        WebElement ccVerify = driver.findElement(By.xpath("//span[@email='']"));
        ccVerify.isDisplayed();
        WebElement recipientVerify = driver.findElement(By.xpath("//span[@email='']"));
        recipientVerify.isDisplayed();
    }


    @BeforeMethod
    protected void startTest(Method method) throws Exception {
        String testName = method.getName();
        System.out.println("Executing test: " + testName);
    }

    @AfterMethod
    protected void endTest(Method method) throws Exception {
        String testName = method.getName();
        System.out.println("Finished with test: " + testName);
    }

    @AfterSuite
    protected void afterSuite(){
        //destroy the driver at the end of all the tests
        driver.quit();
    }

}
