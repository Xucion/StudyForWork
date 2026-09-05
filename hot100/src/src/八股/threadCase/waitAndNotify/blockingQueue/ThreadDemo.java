package 八股.threadCase.waitAndNotify.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

public class ThreadDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        Cook c = new Cook(queue);
        Foodie f = new Foodie(queue);
        c.start();
        f.start();
    }
}
