package pg.vt.sec03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.util.CommonUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RaceConditionDemo {
    private static final Logger log = LoggerFactory.getLogger(RaceConditionDemo.class);
    private static final List<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        demo(Thread.ofVirtual());
        CommonUtils.sleep(Duration.ofSeconds(2));
        log.info("List size: {}",list.size());
    }

    private static void demo(Thread.Builder builder){
        for (int i = 0; i < 50; i++) {
            builder.start(()->{
                log.info("Task started: {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    store();
                }
                log.info("Task ended: {}",Thread.currentThread());
            });
        }
    }

    private static synchronized void store(){
        list.add(1);
    }
}
