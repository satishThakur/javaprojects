package collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by satish on 22/05/14.
 */

class City{
    private String name;

    City(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "City: " + name;
    }
}


public class OddCollect {
    public static void main(String[] args) {

        List<String> cityNames = Arrays.asList("Bangalore", "chennai", "delhi", "calcutta");

        List<City> cities = cityNames.parallelStream().map(City::new).
                collect(() -> new ArrayList<>(),
                        (al, element) -> al.add(element),
                        (a1,a2) -> a1.addAll(a2));

        cities.forEach(System.out::println);

    }


}
