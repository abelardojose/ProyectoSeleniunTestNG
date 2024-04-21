package automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AutomationUtils;
import utilities.BaseTest;
import utilities.Logs;

public class NagivationTests extends BaseTest {

    @Test(groups = {regression, smoke})
    public void FirstTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la %s", url);
        driver.get(url);
        AutomationUtils.sleep(2000);

        Logs.info("Obteniendo la url actual");
        final var getCurrentUrl = driver.getCurrentUrl();

        Logs.info("Verificando si las url son actuales");
        Assert.assertEquals(url, getCurrentUrl);
    }

    @Test(groups = {regression})
    public void SecondTest() {
        final var url1 = "https://the-internet.herokuapp.com/";

        Logs.info("Ingresando a %s", url1);
        driver.get(url1);
        AutomationUtils.sleep(2000);

        final var url2 = "https://github.com/";

        Logs.info("Ahora,ingresamos a %s", url2);
        driver.get(url2);
        AutomationUtils.sleep(3000);

        Logs.info("Regresando a la url anterior");
        driver.navigate().back();
        AutomationUtils.sleep(1500);

        Logs.info("Obteniendo la url actual");
        final var getCurrentUrl = driver.getCurrentUrl();

        Logs.info("Verificando si las actual sea %s", url1);
        Assert.assertEquals(url1, getCurrentUrl);
    }

    @Test(groups = {regression, smoke})
    public void alwaysFailTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la %s", url);
        driver.get(url);

        Logs.info("Obteniendo la url actual");
        final var getCurrentUrl = driver.getCurrentUrl();

        Logs.info("Verificando si las url son actuales");
        Assert.assertEquals("hola mundo", getCurrentUrl);
    }
}
