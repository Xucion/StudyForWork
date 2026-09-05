package 八股.threadCase.threadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread{
    static int ticket = 0; //0-99
    static Lock lock = new ReentrantLock();

    //锁对象，一定要是唯一的
    static Object obj = new Object();

    @Override
    public void run() {
        while (true){
            //同步代码块
            //synchronized (obj){
            lock.lock();
            try {
                if(ticket<100){
                    Thread.sleep(10);
                    ticket ++;
                    System.out.println(getName() + "正在卖第" + ticket + "张票");
                }else {
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

            //}
        }
    }
}
