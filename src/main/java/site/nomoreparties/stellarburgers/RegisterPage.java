package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;


public class RegisterPage {
    @FindBy(how = How.XPATH, using = ".//div[label[text() = 'Имя']]/input")
    private SelenideElement nameInput;
    @FindBy(how = How.XPATH, using = ".//div[label[text() = 'Email']]/input")
    private SelenideElement emailInput;
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH, using = ".//button[text() = 'Зарегистрироваться']")
    private SelenideElement registrationButton;
    @FindBy(how = How.XPATH, using = ".//a[@href = '/login']")
    private SelenideElement enterButton;
    @FindBy(how = How.XPATH, using = ".//h2[text() = 'Регистрация']")
    private SelenideElement registrationHeader;
    @FindBy(how = How.XPATH, using = ".//p[text() = 'Некорректный пароль']")
    private SelenideElement passwordIncorrectInput;


    @Step("Ввод имени")
    public void setName(String name) {
        nameInput.setValue(name);
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public RegisterPage registrationButtonClick() {
        registrationButton.click();
        return page(RegisterPage.class);
    }

    @Step("Клик по кнопке 'Войти'")
    public LoginPage enterButtonClick() {
        enterButton.click();
        return page(LoginPage.class);
    }

    @Step("Вход пользователя с корректным паролем")
    public void registrationSuccess(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        registrationButtonClick();
        registrationHeader.shouldNotBe(visible, Duration.ofSeconds(3));
    }

    @Step("Вход пользователя с некорректным паролем")
    public boolean registrationFail(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        registrationButtonClick();
        return passwordIncorrectInput.isDisplayed();
    }
}
