package composition;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by satish on 25/05/14.
 */
public class ComposeLimitation {

    //fixes...
    public static <T> UnaryOperator<T> convert(Function<T,T> func){
        return t -> func.apply(t);
    }

    //fix 2

    public static <T> UnaryOperator<T> andThen(UnaryOperator<T> first, UnaryOperator<T> second){
        return t -> second.apply(first.apply(t));
    }



    public static void main(String[] args) {
        UnaryOperator<String> toUpper = String::toUpperCase;
        UnaryOperator<String> firstTwoLetters = s -> s.substring(0,2);

        Function<String,String> toUpperAndFirstTwoLetters = toUpper.andThen(firstTwoLetters);
    }
}
