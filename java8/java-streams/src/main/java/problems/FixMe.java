package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by satish on 21/05/14.
 */
public class FixMe {

    public void fixMe(){

        Pattern pattern = Pattern.compile("^a.*");
        Stream<String> stream = Stream.of("all", "none", "america", "bye");
        List<String> results = new ArrayList<>();
        stream.filter(s -> pattern.matcher(s).matches())
                .forEach(s -> results.add(s));  // Unnecessary use of side-effects!

        System.out.println(results);

    }

    public void fixed(){
        Pattern pattern = Pattern.compile("^a.*");
        Stream<String> stream = Stream.of("all", "none", "america", "bye");

        List<String> results =
                stream.filter(s -> pattern.matcher(s).matches()).collect(Collectors.toList());

        System.out.println(results);

    }


    public static void main(String[] args) {
        FixMe f = new FixMe();
        f.fixMe();
        f.fixed();

    }
}
