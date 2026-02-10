package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // ===== ЛОКАТОРЫ ПЕРВОЙ СТРАНИЦЫ =====
    private By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroField = By.className("select-search__input");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");

    // ===== ВТОРАЯ СТРАНИЦА =====
    private By orderButton = By.xpath("//button[text()='Заказать']");
    private By confirmButton = By.xpath("//button[text()='Да']");

    // ===== МОДАЛКА УСПЕХА =====
    private By successModal = By.className("Order_Modal__YZ-d3");

    // ===== МЕТОДЫ =====

    public void fillFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
    }

    public void fillAddress(String address) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressField)).sendKeys(address);
    }

    public void fillPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField)).sendKeys(phone);
    }

    // шаблон локатора храним в поле класса
    private static final String METRO_OPTION_BY_TEXT =
            "//div[contains(@class,'select-search__option') and text()='%s']";

    private By metroOption(String metro) {
        return By.xpath(String.format(METRO_OPTION_BY_TEXT, metro));
    }

    public void selectMetro(String metro) {
        wait.until(ExpectedConditions.elementToBeClickable(metroField)).sendKeys(metro);
        wait.until(ExpectedConditions.elementToBeClickable(metroOption(metro))).click();
    }


    // объединённый метод
    public void fillOrderForm(String firstName, String lastName,
                              String address, String metro, String phone) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillAddress(address);
        selectMetro(metro);
        fillPhone(phone);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public void clickConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isOrderSuccessModalDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successModal)).isDisplayed();
    }
}

