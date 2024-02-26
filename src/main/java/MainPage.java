import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    @FindBy(xpath = "//span[text()='Log in']")
    private WebElement loginPage;

    MainPage(WebDriver driver){
       super(driver);
    }

    public void goToLoginPage() {
        loginPage.click();
    }
}
