package collectors;

import domain.Person;
import domain.PersonsDb;
import domain.Sex;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by satish on 16/05/14.
 */
public class CollectToMap {

    public static void main(String[] args) {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());

        Map<String,String> lang = locales.collect(Collectors.toMap(l -> l.getDisplayLanguage(),
                l -> l.getDisplayLanguage(l),
                (old, n) -> old));

        System.out.println(lang);

        //but i want all languages of a country
        locales = Stream.of(Locale.getAvailableLocales());

        Map<String, Set<String>> countryLangs = locales.collect(Collectors.toMap(l -> l.getDisplayCountry(),
                l -> Collections.singleton(l.getDisplayLanguage()),
                (old,n) -> {
                    Set<String> languages = new HashSet<String>(old);
                    languages.addAll(n);
                    return languages;
                }));

        System.out.println(countryLangs);
        System.out.println(countryLangs.get(""));
        System.out.println(countryLangs.get("India"));
        System.out.println(countryLangs.get("Belgium"));


        //Simple example we have person and we want to map person object by name
        List<Person> persons = new PersonsDb().persons;

        Map<String, Person> nameToPerson = persons.stream().collect(Collectors.toMap(p -> p.name, Function.identity()));
        System.out.println(nameToPerson);

        //there is a simple way..
        Map<String, List<Person>> nameToPerson1 = persons.stream().collect(Collectors.groupingBy(p -> p.name));
        System.out.println(nameToPerson1);

        //or
        Map<String, Person> nameToPerson2 = persons.stream().collect(Collectors.groupingBy(p -> p.name,
                Collectors.reducing(new Person("XYZ", 99, Sex.MALE), (p1, p2) -> p2)));

        System.out.println(nameToPerson2);

    }
}
