import api.UserCreateDelete;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import site.nomoreparties.stellarburgers.ForgotPasswordPage;
import site.nomoreparties.stellarburgers.LoginPage;
import site.nomoreparties.stellarburgers.MainPage;
import site.nomoreparties.stellarburgers.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static urls.Urls.*;

public class AuthTest {
    private static String email;
    private static String password;
    private static String name;
    private static String accessToken;

    @BeforeClass
    public static void globalSetUp() {
        name = RandomStringUtils.randomAlphanumeric(3, 10);
        email = RandomStringUtils.randomAlphanumeric(3, 10).toLowerCase() + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(6, 12);
        UserCreateDelete userCreateDelete = new UserCreateDelete();
        ValidatableResponse response = userCreateDelete.create(email, password, name);
        accessToken = response.extract().path("accessToken");
    }

    @AfterClass
    public static void globalCleanUp() {

        UserCreateDelete userCreateDelete = new UserCreateDelete();
        userCreateDelete.delete(accessToken);
    }

    @Before
    public void setUp() {
//        При необходимости провести тестирование в ЯБ, расскоментируй строки ниже
//        System.setProperty("webdriver.chrome.driver", ".//Webdriver/yandexdriver.exe");
//        System.setProperty("selenide.browser", "Chrome");
        Configuration.startMaximized = true;
    }

    @After
    public void cleanUp() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Вход пользователя через кнопку 'Войти в аккаунт' на главной странице")
    public void authLoginButtonMainPage() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        LoginPage loginPage = mainPage.loginButtonClick();
        loginPage.authorization(email, password);
        boolean orderButtonIsDisplayed = mainPage.isOrderButtonDisplayed();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Пользователя не перенаправило на главную страницу", MAIN_PAGE_URL, currentUrl);
        assertTrue("Кнопка 'Оформить заказ' не появилась", orderButtonIsDisplayed);
    }

    @Test
    @DisplayName("Вход пользователя через кнопку 'Личный кабинет' на главной странице")
    public void authPersonalAccountMainPage() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        LoginPage loginPage = mainPage.personalAccountButtonClickToLoginPage();
        loginPage.authorization(email, password);
        boolean orderButtonIsDisplayed = mainPage.isOrderButtonDisplayed();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Пользователя не перенаправило на главную страницу", MAIN_PAGE_URL, currentUrl);
        assertTrue("Кнопка 'Оформить заказ' не появилась", orderButtonIsDisplayed);
    }

    @Test
    @DisplayName("Вход пользователя через кнопку 'Войти' на странице регистрации")
    public void authEnterButtonRegisterPage() {
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        LoginPage loginPage = registerPage.enterButtonClick();
        MainPage mainPage = loginPage.authorization(email, password);
        boolean orderButtonIsDisplayed = mainPage.isOrderButtonDisplayed();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Пользователя не перенаправило на главную страницу", MAIN_PAGE_URL, currentUrl);
        assertTrue("Кнопка 'Оформить заказ' не появилась", orderButtonIsDisplayed);
    }

    @Test
    @DisplayName("Вход пользователя через кнопку 'Войти' на странице восстановления пароля")
    public void authEnterButtonForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = open(FORGOT_PASSWORD_URL, ForgotPasswordPage.class);
        LoginPage loginPage = forgotPasswordPage.enterButtonClick();
        MainPage mainPage = loginPage.authorization(email, password);
        boolean orderButtonIsDisplayed = mainPage.isOrderButtonDisplayed();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Пользователя не перенаправило на главную страницу", MAIN_PAGE_URL, currentUrl);
        assertTrue("Кнопка 'Оформить заказ' не появилась", orderButtonIsDisplayed);
    }

}
