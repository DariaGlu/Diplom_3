package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    @FindBy(how = How.XPATH, using = ".//a[@href = '/login']")
    private SelenideElement enterButton;

    @Step("Клик по кнопке 'Войти' на странице восстановления пароля")
    public LoginPage enterButtonClick() {
        enterButton.click();
        return page(LoginPage.class);
    }
}
