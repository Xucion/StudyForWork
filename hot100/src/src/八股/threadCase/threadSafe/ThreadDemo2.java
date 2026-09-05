package 八股.threadCase.threadSafe;

public class ThreadDemo2 {
    public static void main(String[] args) {
        MyRunnable2 mr =new MyRunnable2();

        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);
        Thread t3 = new Thread(mr);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
