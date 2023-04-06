package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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

    @Step("New user registration")
    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        registrationButtonClick();
        registrationHeader.shouldNotBe(Condition.visible);
    }
    @Step("Click enter button on registration page")
    public void enterButtonClick() {
        enterButton.click();
    }

}
