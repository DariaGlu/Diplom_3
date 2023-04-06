import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Test;
import site.nomoreparties.stellarburgers.LoginPage;
import site.nomoreparties.stellarburgers.MainPage;
import site.nomoreparties.stellarburgers.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    @Test
    public void registrationSuccess() {
        Configuration.startMaximized = true;
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site",
                MainPage.class);
        LoginPage loginPage = mainPage.headerPersonalAccountButtonClick();
        RegisterPage registerPage = loginPage.registerButtonClick();
        registerPage.registration("Маша", "mashka1993@mail.ru", "123456");
        loginPage.h2Header.shouldNotBe(visible);
        String url = "https://stellarburgers.nomoreparties.site/login";
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(url, currentUrl);
    }

}
