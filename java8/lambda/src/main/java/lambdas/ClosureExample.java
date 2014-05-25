package lambdas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by satish on 13/05/14.
 */
public class ClosureExample {

    public static void main(String[] args) {
        String[] names = { "Pete", "Paul", "Mary" };
        List< Runnable > runners = new ArrayList<>();
        for (String name : names)
            runners.add(() -> System.out.println(name));

        runners.forEach(r -> r.run());


        List<Runnable> runners1 = new ArrayList<>();

        //This will not work as i is not effective final!!
        for(int i = 0; i < names.length; i ++){
            //runners1.add(() -> System.out.println(names[i]));
        }



    }
}
