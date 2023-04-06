package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    @FindBy(how = How.XPATH, using = ".//a[@href = '/register']")
    public SelenideElement registerButton;
    @FindBy(how = How.NAME, using = "name")
    private SelenideElement emailInput;
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH, using = ".//button[text() = 'Войти']")
    private SelenideElement enterButton;
    @FindBy(how = How.XPATH, using = ".//a[@href= '/forgot-password']")
    public SelenideElement forgotPasswordButton;
    @FindBy(how = How.XPATH, using = ".//h2[text() = 'Вход']")
    public SelenideElement h2Header;

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }
    public void enterButtonClick() {

    }

    public RegisterPage registerButtonClick() {
        registerButton.click();
        return page(RegisterPage.class);
    }
}
