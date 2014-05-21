package progs;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by satish on 21/05/14.
 */

class FilesWithSuffixVisitor extends SimpleFileVisitor<Path>{
    List<Path> files = new ArrayList<>();
    private String suffix;

    FilesWithSuffixVisitor(String suffix){
        this.suffix = suffix;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.toFile().getName();
        if(fileName.endsWith("java")) {
            files.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
public class CountFiles {

    public List<Path> getFiles(Path path, String suffix) throws IOException {
        FilesWithSuffixVisitor visitor = new FilesWithSuffixVisitor(suffix);
        Files.walkFileTree(path, visitor );

        return visitor.files;

    }

    public static void main(String[] args) throws IOException {
        CountFiles counter = new CountFiles();
        List<Path> javaFiles = counter.getFiles(Paths.get("/Users/satish/java/javaprojects/java8"), "java");
        System.out.println(javaFiles);
        System.out.println("Total java files: " + javaFiles.size());
    }
}
