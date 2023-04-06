package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    @FindBy(how = How.XPATH, using = ".//a[@href = '/register']")
    private SelenideElement registerButton;
    @FindBy(how = How.NAME, using = "name")
    private SelenideElement emailInput;
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH, using = ".//button[text() = 'Войти']")
    private SelenideElement enterButton;
    @FindBy(how = How.XPATH, using = ".//a[@href= '/forgot-password']")
    private SelenideElement forgotPasswordButton;
    @FindBy(how = How.XPATH, using = ".//h2[text() = 'Вход']")
    private SelenideElement enterHeader;

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public MainPage enterButtonClick() {
        enterButton.click();
        return page(MainPage.class);
    }

    public RegisterPage registerButtonClick() {
        registerButton.click();
        return page(RegisterPage.class);
    }

    @Step("User authorization")
    public void authorization(String email, String password) {
        setEmail(email);
        setPassword(password);
        enterButtonClick();
        enterHeader.shouldNotBe(visible, Duration.ofSeconds(3));
    }
}
