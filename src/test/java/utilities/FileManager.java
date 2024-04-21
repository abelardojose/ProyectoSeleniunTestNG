package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final static String screenshotPath = "src/test/resources/screenshots";

    private final static String pageStructurePath = "src/test/resources/pageStructure";

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Tomando Screenshot");

        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.FILE);
        final var path = String.format("%s/%s.png", screenshotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error al tomar screenshot: %s", ioException.getLocalizedMessage());
        }
    }

    public static void getPageSource(String fileName) {
        Logs.debug("Tomando el page source de la pagina");

        final var path = String.format("%s/page-source-%s.html", pageStructurePath, fileName);
        try {
            final var file = new File(path);

            Logs.debug("Creando los directorios padres sino existen");
            if (file.getParentFile().mkdirs()) {
                final var fileWrite = new FileWriter(file);
                final var pageSource = new WebDriverProvider().get().getPageSource();
                fileWrite.write(Jsoup.parse(pageSource).toString());
                fileWrite.close();
            }
        } catch (IOException ioException) {
            Logs.error("Error al obtener el page source: %s", ioException.getLocalizedMessage());
        }
    }

    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Borrando la evidencia de screenshot en ejecucion anterior");
            FileUtils.deleteDirectory(new File(screenshotPath));
            Logs.debug("Borrando la evidencia de pagesource en ejecucion anterior");
            FileUtils.deleteDirectory(new File(pageStructurePath));
        } catch (IOException ioException) {
            Logs.error("Error borrando evidencias anterios: %s", ioException.getLocalizedMessage());
        }
    }

    @Attachment(value = "failureScreenshot", type = "image/png")
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "pageSource", type = "text/html", fileExtension = "txt")
    public static String getPageSource() {
        return Jsoup.parse(new WebDriverProvider().get().getPageSource()).toString();
    }
}
