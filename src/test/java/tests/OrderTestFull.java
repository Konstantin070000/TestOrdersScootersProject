package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.pages.MainPage;
import tests.pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTestFull {

    private WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;

    public OrderTestFull(String firstName, String lastName, String address, String metro, String phone) {
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
    public void orderScooterTopButton() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
        mainPage.clickOrderButtonTop(); // верхняя кнопка

        OrderPage orderPage = new OrderPage(driver);
        fillAndConfirmOrder(orderPage);
    }

    @Test
    public void orderScooterBottomButton() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
        mainPage.clickOrderButtonBottom(); // нижняя кнопка

        OrderPage orderPage = new OrderPage(driver);
        fillAndConfirmOrder(orderPage);
    }

    private void fillAndConfirmOrder(OrderPage orderPage) {
        orderPage.fillFirstName(firstName);
        orderPage.fillLastName(lastName);
        orderPage.fillAddress(address);
        orderPage.selectMetro(metro);
        orderPage.fillPhone(phone);
        orderPage.clickNext();

        orderPage.clickOrder();
        orderPage.clickConfirm();

        assertTrue("Заказ не создан", orderPage.isOrderSuccessModalDisplayed());
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // закрывает браузер после каждого теста
        }
    }
}