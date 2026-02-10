package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YandexLogoTest extends BaseTest {

    @Test
    public void clickYandexLogoOpensYandexInNewTab() {
        String originalWindow = driver.getWindowHandle();

        mainPage.clickYandexLogo();

        new WebDriverWait(driver, 5).until(d -> d.getWindowHandles().size() > 1);

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Assert.assertTrue("Открылся не Яндекс", driver.getCurrentUrl().contains("yandex"));
    }
}

