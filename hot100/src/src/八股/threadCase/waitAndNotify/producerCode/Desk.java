package 八股.threadCase.waitAndNotify.producerCode;

public class Desk {
    /*
    * 作用：控制生产者和消费者的执行
    * */

    public static int foodFlag = 0;

    //总个数，吃货总共能吃10碗
    public static int count = 10;

    //锁对象
    public static Object lock = new Object();
}
