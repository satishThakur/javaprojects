package streams;

import java.util.stream.Stream;

/**
 * Created by satish on 17/05/14.
 */
public class RamdonNumberGen {

    //Xn+1 = (aXn + c) %m
    Stream<Long> getLongGenerator(Long seed, Long a, Long c, Long m){

        return Stream.iterate(seed, xn -> (a * xn + c) % m);

    }


    public static void main(String[] args) {
        Stream<Long> longStream = new RamdonNumberGen().getLongGenerator(0L, 25214903917L,11L, 248L);

        longStream.limit(40).forEach(System.out::println);
    }
}
