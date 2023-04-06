import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.LoginPage;
import site.nomoreparties.stellarburgers.MainPage;
import site.nomoreparties.stellarburgers.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;
import static urls.Urls.MAIN_PAGE_URL;

public class RegistrationFailTest {
    private MainPage mainPage;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL,
                MainPage.class);
        name = RandomStringUtils.randomAlphanumeric(3, 10);
        email = RandomStringUtils.randomAlphanumeric(3, 10).toLowerCase() + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(1, 5);
    }

    @Test
    @DisplayName("Неуспешная регистрация, некорректный пароль")
    public void registrationFailed() {
        LoginPage loginPage = mainPage.headerPersonalAccountButtonClick();
        RegisterPage registerPage = loginPage.registerButtonClick();
        boolean didRegistrationFailed = registerPage.registrationFail(name, email, password);
        assertTrue("Регистрация не провалилась, сообщение о некорректном пароле не выведено", didRegistrationFailed);
    }
}
