package collectors;

import java.util.stream.Collectors;

/**
 * Created by satish on 15/05/14.
 */
public class Test {

    public static void main(String[] args) {
        Collectors.toList().characteristics().forEach(System.out::println);
    }
}
