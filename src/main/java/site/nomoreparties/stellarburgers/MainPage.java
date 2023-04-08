package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    @FindBy(how = How.XPATH, using = ".//a[@href = '/account']")
    private SelenideElement personalAccountButton;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;
    @FindBy(how = How.XPATH, using = ".//button[text() = 'Оформить заказ']")
    private SelenideElement orderButton;
    @FindBy(how = How.XPATH, using = ".//div[./span[text()='Булки']]")
    private SelenideElement bunTab;
    @FindBy(how = How.XPATH, using = ".//div[./span[text()='Соусы']]")
    private SelenideElement sauceTab;
    @FindBy(how = How.XPATH, using = ".//div[./span[text()='Начинки']]")
    private SelenideElement fillingTab;

    @Step("Клик по кнопке 'Личный кабинет' на главной странице для не авторизованного пользователя")
    public LoginPage personalAccountButtonClickToLoginPage() {
        personalAccountButton.click();
        orderButton.shouldNotBe(visible, Duration.ofSeconds(3));
        return page(LoginPage.class);
    }

    @Step("Клик по кнопке 'Личный кабинет' на главной странице для авторизованного пользователя")
    public AccountProfilePage personalAccountButtonClickToAccountProfilePage() {
        personalAccountButton.click();
        orderButton.shouldNotBe(visible, Duration.ofSeconds(3));
        return page(AccountProfilePage.class);
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public LoginPage loginButtonClick() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Ожидание видимости кнопки 'Оформить заказ' для авторизованного пользователя")
    public boolean isOrderButtonDisplayed() {
        orderButton.shouldBe(visible);
        return orderButton.isDisplayed();
    }

    @Step("Клик по вкладке 'Булки' в разделе 'Конструктор'")
    public String bunTabClick() {
        bunTab.click();
        return bunTab.getAttribute("class");
    }

    @Step("Клик по вкладке 'Соусы' в разделе 'Конструктор'")
    public String sauceTabClick() {
        sauceTab.click();
        return sauceTab.getAttribute("class");
    }

    @Step("Клик по вкладке 'Начинки' в разделе 'Конструктор'")
    public String fillingTabClick() {
        fillingTab.click();
        return fillingTab.getAttribute("class");
    }

}
