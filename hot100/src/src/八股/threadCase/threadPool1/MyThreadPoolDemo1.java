 package 八股.threadCase.threadPool1;

import java.util.concurrent.*;

 public class MyThreadPoolDemo1 {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,
                6,
                60,
                TimeUnit.SECONDS,
                //new LinkedBlockingDeque<>()
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
                );
    }
}
