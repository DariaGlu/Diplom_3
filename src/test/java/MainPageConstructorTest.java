import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static urls.Urls.MAIN_PAGE_URL;

public class MainPageConstructorTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
//        При необходимости провести тестирование в ЯБ, расскоментируй строки ниже
//        System.setProperty("webdriver.chrome.driver", ".//Webdriver/yandexdriver.exe");
//        System.setProperty("selenide.browser", "Chrome");
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @Test
    @DisplayName("Проверка перехода на вкладку 'Булочки' в разделе 'Конструктор'")
    public void checkBunTabCurrent() {
        mainPage.fillingTabClick();
        String currentTab = mainPage.bunTabClick();
        Assert.assertTrue("Вкладка неактивна", currentTab.contains("current"));
    }

    @Test
    @DisplayName("Проверка перехода на вкладку 'Соусы' в разделе 'Конструктор'")
    public void checkSauceTabCurrent() {
        String currentTab = mainPage.sauceTabClick();
        Assert.assertTrue("Вкладка неактивна", currentTab.contains("current"));
    }

    @Test
    @DisplayName("Проверка перехода на вкладку 'Начинки' в разделе 'Конструктор'")
    public void checkFillingTabCurrent() {
        String currentTab = mainPage.fillingTabClick();
        Assert.assertTrue("Вкладка неактивна", currentTab.contains("current"));
    }
}
