import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class UiTest {

    private final String baseUrl = "https://www.ikea.com/ua/uk/1213";
    WebDriver driver;
    String  STANDART_USER_LOGIN ="standard_user";
    String  STANDART_USER_PASSWORD ="secret_sauce";
    String  LOCKER_USER_LOGIN ="locked_out_user";
    String expectedResult = "https://www.saucedemo.com/inventory.html";


    @BeforeTest
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @AfterTest
    public void cleanDriver() {
        driver.quit();

    }
    @Ignore
    @Test
    public void myUiTest() {
        driver.get(baseUrl);
//         close закрывает текущую вкладку  quit - закрывает полностью браузер
//        driver.close();
    }
    @Ignore
    @Test
    public void myUiTest2() {
        driver.get(baseUrl);
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        Assert.assertEquals(currentUrl, "https://www.ikea.com/ua/uk/1213", "Wrong url");
    }
    @Ignore
    @Test
    public void myUiTest3() {
        driver.get(baseUrl);
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
        Assert.assertEquals(currentTitle, "404 - Ой! Щось пішло не так :( - IKEA", "Wrong title");
        Assert.assertTrue(currentTitle.contains("IKEA"));
    }
    @Ignore
    @Test
    public void myUiTest4() {

        driver.navigate().to(baseUrl);
        driver.navigate().to("https://www.ikea.com/ua/uk");
        driver.navigate().back();
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
        Assert.assertTrue(currentTitle.contains("IKEA"));
    }

    private void login(String STANDART_USER_LOGIN, String STANDART_USER_PASSWORD){
    WebElement loginStandart = driver.findElement(By.id("user-name"));
    WebElement passwordFiled = driver.findElement(By.id("password"));
    loginStandart.sendKeys(STANDART_USER_LOGIN);
    passwordFiled.sendKeys(STANDART_USER_PASSWORD);
    WebElement loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();
}

    @Test
    public void standartUserLoginTest(){
        openSource();
        login(STANDART_USER_LOGIN, STANDART_USER_PASSWORD);
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult,"User didn't login");
    }


    @Test
    public void lockedtUserLoginTest() throws InterruptedException {
        openSource();
        login(LOCKER_USER_LOGIN, STANDART_USER_PASSWORD);
        WebElement text = driver.findElement(By.cssSelector(".error-message-container.error"));
        Thread.sleep(500);
        String actualResult = text.getText();
        System.out.println(text.getText());
        String expectedResult = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(actualResult,expectedResult,"Wrong result");
    }

    @Test
    public void logOut() throws InterruptedException {
        openSource();
        login(STANDART_USER_LOGIN, STANDART_USER_PASSWORD);
        WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
        burgerMenu.click();
        WebElement logOutButton = driver.findElement(By.id("logout_sidebar_link"));
        Thread.sleep(500);
        logOutButton.click();
        Assert.assertFalse(driver.getCurrentUrl().contains(expectedResult));


    }

    public void openSource(){
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }
}
