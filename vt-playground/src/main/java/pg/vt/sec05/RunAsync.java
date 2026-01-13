package pg.vt.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.util.CommonUtils;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class RunAsync {
    private static final Logger log = LoggerFactory.getLogger(RunAsync.class);

    public static void main(String[] args) {
        log.info("main starts");
        runAsync().thenRun(()-> log.info("It is done")).exceptionally(ex->{
            log.info("error: {}",ex.getMessage());
            return null;
        });
        log.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(5));
    }
    private static CompletableFuture<Void> runAsync(){
        log.info("method starts");
        var cf = CompletableFuture.runAsync(()->{
            CommonUtils.sleep(Duration.ofSeconds(3));
            throw new RuntimeException("Oops!");
        }, Executors.newVirtualThreadPerTaskExecutor());
        log.info("method ends");
        return cf;
    }
}
