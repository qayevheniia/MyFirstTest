package SelenideTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {
    GooglePageObject googlePageObject = new GooglePageObject();
    Header header = new Header();


    @Test
    public void newTest(){
         Configuration.browser = System.getProperty("browser");
//        чтоб запустить на бекраунде тесты headlesa
//        Configuration.headless = true;
        open("https://google.com");
        String actualTitle = Selenide.title();
        String  expectedResult ="Google";
        googlePageObject.getSearchFields().should(appear).setValue("test").pressEnter();
        $(By.tagName("h3")).shouldHave(text("Speedtest"));
        Assert.assertEquals(actualTitle, expectedResult, "Title is wrong");
    }

    String urlShop = "http://automationpractice.com/";

    @Test
    public void verifyNumberPhone(){
        open(urlShop);
        String actualNumberPhone = header.numberPhone.getText();
        Assert.assertEquals(actualNumberPhone, header.getExpectedNumberPhone(), "Number is different");
    }

    @Test
    public void verifySignInButton(){
        open("http://automationpractice.com/");
        header.signInButton.click();
        String actualTitle = Selenide.title();
        Assert.assertEquals(actualTitle, header.getExpectedTitleLoginPage(), "Wrong page for login button");
    }



    @Test
    public void verifyContactButton(){
        open("http://automationpractice.com/");
        header.signInButton.click();
        String actualTitle = Selenide.title();
        Assert.assertEquals(actualTitle, header.getExpectedTitleLoginPage(), "Wrong page for login button");
    }


}
