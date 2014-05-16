package optionals;

import domain.Person;
import domain.PersonsDb;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by satish on 15/05/14.
 */
public class OptionalEx {

    List<Person> persons = new PersonsDb().persons;

    public Optional<Person> getPersonByName(String name){
        return persons.stream().filter(p -> p.name.equals(name)).findFirst();
    }

    public static void main(String[] args){
        OptionalEx op = new OptionalEx();

        //Usage pattern 1
        Person ram = op.getPersonByName("Ram").orElseThrow(NoSuchElementException::new);
        System.out.println(ram);

        //Usage pattern 2
        Optional<Person> dummy = op.getPersonByName("Dummy");
        dummy.ifPresent(System.out::println);

        //Usage pattern 3
        //Test how many test cases needed to cover this?
        Optional<Integer> age = op.getPersonByName("Sam").map(p -> p.age);
        age.ifPresent(a -> System.out.println("Sam age is " + a));

    }
}
