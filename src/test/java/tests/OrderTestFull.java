package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTestFull extends BaseTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;

    public OrderTestFull(String firstName, String lastName,
                         String address, String metro, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
    }

    @Parameterized.Parameters(name = "user={0} {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Ленина, 1",   "Черкизовская", "+79991234567"},
                {"Пётр", "Петров", "ул. Пушкина, 10", "Маяковская",   "+79997654321"}
        });
    }

    @Test
    public void orderScooterShouldBeCreatedFromTopAndBottomButtons() {
        checkOrderFromButton("top");
        checkOrderFromButton("bottom");
    }

    private void checkOrderFromButton(String button) {
        // на каждый заказ — заново открываем главную страницу (без этого второй заказ может стартовать не с главной)
        mainPage.open();
        mainPage.acceptCookies();

        if ("top".equals(button)) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        OrderPage orderPage = new OrderPage(driver);

        orderPage.fillOrderForm(firstName, lastName, address, metro, phone);
        orderPage.clickNext();

        orderPage.clickOrder();
        orderPage.clickConfirm();

        assertTrue("Заказ не создан из кнопки: " + button, orderPage.isOrderSuccessModalDisplayed());
    }
}
