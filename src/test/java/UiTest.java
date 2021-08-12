import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class UiTest {

    private final String baseUrl = "https://www.ikea.com/ua/uk/1213";
    WebDriver driver;



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
}
