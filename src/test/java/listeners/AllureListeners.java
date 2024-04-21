package listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import utilities.FileManager;
import utilities.Logs;

public class AllureListeners implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        Logs.debug("Antes step stop de Allure");
        final var resulType = result.getStatus();
        switch (resulType) {
            case BROKEN, FAILED -> {
                FileManager.getScreenshot();
                FileManager.getPageSource();
            }
        }
    }
}
