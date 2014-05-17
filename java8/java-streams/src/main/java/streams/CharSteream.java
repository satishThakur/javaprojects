package streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by satish on 17/05/14.
 */
public class CharSteream {

    public Stream<Character> getCharStream(String string){
         return IntStream.range(0,string.length()).mapToObj(string::charAt);
    }


    public static void main(String[] args) {
        CharSteream cs = new CharSteream();

        cs.getCharStream("HelloWorld").forEach(System.out::println);
    }
}
