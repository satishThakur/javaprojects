package optionals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by satish on 15/05/14.
 */
public class OptionalFlatMap {

    private List<City> citiesDb = Stream.of("Bangalore", "Chennai", "Delhi", "Calcutta").
            map(City::new).collect(Collectors.toList());

    public Optional<City> getCity(String name){
           return citiesDb.stream().filter(c -> c.getName().equals(name)).findFirst();

    }

    public static void main(String[] args) {

        OptionalFlatMap ex = new OptionalFlatMap();
        Optional<City> city = ex.getCity("Bangalore");
        Optional<String> designer = city.flatMap(c -> c.getDesigner());
        Optional<String> cityName = city.map(City::getName);


        //better way
        String chennaiDesigner = ex.getCity("Chennai").flatMap(City::getDesigner).orElse("No One");

        System.out.println(chennaiDesigner);



    }
}
