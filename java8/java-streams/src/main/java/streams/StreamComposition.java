package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by satish on 25/05/14.
 */
public class StreamComposition {

    public static void main(String[] args) {

        List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six");

        //sub stream
        System.out.println("******** Sub stream limit to first 3 ******* ");
        words.stream().limit(3).forEach(System.out::println);

        System.out.println("******** Sub stream skip the first 3 ******* ");
        words.stream().skip(3).forEach(System.out::println);

        System.out.println("******** peek important method to debug!! ******* ");
        words.stream().peek(System.out::println).filter(s -> s.startsWith("t")).collect(Collectors.toList());

        System.out.println("******** lets concat 2 streams*****");

        Stream<String> repeat = Stream.concat(words.stream(), words.stream());
        repeat.forEach(System.out::println);







    }
}
