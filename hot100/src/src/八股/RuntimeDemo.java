package 八股;

public class RuntimeDemo {
    public static void main(String[] args) {
/*
        Runtime是一个单例
        Runtime的构造函数是private的，他自己创造了一个对象
        runtime表示当前虚拟机的运行环境
        可以获得cpu的线程数
        内存的大小，包括总大小，剩余大小。
*/

        Runtime r = Runtime.getRuntime();
    }
}
