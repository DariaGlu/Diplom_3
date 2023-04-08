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
    @FindBy(how = How.XPATH, using = ".//h2[text() = 'Вход']")
    private SelenideElement enterHeader;

    @Step("Ввод email")
    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public MainPage enterButtonClick() {
        enterButton.click();
        enterHeader.shouldNotBe(visible, Duration.ofSeconds(3));
        return page(MainPage.class);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public RegisterPage registerButtonClick() {
        registerButton.click();
        return page(RegisterPage.class);
    }

    @Step("Вход пользователя")
    public MainPage authorization(String email, String password) {
        setEmail(email);
        setPassword(password);
        enterButtonClick();
        return page(MainPage.class);
    }

    @Step("Ожидание видимости кнопки 'Войти'")
    public boolean enterButtonIsDisplayed() {
        enterButton.shouldBe(visible);
        return enterButton.isDisplayed();
    }
}
