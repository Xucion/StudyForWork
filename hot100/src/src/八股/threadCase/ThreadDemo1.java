package 八股.threadCase;

public class ThreadDemo1 {
    public static void main(String[] args) {
        /*
        * 1多线程的第一种启动方式自定定义一个类，继承Thread
        * 2重写run方法
        * 3创建子类的对象，并启动线程
        * */
        MyThread1 t1 = new MyThread1();
        MyThread1 t2 = new MyThread1();
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
