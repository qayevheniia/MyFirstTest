import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Test18Lessons {
    private final String expectedDress = "Printed Dress";

    
    private final String baseUrl = "http://automationpractice.com/";
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void cleanDriver() {
        driver.quit();
    }

    public void waitElement(String element) {
        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))
                , ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element))));
    }

    @Test
    public void verifyProductIn() throws InterruptedException {
        driver.get(baseUrl);
//
        waitElement("//div[@id='block_top_menu']/ul/li/a[@title='Dresses']");
        WebElement dressesCategory = driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li/a[@title='Dresses']"));
        dressesCategory.click();
        waitElement("//a[@title='Evening Dresses']/img");
        WebElement eveningCategory = driver.findElement(By.xpath("//a[@title='Evening Dresses']/img"));
        eveningCategory.click();
        waitElement("//a[@class='button ajax_add_to_cart_button btn btn-default']");

//        Я цепанула тут на класс (так как мы обсуждали, что неправильно цеплятся к тексту) но мне кажется,
//        что этот тест надо было строить по другому.
        // Я б цеплялась и при добавлении в корзину и при варификации корзины к тексту,
        // то есть добавляем именно платье и его же ищем в корзине. Что ты думаешь по этому поводу?
        WebElement addToCard = driver.findElement(By.xpath("//a[@class='button ajax_add_to_cart_button btn btn-default']"));
        addToCard.click();
        waitElement("//span[@title='Close window']");
       WebElement closeMessage = driver.findElement(By.xpath("//span[@title='Close window']"));
       closeMessage.click();
       waitElement("//div[@class='shopping_cart']/a");
        WebElement basket = driver.findElement(By.xpath("//div[@class='shopping_cart']/a"));
                basket.click();
        String currentDress = driver.findElement(By.xpath("//tr[@id='product_4_16_0_0']//td//p")).getText();
        Assert.assertEquals(currentDress, expectedDress, "Wrong product");

    }

}
