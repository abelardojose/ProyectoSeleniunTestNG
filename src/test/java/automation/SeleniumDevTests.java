package automation;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SeleniumDevTests extends BaseTest {

    @Test
    public void scroll1Test() {
        Logs.info("Navegando por la pagina");
        driver.get("https://www.selenium.dev/selenium/web/scroll.html");

        final var faker = new Faker();
        final var numero = faker.number().numberBetween(5, 9);
        Logs.debug("Numero: %d", numero);

        final var dinamic = String.format("line%d", numero);
        final var lineId = driver.findElement(By.id(dinamic));

        Logs.info("Realizar scroll y clickear");
        new Actions(driver)
                .scrollToElement(lineId)
                .pause(1500)
                .perform();

        lineId.click();

        Logs.info("Validar seleccion");
        Assert.assertEquals(driver.findElement(By.id("clicked")).getText(), dinamic);
    }

    @Test
    public void scroll2Test() {
        Logs.info("Navegando por la pagina");
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/page_with_frame_out_of_view.html");

        final var iframe = driver.findElement(By.name("frame"));

        new Actions(driver)
                .scrollToElement(iframe)
                .pause(3000)
                .perform();

        Logs.debug("Cambiando el Focus al iframe");
        driver.switchTo().frame("frame");

        Logs.info("Clickeando el checkbox");
        final var checkboxox = driver.findElement(By.name("checkbox"));
        checkboxox.click();

        Logs.info("Verificando que este clickeado el checkbox");
        Assert.assertTrue(checkboxox.isSelected());
    }
}
