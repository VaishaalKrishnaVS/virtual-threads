package pg.vt.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.util.CommonUtils;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceType {
    private static final Logger log = LoggerFactory.getLogger(ExecutorServiceType.class);

    public static void main(String[] args) {

    }

    private static void single(){
        execute(Executors.newSingleThreadExecutor(),3);
    }

    private static void fixed(){
        execute(Executors.newFixedThreadPool(2), 4);
    }

    private static void cached(){
        execute(Executors.newCachedThreadPool(),100);
    }
    private static void virtual(){
        execute(Executors.newVirtualThreadPerTaskExecutor(), 100_000);
    }

    private static void execute(ExecutorService executorService, int taskCount){
        try(executorService){
            for (int i = 0; i < taskCount; i++) {
                int j = i;
                executorService.submit(()->ioTask(j));
            }
        }
    }

    private static void ioTask(int i){
        log.info("Task started: {}, Thread info: {}",i,Thread.currentThread());
        CommonUtils.sleep(Duration.ofSeconds(5));
        log.info("Task completed: {}, Thread info: {}",i,Thread.currentThread());
    }

}
