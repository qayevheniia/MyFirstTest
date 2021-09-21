package SelenideTest;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class GooglePageObject {

SelenideElement searchFields = $(".gLFyf");
public SelenideElement getSearchFields(){
    return searchFields;
}
}
