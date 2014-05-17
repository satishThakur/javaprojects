package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by satish on 17/05/14.
 */
public class ReduceArrayList<T> {

    public List<T> reduce(Stream<List<T>> arraysStream){
        return arraysStream.reduce(
                (l1,l2) -> {
                    List<T> merge = new ArrayList<T>(l1);
                    merge.addAll(l2);
                    return merge;
                }).orElse(Collections.emptyList());
    }

    public List<T> reduce1(Stream<List<T>> arraysStream){
        return arraysStream.reduce(new ArrayList<T>(),
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
    }

    public List<T> reduce2(Stream<List<T>> arrayStream){
        return arrayStream.reduce(new ArrayList<T>(),
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                },
        (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        });
    }


    public static void main(String[] args) {
        Stream<List<String>> s = Stream.of(
                Arrays.asList("hi", "bye"),
                Arrays.asList("yes", "no"),
                Arrays.asList("true", "false"));

        ReduceArrayList<String> reducer = new ReduceArrayList<>();
        reducer.reduce2(s).forEach(System.out::println);
    }


}
