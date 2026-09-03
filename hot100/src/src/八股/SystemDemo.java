package 八股;

public class SystemDemo {
    public static void main(String[] args) {
        System.exit(0);
        System.out.println(System.currentTimeMillis());
        System.out.println("Hello, World!");
        System.out.println(System.currentTimeMillis());
        System.arraycopy(new int[]{1, 2, 3}, 0, new int[3], 0, 3);
    }
}
