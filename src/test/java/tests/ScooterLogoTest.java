package tests;

import org.junit.Assert;
import org.junit.Test;

public class ScooterLogoTest extends BaseTest {

    @Test
    public void clickScooterLogoOpensMainPage() {
        mainPage.clickScooterLogo();

        Assert.assertEquals(
                "Открылась не главная страница Самоката",
                "https://qa-scooter.praktikum-services.ru/",
                driver.getCurrentUrl()
        );
    }
}

