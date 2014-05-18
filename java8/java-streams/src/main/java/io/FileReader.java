package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by satish on 17/05/14.
 */
public class FileReader {

    public void printAllLines(Path path) throws IOException {
        try(Stream<String> lines = Files.lines(path)){
            lines.forEach(System.out::println);
        }
    }

    public void processAllLines(Path path, Predicate<String> filter,Consumer<String> lineConsumer) throws IOException {
        try(Stream<String> lines = Files.lines(path)){
            lines.filter(filter).forEach(lineConsumer);
        }
    }


    public static void main(String[] args) throws IOException {
        new FileReader().printAllLines(Paths.get("/var/log/authd.log"));
    }
}
