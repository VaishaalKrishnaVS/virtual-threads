package pg.vt.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.util.CommonUtils;

public class Task {

    public static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void cpuIntensiveTask(int i){
        log.info("Starting task. Thread info: {}", Thread.currentThread());
        var timeTaken = CommonUtils.timer(()->getFibonacci(i));
        log.info("Task ended. Time taken: {}ms", timeTaken);
    }

    private static long getFibonacci(long i){
        if( i<2) return i;
        return getFibonacci(i-1)+ getFibonacci(i-2);
    }
}
