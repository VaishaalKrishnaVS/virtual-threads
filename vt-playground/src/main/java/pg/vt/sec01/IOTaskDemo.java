package pg.vt.sec01;

import java.util.concurrent.CountDownLatch;

public class IOTaskDemo {
    public static final int MAX_ITR = 10;
    public static void main(String[] args) throws InterruptedException {
        platformDaemonThreadDemoUsingBuilder();
    }
    private static void platformThreadDemo() {
        for (int i = 0; i < MAX_ITR; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.someIntensiveIOTask(j));
            thread.start();
        }
    }
    private static void platformThreadDemoUsingBuilder(){
        var builder = Thread.ofPlatform().name("vt-platform");
        for (int i = 0; i < MAX_ITR; i++) {
            int j = i;
            Thread thread = builder.unstarted(()->Task.someIntensiveIOTask(j));
            thread.start();
        }
    }
    private static void platformDaemonThreadDemoUsingBuilder() throws InterruptedException {
        var builder = Thread.ofPlatform().daemon().name("vtd-platform");
        var latch = new CountDownLatch(MAX_ITR);
        for (int i = 0; i < MAX_ITR; i++) {
            int j = i;
            Thread thread = builder.unstarted(()-> {
                Task.someIntensiveIOTask(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }
}
