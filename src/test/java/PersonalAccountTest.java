import api.UserCreateDelete;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import site.nomoreparties.stellarburgers.AccountProfilePage;
import site.nomoreparties.stellarburgers.LoginPage;
import site.nomoreparties.stellarburgers.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static urls.Urls.*;

public class PersonalAccountTest {
    private static final String NAME = RandomStringUtils.randomAlphanumeric(3, 10);
    private static final String EMAIL = RandomStringUtils.randomAlphanumeric(3, 10).toLowerCase() + "@yandex.ru";
    private static final String PASSWORD = RandomStringUtils.randomAlphanumeric(6, 12);
    private static String accessToken;
    private MainPage mainPage;

    @BeforeClass
    public static void globalSetUp() {
        UserCreateDelete userCreateDelete = new UserCreateDelete();
        ValidatableResponse response = userCreateDelete.create(EMAIL, PASSWORD, NAME);
        accessToken = response.extract().path("accessToken");
    }

    @AfterClass
    public static void globalCleanUp() {
        UserCreateDelete userCreateDelete = new UserCreateDelete();
        userCreateDelete.delete(accessToken);
    }

    @Before
    public void setUp() {
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
        LoginPage loginPage = mainPage.loginButtonClick();
        loginPage.authorization(EMAIL, PASSWORD);
    }

    @After
    public void cleanUp() {
        Selenide.closeWebDriver();
    }

    @Test
    public void personalAccountRedirect() {
        AccountProfilePage accountProfilePage = mainPage.personalAccountButtonClickToAccountProfilePage();
        boolean profileButtonIsDisplayed = accountProfilePage.profileButtonIsDisplayed();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Пользователя не перенаправило в личный кабинет", ACCOUNT_PROFILE_URL, currentUrl);
        assertTrue("Кнопка 'Профиль' не появилась", profileButtonIsDisplayed);
    }

    @Test
    public void constructorMainPageRedirect() {
        AccountProfilePage accountProfilePage = mainPage.personalAccountButtonClickToAccountProfilePage();
        mainPage = accountProfilePage.constructorButtonClick();
        boolean orderButtonIsDisplayed = mainPage.isOrderButtonDisplayed();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Пользователя не перенаправило на главную страницу", MAIN_PAGE_URL, currentUrl);
        assertTrue("Кнопка 'Оформить заказ' не появилась", orderButtonIsDisplayed);
    }

    @Test
    public void exitLoginPageRedirect() {
        AccountProfilePage accountProfilePage = mainPage.personalAccountButtonClickToAccountProfilePage();
        LoginPage loginPage = accountProfilePage.exitButtonClick();
        boolean enterButtonIsDisplayed = loginPage.enterButtonIsDisplayed();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("Пользователя не перенаправило на страницу авторизации", LOGIN_PAGE_URL, currentUrl);
        assertTrue("Кнопка 'Оформить заказ' не появилась", enterButtonIsDisplayed);
    }

}
