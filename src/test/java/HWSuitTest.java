import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


public class HWSuitTest {

    private final String baseUrl = "https://lms.ithillel.ua/auth";
    private final String expLinkAfterSignIn = "https://lms.ithillel.ua/";
    private final String expLinkSupport = "https://t.me/hillel_bot";
    private final String emailValid = "qayevheniia@gmail.com";
    private final String passwordValid = "Mzhenya1987)";
    private final String emailInvalid = "QA1212122@mvm.com";
    private final String passwordInvalid = "232131";
    String expAlertInvalidLogin = "Incorrect email or password";

//    the general wait-locators for pages
    String waitElementAfterSignIn = "//*[contains(text(), ' QA Automation ')]";
    String waitSupportIcon = "span.login__support-icon";
    String waitElementBaseUrl = ".button.button--light-blue.button--simple.ng-star-inserted";
    String waitAlertWrongCredential = ".validation-messages__item.ng-star-inserted";

//    general locators
    By emailField = (By.xpath("//input[@type='email']"));
    By passwordField = (By.xpath("//input[@type='password']"));
    By submitButtonSignIn = (By.cssSelector(".button--light-blue.button--simple.ng-star-inserted"));
    By supportIcon = (By.cssSelector("span.login__support-icon"));
    By logOutButton = (By.xpath("//*[contains(text(), ' Выйти ')]"));
    By alertInvalidLogin = (By.cssSelector(".validation-messages__item.ng-star-inserted"));

    WebDriver driver;
    WebDriverWait wait;

    public void openBaseUrl() {
        driver.get(baseUrl);
    }

    public void login(String email, String password) {
        waitElement(waitElementBaseUrl);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButtonSignIn).submit();

    }

    public void switchToLastWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void waitElement(String element) {
        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))
                , ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))));
    }

//так как не срабатывала команда очистики куки пришлось сделать before
// и after метода так как третий тест открывался сразу с залогениной страницы
//    так можно сделать или это замедляет прохождение тестов?

    @BeforeMethod(groups = {"smoke", "regression"})
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().deleteAllCookies();

    }

    @AfterMethod(groups = {"smoke", "regression"})
   public void cleanDriver() {
        driver.quit();
    }

    @Test(groups = "regression")
    public void openUrl() {
        openBaseUrl();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, baseUrl, "Wrong url");
    }


    @Test(groups = {"smoke", "regression"})
    public void signIn() {
        openBaseUrl();
        login(emailValid, passwordValid);
        waitElement(waitElementAfterSignIn);
        String currentlinkSignIn = driver.getCurrentUrl();
        Assert.assertEquals(currentlinkSignIn, expLinkAfterSignIn, "Wrong support link");
    }


    @Test(groups = {"smoke", "regression"})
    public void verifySupportIcon() {
        openBaseUrl();
        System.out.println(driver.getCurrentUrl());
        waitElement(waitSupportIcon);
        driver.findElement(supportIcon).click();
        switchToLastWindow();
        String currentlinkSupport = driver.getCurrentUrl();
        Assert.assertEquals(currentlinkSupport, expLinkSupport, "Wrong support link");
    }

    @Test(groups = {"smoke", "regression"})
    public void logOut() {
        openBaseUrl();
        login(emailValid, passwordValid);
        waitElement(waitElementAfterSignIn);
        driver.findElement(logOutButton).click();
        waitElement(waitElementBaseUrl);
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, baseUrl, "SignOut is broken");
    }


    @Test(groups = {"regression"})
    public void InvalidEmailPassword(){
        openBaseUrl();
        login(emailInvalid, passwordInvalid);
        waitElement(waitAlertWrongCredential);
        String actAlertInvalidLogin = driver.findElement(alertInvalidLogin).getText();
        Assert.assertEquals(actAlertInvalidLogin, expAlertInvalidLogin, "Wrong alert for invalid email & password");
        String actUrl = driver.getCurrentUrl();
        Assert.assertEquals(actUrl, baseUrl, "Wrong url after sign in with wrong credential");
    }
    @Test(groups = {"regression"})
    public void InvalidEmail(){
        openBaseUrl();
        login(emailInvalid, passwordValid);
        waitElement(waitAlertWrongCredential);
        String actAlertInvalidLogin = driver.findElement(alertInvalidLogin).getText();
        Assert.assertEquals(actAlertInvalidLogin, expAlertInvalidLogin, "Wrong alert for invalid email");
        String actUrl = driver.getCurrentUrl();
        Assert.assertEquals(actUrl, baseUrl, "Wrong url after sign in with wrong email");
    }
    @Test(groups = {"regression"})
    public void InvalidPassword(){
        openBaseUrl();
        login(emailValid, passwordInvalid);
        waitElement(waitAlertWrongCredential);
        String actAlertInvalidLogin = driver.findElement(alertInvalidLogin).getText();
        Assert.assertEquals(actAlertInvalidLogin, expAlertInvalidLogin, "Wrong alert for invalid email");
        String actUrl = driver.getCurrentUrl();
        Assert.assertEquals(actUrl, baseUrl, "Wrong url after sign in with wrong password");
    }
    }


