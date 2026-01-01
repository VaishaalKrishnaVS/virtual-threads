package pg.vt.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void someIntensiveIOTask(int i) {
        log.info("starting task no: {} ", i);
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("completed task: {}", i);

    }

}
