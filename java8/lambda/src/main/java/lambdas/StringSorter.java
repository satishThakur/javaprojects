package lambdas;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by satish on 13/05/14.
 */
public class StringSorter {

    //Prior to java8
    public void sortBasedOnSize(List<String> strings){

        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

    }

    public void sortIgnoreCase(List<String> strings){
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
    }

    //with java 8


    //Example of typical Lambda how it looks
    public void sortBasedOnSizeNew(List<String> strings) {
        strings.sort((s1,s2) -> Integer.compare(s1.length(), s2.length()));
    }

    //lambda which extends to multiple lines it does not remain expression anymore.
    //It is a statement and as usual you need to specify return value

    public void sortBasedOnSize1(List<String> strings){
        strings.sort((s1, s2) -> {
            int s1Length = s1.length();
            int s2Length = s2.length();
            return Integer.compare(s1Length,s2Length);
        });
    }

    //Method reference
    public void sortIgnoreCaseNew(List<String> strings){
        strings.sort(String::compareToIgnoreCase);
    }

    //Rich interfaces with default methods
    public void sortIgnoreCaseDesc(List<String> strings){
        Comparator<String> ignoreCase = String::compareToIgnoreCase;
        strings.sort(ignoreCase.reversed());
    }

}
