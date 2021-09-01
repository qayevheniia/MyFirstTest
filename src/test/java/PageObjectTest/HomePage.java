package PageObjectTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {


    @FindBy(xpath = "//a[@data-tracking-label='profile']")
    WebElement loginButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public WebElement getLoginButton() {
        return loginButton;
    }
}
