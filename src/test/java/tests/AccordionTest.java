package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class AccordionTest {

    private WebDriver driver;
    private final int questionIndex;

    public AccordionTest(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7}
        });
    }

    @Test
    public void checkAccordionAnswerIsDisplayed() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
        mainPage.clickQuestion(questionIndex);

        assertTrue(
                "Ответ для вопроса " + questionIndex + " не отображается",
                mainPage.isAnswerDisplayed(questionIndex)
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
