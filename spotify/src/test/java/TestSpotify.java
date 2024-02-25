import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSpotify {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private final static String BASE_URL = "https://open.spotify.com/";

    @BeforeEach
    public void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void initPages() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginFunctionalityWithEmptyCredentials() {
        driver.get(BASE_URL);
        mainPage.goToLoginPage();
        loginPage.login("saqo", "saqo123");
        loginPage.clearInputFields();
        Assertions.assertTrue(loginPage.isUsernameErrorPresent() && loginPage.isPasswordErrorPresent(), "Error message for empty username or password isn't shown");
        loginPage.login("saqo", "saqo123");
    }

    @Test
    public void testLoginFunctionalityWithWrongCredentials() {
        driver.get(BASE_URL);
        mainPage.goToLoginPage();
        loginPage.login("saqo", "saqo123");
        loginPage.clickOnLoginButton();
        Assertions.assertTrue(loginPage.isIncorrectUsernameOrPasswordPresent(), "Error message for incorrect username or password isn't shown");
    }

    @Test
    public void testLoginFunctionalityWithCorrectCredentials() {
        driver.get(BASE_URL);
        mainPage.goToLoginPage();
        loginPage.login("dmail20212021@gmail.com", "iamthepassword*");
        loginPage.clickOnLoginButton();
        Assertions.assertTrue(loginPage.checkIfNameIsCorrect(), "Error message for wrong name");
    }

    @AfterEach
    public void quitBrowser() {
        driver.quit();
    }
}
