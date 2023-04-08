import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import site.nomoreparties.stellarburgers.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static urls.Urls.MAIN_PAGE_URL;

public class MainPageConstructorTest {
    private static MainPage mainPage;

    @BeforeClass
    public static void globalSetUp() {
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @Test
    public void checkBunTabCurrent() {
        mainPage.fillingTabClick();
        mainPage.bunTabClick();
        String currentTab = mainPage.returnCurrentBunTab();
        Assert.assertTrue(currentTab.contains("current"));
    }

    @Test
    public void checkSauceTabCurrent() {
        mainPage.sauceTabClick();
        String currentTab = mainPage.returnCurrentSauceTab();
        Assert.assertTrue(currentTab.contains("current"));
    }

    @Test
    public void checkFillingTabCurrent() {
        mainPage.fillingTabClick();
        String currentTab = mainPage.returnCurrentFillingTab();
        Assert.assertTrue(currentTab.contains("current"));
    }
}
