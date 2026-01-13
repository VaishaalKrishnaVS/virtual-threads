package pg.vt.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.util.CommonUtils;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class SupplyAsync {
    private static final Logger log = LoggerFactory.getLogger(SupplyAsync.class);

    public static void main(String[] args) {
        log.info("main start");

        var task = task();
        task.thenAccept(v->log.info("Received: {}", v));
        log.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(5));
    }
    private static CompletableFuture<String> task(){
        log.info("method starts");
        Supplier<String> supplier = ()->{
            CommonUtils.sleep(Duration.ofSeconds(3));
            return "Hi";
        };
        var cf = CompletableFuture.supplyAsync(supplier, Executors.newVirtualThreadPerTaskExecutor());
        log.info("method ends");
        return cf;
    }
}
