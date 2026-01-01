package pg.vt.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.util.CommonUtils;

import java.util.concurrent.CountDownLatch;

public class CPUIntensiveTaskDemo {
    private static final Logger log = LoggerFactory.getLogger(CPUIntensiveTaskDemo.class);
    private static final int TASK_COUNT = 3*Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        log.info("Task count: {}", TASK_COUNT);
        for(int i=0;i<3;i++){
            var timeTaken = CommonUtils.timer(()->demo(Thread.ofVirtual()));
            log.info("Time taken for Virtual Threads: {}", timeTaken);
            timeTaken = CommonUtils.timer(()->demo(Thread.ofPlatform()));
            log.info("Time taken for Platform Threads: {}", timeTaken);
        }
    }

    private static void demo(Thread.Builder builder){
        var latch = new CountDownLatch(TASK_COUNT);
        for(int i=1;i<=TASK_COUNT;i++){
            builder.start(()->{
                Task.cpuIntensiveTask(45);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
