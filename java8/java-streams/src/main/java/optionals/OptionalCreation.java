package optionals;

import com.sun.istack.internal.NotNull;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Created by satish on 15/05/14.
 */

class City{

    private String name;

    private int year;

    private String designer;

    public City(String name){
        this.name = name;
    }

    public void setDesigner(String name){
        this.designer = name;
    }

    public Optional<String> getDesigner(){
        return Optional.ofNullable(designer);
    }

    public String getName(){
        return name;
    }

    public OptionalInt getYear(){
        return year == 0 ? OptionalInt.empty() : OptionalInt.of(year);
    }

}

public class OptionalCreation {


    public static void main(String[] args) {
        City city = new City("Bangalore");
        city.setDesigner("Unknown");
        city.getDesigner().ifPresent(System.out::println);

        City anotherCity = new City("Chennai");

        anotherCity.getDesigner().ifPresent(System.out::println);
    }
}
