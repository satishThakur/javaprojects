package collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by satish on 24/05/14.
 */
public class MyJoiningCollector {


    public static Collector<CharSequence,?,String> joining(){

        return Collector.of(StringBuilder::new, StringBuilder::append, (sb1, sb2) -> {
            sb1.append(sb2);
            return sb1;
        }, StringBuilder::toString);
    }

    public static Collector<CharSequence, ?, String> joiningWithDelimiter(String delimiter){
        return Collector.of(StringBuilder::new, (sb, s) -> {
            sb.append(s);
            sb.append(delimiter);
        }, (sb1, sb2) -> {
            sb1.append(sb2);
            return sb1;
        }, StringBuilder::toString);
    }


    public static void main(String[] args) {
        List<String> words = Arrays.asList("function", "method", "void");

        System.out.println(words.stream().collect(Collectors.joining()));
        System.out.println(words.stream().collect(joining()));

        System.out.println(words.stream().collect(joiningWithDelimiter(" | ")));
        System.out.println(words.stream().collect(Collectors.joining(" | ")));

        System.out.println(words.stream().collect(Collectors.joining(" | ","=begin: ", " :=end")));

    }
}
