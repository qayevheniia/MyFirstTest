import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWithXpath {
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

    @Test
    public void xpath(){
        driver.get("https://theautomationzone.blogspot.com/2020/07/xpath-practice.html");
        WebElement test1 = driver.findElement(By.xpath("//*[@id='id1']"));
        System.out.println((test1.getText()));

        WebElement test2 = driver.findElement(By.xpath("//div[@class='class1']"));
        System.out.println((test2.getText()));

//когда надо индетифицирвать за обоими локаторами
        WebElement test3 = driver.findElement(By.xpath("//p[@name='a' and @id='b' or (@name='b' and @id= 'a')]"));
        System.out.println((test3.getText()));

//идет по детям родителей
        WebElement test4 = driver.findElement(By.xpath("//div[@id='div1']/div[2]"));
        System.out.println((test4.getText()));

//        от ребенка к родителю ..
        WebElement test5 = driver.findElement(By.xpath("//span[@id='link']/.."));
        System.out.println((test5.getText()));

//когда надо спуститься от родителя к конкретному ребенку
        WebElement test6 = driver.findElement(By.xpath("//*[@id='divC']/span/input"));
        System.out.println((test6.getText()));

        //когда надо достучаться до текста
        WebElement test7 = driver.findElement(By.xpath("//button[text()='random']"));
        System.out.println((test7.getText()));


        //когда надо достучаться до частичного текста
        WebElement test8 = driver.findElement(By.xpath("//button[contains(text(), 'time')]"));
        System.out.println((test8.getText()));

        String myLabel = "time";
        String myString = "//button[contains(text(), '"+ myLabel+" ')]";
        String myString1 = String.format("//button[contains(text(), '%s' ')]", myLabel);
        System.out.println(myString);
        System.out.println(myString1);

    }
}