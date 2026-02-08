package tests.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;

    public OrderTest(String firstName, String lastName, String address, String metro, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Ленина, 1", "Черкизовская", "+79991234567"},
                {"Пётр", "Петров", "ул. Пушкина, 10", "Маяковская", "+79997654321"}
        });
    }

    @Test
    public void orderScooter() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
        // Нажимаем верхнюю кнопку "Заказать"
        mainPage.clickOrderButtonTop();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstName(firstName);
        orderPage.fillLastName(lastName);
        orderPage.fillAddress(address);
        orderPage.selectMetro(metro);
        orderPage.fillPhone(phone);
        orderPage.clickNext();

        orderPage.clickOrder();
        orderPage.clickConfirm();

        // проверяем модальное окно с успешным заказом
        assertTrue("Заказ не создан", orderPage.isOrderSuccessModalDisplayed());
            driver.quit();
    }
}