package tests.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.util.Set;

import static org.junit.Assert.assertTrue;

public class YandexLogoTest {

    private WebDriver driver;

    @Test
    public void clickYandexLogoOpensYandexInNewTab() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();

        String originalWindow = driver.getWindowHandle();

        // Кликаем по логотипу Яндекса
        mainPage.clickYandexLogo();

        // Ждём появления новой вкладки
        Set<String> allWindows = driver.getWindowHandles();

        // Переключаемся на новое окно
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Проверяем, что открылась главная Яндекса
        assertTrue(
                "Открылся не Яндекс",
                driver.getCurrentUrl().contains("yandex")
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
