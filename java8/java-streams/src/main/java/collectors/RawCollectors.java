package collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * Created by satish on 22/05/14.
 */
public class RawCollectors {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("john", "david", "ram", "sham");

        Collector<String, ArrayList<String>, ArrayList<String>> c = Collector.of(ArrayList::new, ArrayList::add, (a1,a2) ->{
            a1.addAll(a2);
            return a1;
        });

        names.stream().collect(c).forEach(System.out::println);

        Collector<String, StringBuilder, String> c1 = Collector.of(StringBuilder::new,
                StringBuilder::append, (sb1, sb2) ->{
                    sb1.append(sb2);
                    return sb1;
                }, StringBuilder::toString);

        System.out.println(names.stream().collect(c1));
    }
}
