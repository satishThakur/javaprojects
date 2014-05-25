package collectors;

import domain.Person;
import domain.PersonsDb;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by satish on 15/05/14.
 */
public class CollectorsExample {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello", "there", "hello", "bye");

        Set<String> uniqueWords1 = words.stream().collect(() -> new HashSet<>(),
                (set,elem) -> set.add(elem),(s1, s2) -> s1.addAll(s2) );
        System.out.println(uniqueWords1);

        Set<String> uniqueWords = words.stream().collect(HashSet::new, HashSet::add, HashSet::addAll);

        System.out.println(uniqueWords);

        //but there is nicer alternative

        System.out.println(words.stream().collect(Collectors.toSet()));

        //but i want tree set not hashSet

        System.out.println(words.stream().collect(Collectors.toCollection(TreeSet::new)));

        //well i just want to join all strings
        String sentence = words.stream().collect(Collectors.joining());
        System.out.println(sentence);

        //but my sentence need space between words!!!
        System.out.println(words.stream().collect(Collectors.joining(" ")));

        List<Person> persons = new PersonsDb().persons;
        String printablePersons = persons.stream().map(Person::toString).collect(Collectors.joining(" | "));
        System.out.println(printablePersons);

        IntSummaryStatistics ages = persons.stream().collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ages.getSum());

        //CollectorsAndThen

        List<String> immutableNames = persons.stream().map(p -> p.name).
                collect(Collectors.collectingAndThen(
                        Collectors.toList(), Collections::unmodifiableList));

        //immutableNames.add("new guy");


    }
}
