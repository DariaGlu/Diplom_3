package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    @FindBy(how = How.XPATH, using = ".//a[@href = '/login']")
    private SelenideElement enterButton;

    public LoginPage enterButtonClick() {
        enterButton.click();
        return page(LoginPage.class);
    }
}
