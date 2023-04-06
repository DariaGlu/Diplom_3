package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    @FindBy(how = How.XPATH, using = ".//a[@href = '/account']")
    private SelenideElement headerPersonalAccountButton;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;
    @FindBy(how = How.XPATH, using = ".//button[text() = 'Оформить заказ']")
    private SelenideElement orderButton;
    @FindBy(how = How.XPATH, using = ".//div/span[text()='Булки']")
    private SelenideElement bunTab;
    @FindBy(how = How.XPATH, using = ".//div/span[text()='Соусы']")
    private SelenideElement sauceTab;
    @FindBy(how = How.XPATH, using = ".//div/span[text()='Начинки']")
    private SelenideElement fillingTab;

    public LoginPage headerPersonalAccountButtonClick() {
        headerPersonalAccountButton.click();
        return page(LoginPage.class);
    }

    public LoginPage loginButtonClick() {
        loginButton.click();
        return page(LoginPage.class);
    }

    public boolean isOrderButtonDisplayed() {
        return orderButton.isDisplayed();
    }

}
