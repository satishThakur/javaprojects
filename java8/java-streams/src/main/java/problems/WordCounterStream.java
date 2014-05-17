package problems;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by satish on 17/05/14.
 */
public class WordCounterStream {

    public long getLongWordsInFile(Path path) throws Exception{
        return countWords(getWordsOfFile(path));
    }

    public long getLongWordsInFileParallel(Path path) throws Exception{
        return countWordsParallel(getWordsOfFile(path));
    }



    public List<String> getWordsOfFile(Path file) throws Exception {
        List<String> words = new ArrayList<>();
        String content = new String(Files.readAllBytes(file));
        for(int i = 0; i < 50; i ++)
            words.addAll(Arrays.asList(content.split("[\\P{L}]")));
        return words;

    }

    public long countWords(List<String> words) throws Exception {
        return words.stream().filter(word -> word.length() > 12).count();
    }

    public long countWordsParallel(List<String> words) throws Exception {
        return words.parallelStream().filter(word -> word.length() > 12).count();
    }

    //Prove that the filter is lazy...
    public List<String> getnLongWords(List<String> words, int n){
        return words.stream().filter(word -> {
            boolean isBig = word.length() > 12;
            if(isBig)
                System.out.println("found..");
            return isBig;
        }).limit(n).collect(Collectors.toList());
    }

    public static void main(String[] args) throws Exception{
       WordCounterStream counter = new WordCounterStream();
/**
        List<String> words = counter.getWordsOfFile(Paths.get("/Users/satish/books/war-and-peace.txt"));
        System.out.println(counter.countWords(words));

        System.out.println(counter.getnLongWords(words, 5));*/

        long t2 = System.currentTimeMillis();

        System.out.println(counter.getLongWordsInFileParallel(Paths.get("/Users/satish/books/war-and-peace.txt")));

        System.out.println("Parallel. operation took: " + (System.currentTimeMillis() - t2) + " ms");

        long t1 = System.currentTimeMillis();

        System.out.println(counter.getLongWordsInFile(Paths.get("/Users/satish/books/war-and-peace.txt")));

        System.out.println("Seq. operation took: " + (System.currentTimeMillis() - t1) + " ms");




    }

}
