package streams;

import java.math.BigInteger;
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


    }
}
