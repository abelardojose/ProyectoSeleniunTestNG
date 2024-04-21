package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.io.File;

public class TestPagesTests extends BaseTest {

    @Test
    public void uploadFileTest() {
        final var url = "https://testpages.eviltester.com/styled/file-upload-test.html";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        final var file = new File("src/test/resources/samples/img.png");

        Logs.info("Subiendo la imagen");
        driver.findElement(By.id("fileinput")).sendKeys(file.getAbsolutePath());

        Logs.info("Clickeando el boton imagen");
        driver.findElement(By.id("itsanimage")).click();

        Logs.info("Clickeando el boton Upload");
        driver.findElement(By.name("upload")).click();

        Logs.info("Verificando el texto 'You uploaded this image:'");
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='You uploaded this image:']")).isDisplayed());

    }
}
