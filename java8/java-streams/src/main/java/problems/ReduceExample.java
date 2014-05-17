package problems;

import java.util.stream.Stream;

/**
 * Created by satish on 17/05/14.
 */
public class ReduceExample {

    public Double getAverage(Stream<Double> ds){
        return ds.reduce(0.0, (d1, d2) -> d1 + d2/2.0);
    }


    public static void main(String[] args) {
        Stream<Double> doubles = Stream.of(2.0, 4.0, 3.0, 3.0, 11.0);

        ReduceExample reducer = new ReduceExample();

        System.out.println(reducer.getAverage(doubles));

        Stream<Double> doubles1 = Stream.of(5.0, 10.0, 6.0);
        System.out.println(reducer.getAverage(doubles1));
    }
}
