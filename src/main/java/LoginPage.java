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
    WebElement usernameField;

    @FindBy(id = "login-password")
    WebElement passwordField;

    @FindBy(xpath = "//span[text()='Log In']")
    WebElement loginButton;

    @FindBy(id = "username-error")
    WebElement usernameError;

    @FindBy(id = "password-error")
    WebElement passwordError;

    @FindBy(xpath = "//*[text()='Incorrect username or password.']")
    WebElement incorrectUsernameOrPasswordError;

    @FindBy(xpath = "//span[@class='Text__TextElement-sc-if376j-0 gYdBJW encore-text-body-small-bold NqzueDshzvgXEygqOGPG']")
    WebElement userIcon;

    @FindBy(xpath = "//*[text()='Profile']")
    WebElement profileButton;

    @FindBy(xpath = "//h1[@class='Text__TextElement-sc-if376j-0 ksSRyh encore-text-headline-large']")
    WebElement profileNameField;

    public LoginPage(WebDriver driver) {
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

    public boolean checkIfNameIsCorrect() {
        wait.until(ExpectedConditions.visibilityOf(userIcon));
        userIcon.click();
        profileButton.click();
        wait.until(ExpectedConditions.visibilityOf(profileNameField));
        return profileNameField.getText().equals("Test");
    }
}
