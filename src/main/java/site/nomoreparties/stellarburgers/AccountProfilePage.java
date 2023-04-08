package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
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
    @FindBy(how = How.XPATH, using = ".//a[@href='/']")
    private SelenideElement constructorButton;

    public boolean profileButtonIsDisplayed() {
        profileButton.shouldBe(visible);
        return profileButton.isDisplayed();
    }

    public MainPage constructorButtonClick() {
        constructorButton.click();
        return page(MainPage.class);
    }

    public LoginPage exitButtonClick() {
        exitButton.click();
        profileButton.shouldNotBe(visible, Duration.ofSeconds(5));
        return page(LoginPage.class);
    }
}
