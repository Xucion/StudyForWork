package 八股.threadCase.threadMethod1;

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        System.out.println(t1.currentThread());
        t1.start();
        t2.start();
    }
}
