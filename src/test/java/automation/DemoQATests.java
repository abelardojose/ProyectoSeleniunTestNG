package automation;

import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.time.Duration;

public class DemoQATests extends BaseTest {
    @Test
    public void keyboard1Test() {
        Logs.info("Navegando por la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final var fullName = faker.name().fullName();
        Logs.debug("full name: %s", fullName);

        Logs.debug("Instanciar la clase Actions ");
        final var action = new Actions(driver);
        final var imputFullName = driver.findElement(By.id("userName"));

        action.contextClick(imputFullName);
        Logs.info("Presionar la tecla SHIFT");
        action.keyDown(Keys.SHIFT);
        Logs.info("Ingresar Valores en el campo Full Name");
        action.sendKeys(fullName);
        Logs.info("Dejar de presionar la tecla SHIFT");
        action.keyUp(Keys.SHIFT);
        Logs.debug("Realiza todas las acciones");
        action.perform();

        Logs.info("Validamos el texto en mayuscula");
        Assert.assertEquals(driver.findElement(By.id("userName")).getAttribute("value"), fullName.toUpperCase());
    }

    @Test
    public void keyboard2Test() {
        Logs.info("Navegando por la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final var fullAddress = faker.address().fullAddress();
        final var actions = new Actions(driver);
        Logs.debug("full Adrres: %s", fullAddress);

        final var imputCurrentAddress = driver.findElement(By.id("currentAddress"));

        Logs.info("Ingreso la direccion al Current Address");
        imputCurrentAddress.sendKeys(fullAddress);

        Logs.info("Clickeamos el Current Address");
        //imputCurrentAddress.click();

        Logs.info("Seleccionamos y copiamos el texto");
        actions
                .contextClick(imputCurrentAddress)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .sendKeys("c")
                .keyUp(Keys.CONTROL)
                .perform();

        final var imputPermanentAddress = driver.findElement(By.id("permanentAddress"));
        Logs.info("Clickeamos el Permanent Address");
        //imputPermanentAddress.click();

        Logs.info("Pegamos el texto");
        actions
                .contextClick(imputPermanentAddress)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();

        Logs.info("Validamos los texto sean iguales");
        Assert.assertEquals(imputCurrentAddress.getAttribute("value"), imputPermanentAddress.getAttribute("value"));
    }

    @Test
    public void droppableTest() {
        Logs.info("Navegando por la pagina");
        driver.get("https://demoqa.com/droppable");

        final var actions = new Actions(driver);

        final var elementOrigen = driver.findElement(By.id("draggable"));
        final var elementDestino = driver.findElement(By.id("droppable"));

        Logs.info("Mover el elemento origen al destino");
        actions.
                dragAndDrop(elementOrigen, elementDestino)
                .perform();

        Logs.info("Validamos Texto que se genera al mover el elemento");
        Assert.assertEquals(driver.findElement(By.id("droppable")).getText(), "Dropped!");
    }

    @Test
    public void hoverTest() {
        Logs.info("Navegando por la pagina");
        driver.get("https://demoqa.com/tool-tips");

        final var boton = driver.findElement(By.id("toolTipButton"));
        final var actions = new Actions(driver);

        Logs.info("Mover el cursor hacia el elemento");
        actions
                .moveToElement(boton)
                .pause(1500)
                .perform();
        Logs.info("Validamos toolip sea visible");
        Assert.assertEquals(boton.getAttribute("aria-describedby"), "buttonToolTip");
    }

    @Test
    public void alert1Test() {
        Logs.info("Navegando por la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Logs.info("Clickenado boton de alerta");
        driver.findElement(By.id("alertButton")).click();

        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());

        Logs.info("Valindando texto de la alerta");
        Assert.assertEquals(alert.getText(), "You clicked a button");

        Logs.info("Clickeando aceptar la alerta");
        alert.accept();
    }

    @Test
    public void alert2Test() {
        Logs.info("Navegando por la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("text-center")));

        new Actions(driver)
                .scrollToElement(driver.findElement(By.xpath("//div[text()='Book Store Application']"))).pause(2000).perform();

        Logs.info("Clickenado boton de alerta");
        driver.findElement(By.id("confirmButton")).click();

        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());

        alert.dismiss();

        Logs.info("Validando seleccion");
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Cancel']")).isDisplayed());
    }

    @Test
    public void promt1Test() {
        Logs.info("Navegando por la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("text-center")));

        new Actions(driver)
                .scrollToElement(driver.findElement(By.xpath("//div[text()='Book Store Application']"))).pause(2000).perform();
        Logs.info("Clickenado boton de alerta");
        driver.findElement(By.id("promtButton")).click();
        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());
        final var inputName = new Faker().name().fullName();
        alert.sendKeys(inputName);
        alert.accept();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + inputName + "']")).isDisplayed());
    }
}
