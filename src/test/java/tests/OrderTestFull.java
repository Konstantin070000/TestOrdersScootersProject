package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTestFull {

    private WebDriver driver;
    private MainPage mainPage;

    private final String orderButton; // "top" или "bottom"
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;

    public OrderTestFull(String orderButton,
                         String firstName, String lastName,
                         String address, String metro, String phone) {
        this.orderButton = orderButton;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
    }

    @Parameterized.Parameters(name = "button={0}, user={1} {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"top",    "Иван", "Иванов",  "ул. Ленина, 1",   "Черкизовская", "+79991234567"},
                {"bottom", "Иван", "Иванов",  "ул. Ленина, 1",   "Черкизовская", "+79991234567"},
                {"top",    "Пётр", "Петров",  "ул. Пушкина, 10", "Маяковская",   "+79997654321"},
                {"bottom", "Пётр", "Петров",  "ул. Пушкина, 10", "Маяковская",   "+79997654321"}
        });
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
    }

    @Test
    public void orderScooterShouldBeCreated() {
        if ("top".equals(orderButton)) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        OrderPage orderPage = new OrderPage(driver);
        fillAndConfirmOrder(orderPage);

        assertTrue("Заказ не создан", orderPage.isOrderSuccessModalDisplayed());
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
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
