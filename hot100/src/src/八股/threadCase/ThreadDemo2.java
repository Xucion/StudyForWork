package 八股.threadCase;

public class ThreadDemo2 {
    public static void main(String[] args) {
        /*
         * 1多线程的第二种启动方式：自己实现Runnable接口
         * 2重写里面的run方法
         * 3创建自己的类的对象
         * 4创建一个thread类的对象，并开启线程
         * */
        //创建MyRun的对象
        //表示多线程要执行的任务
        MyRun2 mr = new MyRun2();

        //创建Thread对象
        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        //给线程设置名字
        t1.setName("线程1");
        t2.setName("线程2");

        //开启线程
        t1.start();
        t2.start();
    }
}
