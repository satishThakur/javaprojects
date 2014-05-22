package generator;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by satish on 22/05/14.
 */

class Integers implements Generator<Integer>{
    Random rand = new Random();
    @Override
    public Integer generate() {
        return rand.nextInt();
    }
}

class Pair<T,U>{
    T t;
    U u;

    public Pair(T t, U u){
        this.t = t;
        this.u = u;
    }

    @Override
    public String toString() {
        return t + "," + u;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Pair))
            return false;
        Pair other = (Pair)obj;
        return Objects.equals(t, other.t) && Objects.equals(u, other.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, u);
    }
}


public class GeneratorImpl {
    public static void main(String[] args) {
        Generator<Integer> integers = new Integers();

        Generator<Boolean> booleans = integers.map(x -> x > 0);

        Generator<Pair<Integer,Integer>> pairGenerator = integers.flatMap(
                i -> integers.map(j -> new Pair(i, j)));

        Stream<Integer> intStream = Stream.generate(integers::generate);

        Stream.generate(pairGenerator::generate).limit(10).forEach(System.out::println);



    }
}
