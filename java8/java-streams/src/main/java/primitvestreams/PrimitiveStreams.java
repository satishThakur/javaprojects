package primitvestreams;

import domain.Person;
import domain.PersonsDb;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by satish on 18/05/14.
 */
public class PrimitiveStreams {
    public static void printIntStream(IntStream stream){
        printIntStream(stream, ",");
    }

    public static void printIntStream(IntStream stream, String delim){
        System.out.println(stream.mapToObj(Integer::toString).collect(Collectors.joining(delim)));
    }

    public static void main(String[] args) {

        printIntStream(IntStream.range(1, 10));
        printIntStream(IntStream.rangeClosed(1, 10));

        //Conversion from Object stream to primitive stream
        List<Person> persons = new PersonsDb().persons;
        IntStream ages = persons.stream().mapToInt(p -> p.age);
        printIntStream(ages);

        //we have seen example of mapToOjb but there is another way as well

        Stream<Integer> ints = IntStream.of(2,3,4,5).boxed();

        Random rand = new Random();
        IntStream infiniteInts = rand.ints();

        infiniteInts.limit(50).forEach(System.out::println);


    }
}
