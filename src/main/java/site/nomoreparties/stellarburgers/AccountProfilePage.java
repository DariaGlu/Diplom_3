package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AccountProfilePage {
    @FindBy(how = How.XPATH, using = ".//a[@href='/account/profile']")
    private SelenideElement profileButton;
    @FindBy(how = How.XPATH, using = ".//button[text() = 'Выход']")
    private SelenideElement exitButton;
    @FindBy(how = How.XPATH, using = ".//a[@href='/']/p[text() = 'Конструктор']")
    private SelenideElement constructorButton;
    @FindBy(how = How.XPATH, using = ".//div/a[@href='/']")
    private SelenideElement logo;

    public boolean profileButtonIsDisplayed() {
        profileButton.shouldBe(visible);
        return profileButton.isDisplayed();
    }

    @Step("Клик на кнопку 'Конструктор' из личного кабинета")
    public MainPage constructorButtonClick() {
        constructorButton.click();
        return page(MainPage.class);
    }

    @Step("Клик по логотипу из личного кабинета")
    public MainPage logoClick() {
        logo.click();
        return page(MainPage.class);
    }

    @Step("Клик по кнопке 'Выход' в личном кабинете")
    public LoginPage exitButtonClick() {
        exitButton.click();
        profileButton.shouldNotBe(visible, Duration.ofSeconds(3));
        return page(LoginPage.class);
    }
}
