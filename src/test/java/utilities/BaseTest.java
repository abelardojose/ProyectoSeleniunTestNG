package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({SuiteListeners.class, TestListeners.class})
public class BaseTest {
    protected SoftAssert softAssert;
    protected final String regression = "regression";
    protected final String smoke = "smoke";
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();

        Logs.debug("Inicializar driver");
        driver = new ChromeDriver();
        Logs.debug("Maximizar pantalla");
        driver.manage().window().maximize();
        Logs.debug("Borrar cookies");
        driver.manage().deleteAllCookies();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        new WebDriverProvider().set(driver);


    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        Logs.debug("Finalizar driver");
        driver.quit();
    }
}
