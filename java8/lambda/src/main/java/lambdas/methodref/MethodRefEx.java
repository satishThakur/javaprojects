package lambdas.methodref;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by satish on 13/05/14.
 */

//Examples Class::static method
class Calculator{

    private int doBinaryOp(int a, int b, IntBinaryOperator op){
        return op.applyAsInt(a,b);
    }

    public int sum(int a, int b){
        //return doBinaryOp(a,b, (x,y) -> x + y);
        return doBinaryOp(a,b, Integer::sum);
    }

    public int multiply(int a, int b){
        return doBinaryOp(a,b, Math::multiplyExact);
    }

    private IntUnaryOperator multiple(int m){
        return a -> a * m;
    }

    public int doubleNum(int num){
        return multiple(2).applyAsInt(num);
    }

}




public class MethodRefEx {



    public static void main(String[] args) {

        //Example object::instance
        List<String> strings = Arrays.asList("movie", "song", "text", "video");

        strings.forEach(s -> System.out.println(s));

        strings.forEach(System.out::println);

        //Example Class::instance method
        strings.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        strings.sort(String::compareToIgnoreCase);


        //Example constructor reference

        List<Thread> threads = strings.stream().map(Thread::new).collect(Collectors.toList());

        threads.forEach(System.out::println);




    }


}
