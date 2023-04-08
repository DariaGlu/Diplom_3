package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
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

    public LoginPage personalAccountButtonClickToLoginPage() {
        personalAccountButton.click();
        orderButton.shouldNotBe(visible, Duration.ofSeconds(3));
        return page(LoginPage.class);
    }

    public AccountProfilePage personalAccountButtonClickToAccountProfilePage() {
        personalAccountButton.click();
        orderButton.shouldNotBe(visible, Duration.ofSeconds(5));
        return page(AccountProfilePage.class);
    }

    public LoginPage loginButtonClick() {
        loginButton.click();
        return page(LoginPage.class);
    }

    public boolean isOrderButtonDisplayed() {
        orderButton.shouldBe(visible);
        return orderButton.isDisplayed();
    }

    public void bunTabClick() {
        bunTab.click();
    }

    public String returnCurrentBunTab() {
        return bunTab.getAttribute("class");
    }

    public void sauceTabClick() {
        sauceTab.click();
    }

    public String returnCurrentSauceTab() {
        return sauceTab.getAttribute("class");
    }

    public void fillingTabClick() {
        fillingTab.click();
    }

    public String returnCurrentFillingTab() {
        return fillingTab.getAttribute("class");
    }

}
