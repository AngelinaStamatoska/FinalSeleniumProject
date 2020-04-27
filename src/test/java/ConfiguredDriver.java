import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfiguredDriver {
    public static WebDriver configure(String url) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Angelina\\IdeaProjects\\SeleniumFinalProject\\src\\test\\Browser driver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();

        return driver;
    }
}
