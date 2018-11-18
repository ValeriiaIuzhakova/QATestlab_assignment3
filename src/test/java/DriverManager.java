import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public static WebDriver getDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver");
                return new FirefoxDriver();
            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
                return new ChromeDriver();
        }
    }
}
