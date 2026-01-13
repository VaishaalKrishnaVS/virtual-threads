package pg.vt.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.util.CommonUtils;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class SimpleCompletableFuture {
    private static final Logger log = LoggerFactory.getLogger(SimpleCompletableFuture.class);

    public static void main(String[] args) {
        log.info("main method starts");
        var tas = slowTask();
        tas.thenAccept(v->log.info("value: {}", v));
        log.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(4));
    }
    private static CompletableFuture<String> fastTask(){
        log.info("method starts");
        var cf = new CompletableFuture<String>();
        cf.complete("Hi");
        log.info("method ends");
        return cf;
    }
    private static CompletableFuture<String> slowTask(){
        log.info("slow method starts");
        var cf = new CompletableFuture<String>();
        Thread.ofVirtual().start(()->{
            CommonUtils.sleep(Duration.ofSeconds(3));
            cf.complete("Hi");
        });
        log.info("slow method ends");
        return cf;
    }
}
