import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @FindBy(id = "login-username")
    private WebElement usernameField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(xpath = "//span[text()='Log In']")
    private WebElement loginButton;

    @FindBy(id = "username-error")
    private WebElement usernameError;

    @FindBy(id = "password-error")
    private WebElement passwordError;

    @FindBy(xpath = "//*[text()='Incorrect username or password.']")
    private WebElement incorrectUsernameOrPasswordError;

    @FindBy(xpath = "//*[@style='background-color: var(--background-base);']")
    private WebElement userIcon;

    @FindBy(xpath = "//*[text()='Profile']")
    private WebElement profileButton;

    @FindBy(xpath = "//*[@class='wCkmVGEQh3je1hrbsFBY']")
    private WebElement profileNameField;

    LoginPage(WebDriver driver) {
        super(driver);
    }

    private void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    private void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        enterUsername(username);
        enterPassword(password);
    }

    public void clearInputFields() {
        usernameField.sendKeys(Keys.CONTROL, "a");
        usernameField.sendKeys(Keys.DELETE);
        passwordField.sendKeys(Keys.CONTROL, "a");
        passwordField.sendKeys(Keys.DELETE);
    }

    public boolean isUsernameErrorPresent() {
        wait.until(ExpectedConditions.visibilityOf(usernameError));
        return usernameError.isDisplayed();
    }

    public boolean isPasswordErrorPresent() {
        wait.until(ExpectedConditions.visibilityOf(passwordError));
        return passwordError.isDisplayed();
    }

    public boolean isIncorrectUsernameOrPasswordPresent() {
        wait.until(ExpectedConditions.visibilityOf(incorrectUsernameOrPasswordError));
        return incorrectUsernameOrPasswordError.isDisplayed();
    }

    public boolean checkIfNameIsCorrect(String profileName) {
        wait.until(ExpectedConditions.visibilityOf(userIcon));
        userIcon.click();
        profileButton.click();
        wait.until(ExpectedConditions.visibilityOf(profileNameField));
        return profileNameField.getText().equals(profileName);
    }
}