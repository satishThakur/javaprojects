package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by satish on 14/05/14.
 */
public class FlatmapEx {

    public static Stream<Character> getAllchars(String word){
        List<Character> chars = new ArrayList<>();
        for(Character c : word.toCharArray()){
            chars.add(c);
        }

        return chars.stream();
    }

    public void foo(int num){
        IntStream.range(0,num).flatMap( x -> {
            return IntStream.range(0,x).map(y -> {
                System.out.println( x + "," + y);
                return 0;
            });
        }).forEach((x) -> {});
    }




    public void getAllCharsInWords(List<String> words){

        Stream<Character> chars = words.stream().flatMap(FlatmapEx::getAllchars);
    }

    public static void main(String[] args) {
        FlatmapEx ex = new FlatmapEx();
        ex.foo(7);
    }
}
