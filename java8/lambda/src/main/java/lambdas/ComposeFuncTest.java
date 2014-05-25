package lambdas;

import java.util.function.Function;

/**
 * Created by satish on 10/05/14.
 */

// in mathematics its f(g(x))
public class ComposeFuncTest {

    public static void main(String[] args) {
        Function<Integer, Integer> addFive = i -> i + 5;
        Function<Integer, String> intStr = i -> "Integer: "  + i ;

        Function<Integer,String> addFiveAndStr = addFive.andThen(intStr);
        System.out.println(addFiveAndStr.apply(34));
    }
}
