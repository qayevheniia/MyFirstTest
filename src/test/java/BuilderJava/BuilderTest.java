package BuilderJava;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Builder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class BuilderTest {    WebDriver driver;
    public static FluentWait fluentwait2;
    public static WebDriverWait wait1;


    public static String baseUrl = "https://www.ikea.com/ua/uk/";

    @BeforeTest
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        fluentwait2 = new FluentWait(driver);
        fluentwait2.withTimeout(5000, TimeUnit.MILLISECONDS);
        wait1 = new WebDriverWait(driver, 10);

    }

    @AfterTest
    public void cleanDriver() {
        driver.quit();
    }

    @Test
    public void testSumm(){
        User myUser = User.builder()
                .name("Jane")
                .isRegistered(true)
                .address("Kyibv")
                .build();

        String numbers ="01234567890";
        StringBuilder sb= new StringBuilder(numbers);
        sb.append("Helo");

    }


}
