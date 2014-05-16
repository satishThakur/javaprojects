package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by satish on 15/05/14.
 */
public class PersonsDb {

    public final List<Person> persons = new ArrayList<Person>(Arrays.asList(
            new Person("Billy", 10, Sex.MALE),
            new Person("Sam", 20, Sex.MALE),
            new Person("Sonia", 34, Sex.FEMALE),
            new Person("Peter", 30, Sex.MALE),
            new Person("Raj", 87, Sex.MALE),
            new Person("Rose", 17, Sex.FEMALE),
            new Person("satish", 31, Sex.MALE),
            new Person("Ram", 65, Sex.MALE)));

}
