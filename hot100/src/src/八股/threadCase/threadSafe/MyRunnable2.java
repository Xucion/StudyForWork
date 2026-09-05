package 八股.threadCase.threadSafe;

public class MyRunnable2 implements Runnable{
    int ticket = 0;

    @Override
    public void run() {
        //1循环
        //2同步代码块
        //3判断共享数据是否到了末尾，如果到了末尾
        //4判断共享数据是否到了末尾，如果没有到末尾

        while(true){
                if (method()) break;
        }

    }

    //锁对象是this
    private synchronized boolean method() {
        if(ticket == 100){
            return true;
        }else{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ticket++;
            System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket + "张票");
        }
        return false;
    }
}
