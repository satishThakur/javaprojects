package lambdas.methodref;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by satish on 10/05/14.
 */

class Apple{

    private final int weight;
    private final String color;

    public Apple(int weight){
        this(weight, "red");
    }

    public Apple(int weight, String color){
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple weight: " + weight + " , Color: " + color;
    }
}

public class ConstructorRef {



    public static void main(String[] args) {
        Function<Integer, Apple> appleFactory = Apple::new;
        BiFunction<Integer,String, Apple> appleFactory1 = Apple::new;

        System.out.println(appleFactory.apply(2));
        System.out.println(appleFactory1.apply(2, "green"));


    }
}
