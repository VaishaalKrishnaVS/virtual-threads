package pg.vt.sec01;

public class IOTaskDemo {
    public static final int MAX_ITR = 50_000_000;
    public static void main(String[] args) {
        platformThreadDemo();
    }
    private static void platformThreadDemo() {
        for (int i = 0; i < MAX_ITR; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.someIntensiveIOTask(j));
            thread.start();
        }
    }
}
