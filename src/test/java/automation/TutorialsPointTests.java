package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class TutorialsPointTests extends BaseTest {
    @Test
    public void tabTest() {
        final var url = "https://www.tutorialspoint.com/selenium/practice/browser-windows.php";
        Logs.info("ingresar a la pagina : %s", url);
        driver.get(url);

        Logs.info("Obteniendo el valor de la pestaña actual");
        final var tabId = driver.getWindowHandle();
        Logs.debug("tabId: %s", tabId);

        Logs.info("Clickeando el boton New Tab");
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();

        final var windowsHandlesSet = driver.getWindowHandles();
        Logs.debug("Windows handles Set: %s", windowsHandlesSet);

        Logs.info("Posicionandome en la nueva pestaña");
        for (var windowsHandle : windowsHandlesSet) {
            if (!windowsHandle.equals(tabId)) {
                driver.switchTo().window(windowsHandle);
                Logs.debug("Focus de la pestaña: %s", windowsHandle);
            }
        }
        Logs.info("Verificamos que este el texto New Tab");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='New Tab']")).getText(), "New Tab");


        Logs.info("Cerrar pestaña");
        driver.close();

        Logs.debug("Regresando el focus a la ventana %s:", tabId);
        driver.switchTo().window(tabId);

        Logs.info("Verificamos que estemos en la pestaña principal");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Browser Windows']")).getText(), "Browser Windows");
    }

    @Test
    public void windowsTest() {
        final var url = "https://www.tutorialspoint.com/selenium/practice/browser-windows.php";
        Logs.info("ingresar a la pagina : %s", url);
        driver.get(url);

        Logs.info("Obteniendo el valor de la ventana actual");
        final var windowId = driver.getWindowHandle();
        Logs.debug("windowsId: %s", windowId);

        Logs.info("Clickeando el boton New Tab");
        driver.findElement(By.xpath("//button[text()='New Window']")).click();

        final var windowsHandlesSet = driver.getWindowHandles();
        Logs.debug("Windows handles Set: %s", windowsHandlesSet);

        Logs.info("Posicionandome en la nueva pestaña");
        for (var windowHandle : windowsHandlesSet) {
            if (!windowHandle.equals(windowId)) {
                driver.switchTo().window(windowHandle);
                Logs.debug("Focus de la pestaña: %s", windowHandle);
            }
        }

        driver.manage().window().maximize();
        Logs.info("Verificamos que este el texto New Tab");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='New Window']")).getText(), "New Window");
        driver.close();

        driver.switchTo().window(windowId);

        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='New Window']")).isDisplayed());
    }

    @Test
    public void frameTest() {
        final var url = "https://www.tutorialspoint.com/selenium/practice/nestedframes.php";
        Logs.info("ingresar a la pagina : %s", url);
        driver.get(url);

        Logs.info("Posicionarse en el frame");
        driver.switchTo().frame("frame1");

        Logs.info("Verificar el titulo 'New Tab'");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='New Tab']")).getText(), "New Tab");

        Logs.info("Regresando al Contenido Principal");
        driver.switchTo().defaultContent();

        Logs.info("Verificar el titulo 'Nested frames'");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Nested Frames']")).getText(), "Nested Frames");


    }
}
