package 八股;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionDemo {
    public static void main(String[] args) throws ParseException {

        int[] arr = {1,2,3};
        try {
            System.out.println(arr[10]);
        } catch (Exception e) {
            throw new RuntimeException(e);
//            System.out.println(e.toString());
//            e.printStackTrace();
        }

        //
    }
}
