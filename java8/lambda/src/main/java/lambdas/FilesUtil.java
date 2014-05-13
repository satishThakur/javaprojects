package lambdas;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by satish on 13/05/14.
 */
public class FilesUtil {

    //Prior to java 8
    public File[] getAllSubDirectories(File parent){
        return parent.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
    }

    public String[] getAllFilesWithExtension(File parent, String ext){
       return parent.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(ext);
            }
        });
    }

    //Java 8
    public File[] getAllSubDir(File parent){
        return parent.listFiles(File::isDirectory);
    }

    //No need of extension to be final but it has to be effective final..
    public String[] getFilesWithExtension(File parent, String extension){
        return parent.list((dir, name) -> name.endsWith(extension));
    }

    public void sortByDirAndThenPath(File[] files){
        Arrays.sort(files, (file1, file2) -> {
                if(file1.isDirectory() && !file2.isDirectory())
                    return -1;
                else if(file2.isDirectory() && !file1.isDirectory())
                    return 1;
                else
                    return file1.toPath().compareTo(file2.toPath());

            });

    }


    public static void main(String[] args) {
        FilesUtil filesUtil = new FilesUtil();
        File[] subDir = filesUtil.getAllSubDir(new File("/var/tmp"));

        for(File file : subDir){
            System.out.println(file.toPath());
        }

        String[] files = filesUtil.getFilesWithExtension(new File("/var/tmp"), "plist");

        for(String file : files){
            System.out.println(file);
        }

        File[] filesToSort = {
                new File("/var/tmp"),
                new File("/Users/satish"),
                new File("/Users/satish/output.txt"),
                new File("/Users/satish/output1.txt")
        };

        filesUtil.sortByDirAndThenPath(filesToSort);

        for(File file : filesToSort){
            System.out.println(file);
        }
    }
}
