package PageObjectTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(id="login-form-username")
    WebElement loginField;

    @FindBy(id="login-form-password")
    WebElement loginPassword;

    @FindBy(id="login-form-submit")
    WebElement submitButton;

    @FindBy(xpath = "//*[@role='alertdialog']")
    WebElement alert;

    public LoginPage(WebDriver driver) {
        super(driver);

    }

public String getAlertText(){
        sleepFewSeconds();
        return alert.getText();
}

public  void  setLoginField(String login, String password){
    sleepFewSeconds();
    loginField.sendKeys(login);
        loginPassword.sendKeys(password);
        submit();
}

    private void sleepFewSeconds() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submit(){
        submitButton.click();
    }

}
