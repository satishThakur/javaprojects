package problems;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by satish on 19/05/14.
 */

class Triplet<T>{
    private T t;
    private T u;
    private T r;

    public Triplet(T t, T u, T r){
        this.t = t;
        this.u = u;
        this.r = r;
    }

    @Override
    public String toString() {
        return t.toString() + "," + u.toString() + "," + r.toString();
    }
}



public class PythagoreanTriangles {

    public void pythagoreanTriangles(Integer num){
        Stream<Integer> numbers = IntStream.rangeClosed(1,num).boxed();

        Stream<Triplet<Integer>> triangles = numbers.flatMap(x -> {
            return IntStream.rangeClosed(1, x).boxed().flatMap(y -> {
                return IntStream.rangeClosed(1, y).boxed().filter(z -> {
                    return x * x == y * y + z * z;
                }).map(z1 -> new Triplet<Integer>(x, y, z1));
            });
        });

        triangles.forEach(System.out::println);

    }
/*
    public List<Triplet<Integer>> gerFirstTriangles(long num){
        Stream<Integer> infinity = Stream.iterate(1, (n) -> n + 1);

        return infinity.flatMap(x -> {
            return IntStream.rangeClosed(1, x).boxed().flatMap(y -> {
                return IntStream.rangeClosed(1, y).boxed().filter(z -> {
                    return x * x == y * y + z * z;
                }).map(z1 -> new Triplet<Integer>(x, y, z1));
            });
        }).limit(num).collect(Collectors.toList());


    }
    */


    public static void main(String[] args) {
        PythagoreanTriangles triangles = new PythagoreanTriangles();
        triangles.pythagoreanTriangles(10);
        //triangles.gerFirstTriangles(5l).forEach(System.out::println);
    }
}
