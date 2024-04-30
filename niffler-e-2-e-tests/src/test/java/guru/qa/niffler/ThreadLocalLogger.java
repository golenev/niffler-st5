package guru.qa.niffler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalLogger {

    private static final ThreadLocal<Logger> LOGGER = ThreadLocal.withInitial(() -> LoggerFactory.getLogger(ThreadLocalLogger.class));

    public static Logger getLogger() {
        return LOGGER.get();
    }
}
