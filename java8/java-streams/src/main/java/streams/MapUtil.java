package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by satish on 19/05/14.
 */

class Fruit{
    private String name;

    public Fruit(String name){
        this.name = name;
    }

    public String getDesc(){
        return "Fruit name is " + name;
    }

    @Override
    public String toString() {
        return "Fruit: " + name;
    }
}

class Apple extends Fruit{

    private String color;
    public Apple(String color){
        super("Apple");
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple: " + color;
    }
}

class Pair<T>{
    private final T first;
    private final T second;

    public Pair(T first, T second){
        this.first = first;
        this.second = second;
    }

    public T getFirst(){
        return first;
    }

    public T getSecond(){
        return second;
    }

    @Override
    public String toString() {
        return first + " , " + second;
    }
}


public class MapUtil {

    public static String getDesc(Fruit f){
        return f.getDesc();
    }

    public static <T,U> Future<U> map(Future<T> future, Function<T,U> mapper){

        return new Future<U>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return mapper.apply(future.get());
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }

    public static <T,U> List<U> map(List<T> list, Function<T,U> mapper){
        List<U> mappedList = new ArrayList<>();
        list.forEach(elem -> mappedList.add(mapper.apply(elem)));
        return mappedList;
    }

    public static <T,U> Pair<U> map(Pair<T> pair, Function<T,U>  mapper){
        return new Pair<>(mapper.apply(pair.getFirst()), mapper.apply(pair.getSecond()));
    }




    public static void main(String[] args) {
        List<Apple> apples = Stream.of("red", "green", "yellow").map(Apple::new).collect(Collectors.toList());

        map(apples, Apple::toString).forEach(System.out::println);

        map(apples, MapUtil::getDesc).forEach(System.out::println);
    }
}
