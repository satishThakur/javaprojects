package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by satish on 14/05/14.
 */
public class StatefulTransformation {

    public static void main(String[] args) {

        //this is stateful transformation as stream need to remember the earlier element to remove the
        //duplicate elements.
        //This is in contrast to methods like filter and map which need no knowledge of prior elements.
        //Now think how this effects parallelism???
        List<String> unique = Stream.of("hey", "there", "hey", "what").distinct().collect(Collectors.toList());
        System.out.println(unique);


        //sort is also a type of stateful transformation
        //but note the difference Collections.sort does in place sorting which is mutation.
        //But streams does not.
        List<String> unsorted = Arrays.asList("some", "random", "strings");
        List<String> sorted = unsorted.stream().
                sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
        System.out.println(unsorted);
        System.out.println(sorted);
    }
}
