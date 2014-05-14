package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by satish on 14/05/14.
 */
public class Reductions {

    //does it look handy and cool??

    public static <T> void printOptionalIfExist(Optional<T> option){
        option.ifPresent( System.out::println);
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hi", "Bye", "here", "there", "education", "practise");
        //max
        System.out.println("***** Max *******");
        Optional<String> max = words.stream().max(String::compareToIgnoreCase);
        printOptionalIfExist(max);

        //first
        System.out.println("***** find first except hi *******");
        Optional<String> first = words.stream().filter(s -> !s.startsWith("h")).findFirst();
        printOptionalIfExist(first);

        //findAny why should we use it rather than find first? any idea?
        System.out.println("***** find any except hi *******");
        Optional<String> findAny = words.stream().filter(s -> !s.startsWith("h")).findAny();
        printOptionalIfExist(first);

        //there is a better way to find if any match.
        System.out.println("***** any match except hi ******");
        boolean anyMatch = words.stream().anyMatch(s -> !s.startsWith("h"));
        System.out.println("do we have anything except hi? " + anyMatch);

        //we can even check if all match
        System.out.println("***** all match does not start with h ******");
        boolean allMatch = words.stream().allMatch(s -> !s.startsWith("h"));
        System.out.println("do all starts with char other than h? " + allMatch);
    }
}
