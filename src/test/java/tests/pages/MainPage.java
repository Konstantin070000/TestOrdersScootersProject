package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    // ===== ЛОГОТИПЫ =====
    private By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    private By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    // ===== COOKIES =====
    private By cookieButton = By.id("rcc-confirm-button");

    // ===== АККОРДЕОН =====
    private By question(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By answer(int index) {
        return By.id("accordion__panel-" + index);
    }

    public void clickQuestion(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(question(index))).click();
    }

    public boolean isAnswerDisplayed(int index) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answer(index)))
                .isDisplayed();
    }

    // ===== КНОПКИ "ЗАКАЗАТЬ" =====
    private By orderButtonTop =
            By.xpath("//button[contains(@class,'Button_Button') and text()='Заказать']");

    private By orderButtonBottom =
            By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public void clickOrderButtonTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }

    public void clickOrderButtonBottom() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();
    }

    // ===== ЛОГОТИПЫ =====
    public void clickScooterLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(scooterLogo)).click();
    }

    public void clickYandexLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(yandexLogo)).click();
    }

    // ===== СЛУЖЕБНЫЕ МЕТОДЫ =====
    public void open() {
        driver.get(URL);
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }
}

