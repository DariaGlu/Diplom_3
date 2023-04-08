import api.UserCreateDelete;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.LoginPage;
import site.nomoreparties.stellarburgers.MainPage;
import site.nomoreparties.stellarburgers.RegisterPage;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static org.junit.Assert.assertEquals;
import static urls.Urls.LOGIN_PAGE_URL;
import static urls.Urls.MAIN_PAGE_URL;

public class RegistrationSuccessTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
//        При необходимости провести тестирование в ЯБ, расскоментируй строки ниже
//        System.setProperty("webdriver.chrome.driver", ".//Webdriver/yandexdriver.exe");
//        System.setProperty("selenide.browser", "Chrome");
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
        name = RandomStringUtils.randomAlphanumeric(3, 10);
        email = RandomStringUtils.randomAlphanumeric(3, 10).toLowerCase() + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(6, 12);
    }

    @After
    public void cleanUp() {
        loginPage.authorization(email, password);
        String accessToken = localStorage().getItem("accessToken");
        Selenide.closeWebDriver();
        UserCreateDelete userDeletion = new UserCreateDelete();
        userDeletion.delete(accessToken)
                .statusCode(HTTP_ACCEPTED);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя, корректный пароль")
    public void registrationSucceed() {
        loginPage = mainPage.personalAccountButtonClickToLoginPage();
        RegisterPage registerPage = loginPage.registerButtonClick();
        registerPage.registrationSuccess(name, email, password);
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Пользователя не перенаправило на страницу авторизации", LOGIN_PAGE_URL, currentUrl);
    }
}
