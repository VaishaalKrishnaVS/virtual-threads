package pg.vt.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.util.CommonUtils;

import java.time.Duration;
import java.util.concurrent.Executors;

public class AutoClosable {
    private static final Logger log = LoggerFactory.getLogger(AutoClosable.class);
    public static void main(String[] args) {
        log.info("Main:: Thread: {}",Thread.currentThread());
        try(var executorService = Executors.newFixedThreadPool(3)) {
            executorService.submit(AutoClosable::someTask);
            executorService.submit(AutoClosable::someTask);
            executorService.submit(AutoClosable::someTask);
            executorService.submit(AutoClosable::someTask);
            executorService.submit(AutoClosable::someTask);
        }
    }
    private static void someTask(){
        CommonUtils.sleep(Duration.ofSeconds(2));
        log.info("Thread: {}", Thread.currentThread());
    }
}
