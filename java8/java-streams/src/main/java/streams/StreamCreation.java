package streams;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by satish on 14/05/14.
 */
public class StreamCreation {

    public static void main(String[] args) {

        String text = "line of some random text";
        Stream<String> stream = Stream.of(text.split(" "));
        Stream<String> another = Stream.of("hey", "there", "stream");

        //Infinite streams
        Stream<String> echos = Stream.generate(() -> "Echo");
        Stream<Double> nums = Stream.generate(Math::random);
        Stream<BigInteger> ints = Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE));

        System.out.println("Print 10 random doubles!!");
        nums.limit(10).forEach(System.out::println);

        System.out.println("First 20 bigints...");
        ints.limit(20).forEach(System.out::println);
        
        int[] numbers = {1,2,3,4,5};

        Stream<int[]> arrStream = Stream.of(numbers);

        IntStream intStream = Arrays.stream(numbers);


    }
}
