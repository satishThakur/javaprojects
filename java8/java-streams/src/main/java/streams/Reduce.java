package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by satish on 15/05/14.
 */
public class Reduce {


    //reduce form one. This is same as foldLeft in scala. Nothing for foldRight in java i guess.
    public static void reduce1(){
        List<String> words = Arrays.asList("hello", "there", "how", "are", "you");

        Optional<String> word = words.stream().reduce((s1, s2) -> s1 + s2);

        word.ifPresent(System.out::println);
    }

    //reduce 2, you have a seed to start from.
    public static void reduce2(){
        int sum = Stream.of(1,4,6,7).reduce(0,Integer::sum);
        System.out.println("1 + 4 + 6 + 7 = " + sum);
    }

    //reduce 3, here the reduction type is different, this is most general

    public static void reduce3(){
        List<String> words = Arrays.asList("hi", "Bye", "here", "there", "education", "practise");

        //here the identity type and the stream type are diffrent
        int count = words.stream().reduce(0, (num, word) -> num + word.length(),
                (total1, total2) -> total1 + total2);

        System.out.println("char count = " + count);
    }



    public static void main(String[] args) {
        reduce1();
        reduce2();
        reduce3();

    }
}
