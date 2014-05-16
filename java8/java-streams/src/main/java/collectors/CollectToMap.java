package collectors;

import java.util.*;
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
    }
}
