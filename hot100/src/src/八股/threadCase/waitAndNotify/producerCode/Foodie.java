package 八股.threadCase.waitAndNotify.producerCode;

public class Foodie extends Thread{
    @Override
    public void run() {
        /*
         * 1循环
         * 2synchronized同步代码块
         * 3判断共享数据是否到了末尾，到了末尾
         * 4没有到末尾
         * */
        while(true){
            synchronized (Desk.lock){
                if(Desk.count == 0){
                    break;
                }else {
                    //先判断桌子上是否有面条
                    if(Desk.foodFlag == 0){
                        //没有等待
                        try {
                            Desk.lock.wait();//让当先线程跟锁进行绑定
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }else {
                        //把吃的总数-1
                        Desk.count--;
                        //有就开吃
                        System.out.println("吃面，还能吃" + Desk.count + "碗");
                        //修改桌子状态
                        Desk.foodFlag = 0;
                        //吃完后，唤醒厨师继续做
                        Desk.lock.notifyAll();//唤醒跟这把锁绑定的线程
                    }

                }
            }
        }
    }
}
