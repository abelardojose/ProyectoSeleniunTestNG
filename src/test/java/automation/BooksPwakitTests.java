package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class BooksPwakitTests extends BaseTest {

    @Test
    public void shadowDom1Test() {
        final var url = "https://books-pwakit.appspot.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.debug("Obteniendo el Shadow Root");
        final var shadowRoot = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']")).getShadowRoot();

        Logs.debug("Obteniendo el footer a travez del shadow Root");
        final var footer = shadowRoot.findElement(By.cssSelector("footer > p"));

        Logs.info("Validando el footer");
        Assert.assertEquals(footer.getText(), "Made with <3 by the Polymer team.");
    }
}
