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
    @FindBy(how = How.XPATH, using = ".//a[@ahref = '/login']")
    private SelenideElement enterButton;
    @FindBy(how = How.XPATH, using = ".//h2[text() = 'Регистрация']")
    private SelenideElement registrationHeader;
    @FindBy(how = How.XPATH, using = ".//p[text() = 'Некорректный пароль']")
    private SelenideElement passwordIncorrectInput;


    public void setName(String name) {
        nameInput.setValue(name);
    }

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public RegisterPage registrationButtonClick() {
        registrationButton.click();
        return page(RegisterPage.class);
    }

    public void enterButtonClick() {
        enterButton.click();
    }

    @Step("New user successful registration, correct password")
    public void registrationSuccess(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        registrationButtonClick();
        registrationHeader.shouldNotBe(visible, Duration.ofSeconds(3));
    }

    @Step("New user failed registration, incorrect password")
    public boolean registrationFail(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        registrationButtonClick();
        return passwordIncorrectInput.isDisplayed();
    }
}
