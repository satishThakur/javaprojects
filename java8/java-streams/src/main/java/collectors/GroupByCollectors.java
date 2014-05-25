package collectors;

import domain.Person;
import domain.PersonsDb;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Created by satish on 24/05/14.
 */
public class GroupByCollectors {

    public static <T,U> void pairPrinter(T t, U u){
        System.out.println(t + " : " + u);
    }

    public static void main(String[] args) {

        List<Person> persons = new PersonsDb().persons;

        Map<Integer, List<Person>> ageToPersons = persons.stream().collect(Collectors.groupingBy(p -> p.age));
        ageToPersons.forEach(GroupByCollectors::pairPrinter);

        System.out.println("***** Parallel grouping******");
        persons.parallelStream().collect(Collectors.groupingByConcurrent(p -> p.age)).forEach(GroupByCollectors::pairPrinter);
        
        
        //but i just want person names and that too in a set!!
        System.out.println("******** just names ************");
        Map<Integer, Set<String>> ageToNames = persons.stream().collect(
                Collectors.groupingBy(p -> p.age, Collectors.mapping(p -> p.name, Collectors.toSet())));

        ageToNames.forEach(GroupByCollectors::pairPrinter);


        //sorry but i wanted age in order as well!!
        System.out.println("******** just names but age in order ************");
        persons.stream().collect(
                Collectors.groupingBy(p -> p.age, TreeMap::new,
                        Collectors.mapping(p -> p.name, Collectors.toSet()))).forEach(GroupByCollectors::pairPrinter);

        //but wait i wanted names in order as well!!
        System.out.println("******** just names but age in order and names as well ************");
        persons.stream().collect(
                Collectors.groupingBy(p -> p.age, TreeMap::new,
                        Collectors.mapping(p -> p.name, Collectors.toCollection(TreeSet::new)))).forEach(GroupByCollectors::pairPrinter);

    }
}
