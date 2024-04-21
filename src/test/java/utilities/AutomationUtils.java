package utilities;

public class AutomationUtils {
    public static void sleep(long timeMS) {
        try {
            Logs.info("Esperando por %dms", timeMS);
            Thread.sleep(timeMS);
        } catch (InterruptedException interruptedException) {
            Logs.error("Error al esperar: %s", interruptedException.getLocalizedMessage());
        }
    }
}
