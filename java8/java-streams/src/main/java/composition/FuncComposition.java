package composition;

import java.util.function.Function;

/**
 * Created by satish on 18/05/14.
 */




public class FuncComposition {

    public static void main(String[] args) {
        Function<String,String> upperCase = String::toUpperCase;
        Function<String,String> firstTwoChars = s -> s.substring(0,2);

        String target = "small";

        System.out.println(upperCase.andThen(firstTwoChars).apply(target));
    }

}
