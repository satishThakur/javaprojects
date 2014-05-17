package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by satish on 14/05/14.
 */

class Person{
    private final String name;

    public Person(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person - " + name;
    }
}

public class StreamMethods {


    public static void main(String[] args) {

        //remove the collect to see it is really lazy!!!
        //peek would be executed every time the stram produces something. good for debugging
        List<Double> numbers = Stream.iterate(1.0, p -> p * 2).
                peek(System.out::println).limit(20).collect(Collectors.toList());

        //map
        System.out.println("*******Map********");
        List<String> names = Arrays.asList("david", "jenny", "rahim");

        List<Person> persons = names.stream().map(String::toUpperCase).map(Person::new).collect(Collectors.toList());
        persons.forEach(System.out::println);
    }
}
