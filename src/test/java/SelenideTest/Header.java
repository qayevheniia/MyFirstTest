package SelenideTest;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class Header {


    private String expectedNumberPhone = "Call us now: 0123-456-789";
    private String expectedTitleLoginPage = "Login - My Store";

    SelenideElement imageDiscount = $(By.xpath("//img[@class='img-responsive']"));
    SelenideElement numberPhone = $(By.xpath("//span[@class='shop-phone']"));
    SelenideElement contactButton = $(By.xpath("//a[@title='Contact Us'"));
    SelenideElement signInButton = $(By.xpath("//a[@class='login']"));



}
