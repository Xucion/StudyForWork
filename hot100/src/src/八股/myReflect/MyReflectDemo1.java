package 八股.myReflect;

public class MyReflectDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("八股.myReflect.Student");
        System.out.println(clazz);

        Class<Student> clazz2 = Student.class;
        System.out.println(clazz == clazz2);

        Student s = new Student();
        Class<? extends Student> clazz3 = s.getClass();
        System.out.println(clazz == clazz2);
        System.out.println(clazz3 == clazz2);

    }
}
