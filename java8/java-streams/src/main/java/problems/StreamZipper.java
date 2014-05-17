package problems;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by satish on 17/05/14.
 */
public class StreamZipper {

    public <T> Stream<T> zip(Stream<T> s1, Stream<T> s2){

        Iterator<T> zipIterator = new Iterator<T>() {

            boolean isSecond;
            Iterator<T> first = s1.iterator();
            Iterator<T> second = s2.iterator();

            @Override
            public boolean hasNext() {
                if(isSecond)
                    return second.hasNext();
                else
                    return first.hasNext() && second.hasNext();
            }

            @Override
            public T next() {
                if(isSecond){
                    isSecond = false;
                    return second.next();
                }else{
                    isSecond = true;
                    return first.next();
                }
            }
        };

        int characteristics = Spliterator.ORDERED | Spliterator.IMMUTABLE;

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(zipIterator,characteristics),false);
    }

    public static void main(String[] args) {
        Stream<String> s1 = Stream.of("One", "three", "five");
        Stream<String> s2 = Stream.of("Two", "four", "six", "eight");

        StreamZipper zipper = new StreamZipper();
        zipper.zip(s1,s2).forEach(System.out::println);

    }
}
