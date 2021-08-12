import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class HWSuitTest {

    private final String baseUrl = "https://lms.ithillel.ua/auth";
    WebDriver driver;
    WebDriverWait wait;
    private final String emailValid = "qayevheniia@gmail.com";
    private final String passwordValid = "Mzhenya1987)";


    @BeforeTest (groups = { "smoke", "regression" })

    public void downloadDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest (groups = { "smoke", "regression" })
    public void cleanDriver() {
        driver.quit();
    }

    @Test(groups = "regression")
    public void openUrl() {
        driver.get(baseUrl);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://lms.ithillel.ua/auth", "Wrong url");
    }

    @Test(groups = { "smoke", "regression" })
    public void signIn() {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.login")));
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(emailValid);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(passwordValid);
        driver.findElement(By.cssSelector(".button--light-blue.button--simple.ng-star-inserted")).submit();
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
        Assert.assertTrue(currentTitle.contains("Система личных кабинетов Студентов Компьютерной школы Hillel"));
    }

    @Test(groups = { "smoke", "regression" })
    public void signOut(){

    }


// Будем ли мы выносить селектора в отдельный класс?
// если у нас один и тот же код, например логинизация повторяется несколько раз, то нам вынести это в отдельный класс или как мы можем достучаться до метода, чтоб не дублировать код
// если у нас одно непрерывное флоу  и нет возможности разбить на методы как быть в этом случае
    
}
