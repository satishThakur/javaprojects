package problems;

/**
 * Created by satish on 17/05/14.
 */
public class Test {


    public static void main(String[] args) {
        int i  = 0;

        for(int index = 0; index  < 100; index++){
            System.out.println(i);
            i = i + 1 % 4;
        }
    }
}
