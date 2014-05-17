package problems;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by satish on 17/05/14.
 */

class WordCounterWorker implements Runnable{

    List<String> words;

    private static AtomicInteger counter = new AtomicInteger();

    private int count;

    private int id;

    public WordCounterWorker(List<String> words){
        this.words = words;
        id = counter.incrementAndGet();
    }

    public void addWord(String word){
        words.add(word);
    }

    @Override
    public void run() {
        long t = System.currentTimeMillis();
        System.out.println("Processing " + words.size() + " items!!");
        for(String word: words){
            if(word.length() > 12){
                count++;
            }
        }
        System.out.println("Worker " + id + " Took " + (System.currentTimeMillis() - t) + " ms");

    }

    public int getCount(){
        return count;
    }
}


public class WordCounter {

    public int getWordCount(Path file) throws Exception{
        String content = new String(Files.readAllBytes(file));
        List<String> words = Arrays.asList(content.split("[\\P{L}]"));

        return countWords(words);

    }


    public int countWords(List<String> words) throws Exception{
        int counter = 0;

        int processors = Runtime.getRuntime().availableProcessors();

        ExecutorService ex = Executors.newFixedThreadPool(processors);

        List<WordCounterWorker> workers = getWorkers(words, processors);

        for(WordCounterWorker worker : workers){
            ex.submit(worker);
        }

        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.MINUTES);

        int count = 0;

        for(WordCounterWorker worker : workers){
            count += worker.getCount();
        }

        return count;


    }

    private List<WordCounterWorker> getWorkers(List<String> words, int processors) {
        List<WordCounterWorker> workers = new ArrayList<>();
        for(int i = 0; i < processors; i++){
            workers.add(new WordCounterWorker(new ArrayList<String>()));
        }
        int i = 0;
        for(String word : words){
            workers.get(i).addWord(word);
            i = (i+1) % processors;
        }

        return workers;


    }


    public static void main(String[] args) throws Exception {
        List<String> words = Arrays.asList(
                "hello",
                "words",
                "education",
                "something",
                "whatever",
                "small",
                "how",
                "when",
                "there",
                "here",
                "hey",
                "projects"
        );

        long t = System.currentTimeMillis();
        WordCounter counter = new WordCounter();

        //System.out.println(new WordCounter().countWords(words));

        System.out.println(counter.getWordCount(Paths.get("/Users/satish/books/war-and-peace.txt")));
        System.out.println("Took " + (System.currentTimeMillis() - t) + " ms");
    }
}
