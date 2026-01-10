package pg.vt.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.sec04.executorservice.Client;

import java.util.concurrent.Executors;

public class ResponseUsingFuture {
    private static final Logger log = LoggerFactory.getLogger(ResponseUsingFuture.class);

    public static void main(String[] args) throws Exception{
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            var p1 = executor.submit(()-> Client.getProduct(1));
            var p2 = executor.submit(()-> Client.getProduct(2));
            var p3 = executor.submit(()-> Client.getProduct(3));

            log.info("P1: {}", p1.get());
            log.info("P2: {}", p2.get());
            log.info("P3: {}", p3.get());
        }
    }
}
