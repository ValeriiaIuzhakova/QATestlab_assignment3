import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.*;

public class AddCategoryTest {

    public static void main (String[] args) throws InterruptedException {
        EventFiringWebDriver e_driver;
        WebEventListener eventListener;

        // Initializing instance of Chrome WebDriver
        WebDriver driver = DriverManager.getDriver("chrome");
        WebDriverWait wait = new WebDriverWait(driver, 5);

        // Initializing EventFiringWebDriver using Chrome WebDriver instance
        e_driver = new EventFiringWebDriver(driver);

        // Creating object of EventListenerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);

        //An alternative wait option that allows to process scripts correctly
        //e_driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Log in to Admin panel
        System.out.println("***** 1 ***** ");
        e_driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");

        WebElement searchInputLogin = e_driver.findElement(By.id("email"));
        searchInputLogin.sendKeys("webinar.test@gmail.com");

        WebElement searchInputPassword = e_driver.findElement(By.id("passwd"));
        searchInputPassword.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement searchButton = e_driver.findElement(By.name("submitLogin"));
        searchButton.submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtab-AdminCatalog")));

        System.out.println("***** 2 ***** ");
        WebElement topLevelElement = e_driver.findElement(By.id("subtab-AdminCatalog"));

        WebElement searchProduct = e_driver.findElement(By.xpath("//*[@id=\"subtab-AdminCatalog\"]/ul/li[2]"));

        Actions actions = new Actions(e_driver);
        actions.moveToElement(topLevelElement).pause(Duration.ofSeconds(5)).click(searchProduct)
                .build().perform();

        //Add new category
        System.out.println("***** 3 ***** ");
        WebElement searchAddButton = e_driver.findElement(By.id("page-header-desc-category-new_category"));
        searchAddButton.click();

        System.out.println("***** 4 ***** ");
        WebElement inputName = e_driver.findElement(By.id("name_1"));
        inputName.sendKeys("Assignment3 category");

        WebElement submitButton = e_driver.findElement(By.id("category_form_submit_btn"));
        submitButton.submit();

        //Getting successful alert
        WebElement searchAlert = e_driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div"));
        searchAlert.isDisplayed();

        WebElement clickBack = e_driver.findElement(By.id("desc-category-back"));
        clickBack.click();

        //Filter out the created category
        System.out.println("***** 5 ***** ");
        WebElement filterByName = e_driver.findElement(By.name("categoryFilter_name"));
        filterByName.sendKeys("Assignment3 category");

        WebElement search = e_driver.findElement(By.id("submitFilterButtoncategory"));
        search.click();

        WebElement findCategoty = e_driver.findElement(By.xpath("//tr/td[contains(text(), 'Assignment3 category')]"));
        findCategoty.isDisplayed();

        e_driver.close();
    }
}