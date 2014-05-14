package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by satish on 15/05/14.
 */
public class Reduce {

    public static void main(String[] args) {

        int sum = Stream.of(1,4,6,7).reduce(0,Integer::sum);
        System.out.println("1 + 4 + 6 + 7 = " + sum);

        List<String> words = Arrays.asList("hi", "Bye", "here", "there", "education", "practise");

        //here the identity type and the stream type are diffrent
        int count = words.stream().reduce(0, (num, word) -> num + word.length(),
                (total1, total2) -> total1 + total2);

        System.out.println("char count = " + count);
    }
}
