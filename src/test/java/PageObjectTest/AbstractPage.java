package PageObjectTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage{
    private WebDriverWait wait;
    WebDriver driver;

    public  AbstractPage (WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

}
