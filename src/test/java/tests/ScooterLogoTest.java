package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ScooterLogoTest {

    private WebDriver driver;

    @Test
    public void clickScooterLogoOpensMainPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.acceptCookies();
        mainPage.clickScooterLogo();

        // Проверяем, что мы на главной странице
        assertEquals(
                "Открылась не главная страница Самоката",
                "https://qa-scooter.praktikum-services.ru/",
                driver.getCurrentUrl()
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
