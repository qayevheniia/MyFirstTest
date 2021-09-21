package BuilderJava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// почитать про стрингбилдер про драйвер фактори и про синглтон Singleton pattern

//если надо будет прописать свой патерн для выбора браузера
public class DriverFactory {

    public WebDriver getDriver(String browserName){
        WebDriver driver;
        if (browserName.equals("chrome")){
            driver = new ChromeDriver();
        }
        else if (browserName.equals("firefox")){
            driver = new FirefoxDriver();

        }
        else driver = new ChromeDriver();

        return driver;
    }
}
//String browser = System.getProperty("browser")