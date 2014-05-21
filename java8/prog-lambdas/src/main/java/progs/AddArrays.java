package progs;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by satish on 20/05/14.
 */
public class AddArrays {

    public static <T> String getPrintableArray(T[] arr){
        return Arrays.stream(arr).map(Object::toString).collect(Collectors.joining(","));
    }

    public static  String getPrintableIntArray(int[] arr){
        return Arrays.stream(arr).boxed().map(Object::toString).collect(Collectors.joining(","));
    }

    public static int[] addArrays(int[] a1, int[] a2){

        return IntStream.range(0, Math.min(a1.length, a2.length)).
                map(index -> a1[index] + a2[index]).toArray();
    }


    public static void main(String[] args) {
        int[] a1 = {1,2,3,5};
        int[] a2 = {2,4,6,8};

        System.out.println(getPrintableIntArray(addArrays(a1, a2)));

        Integer[] intArrays = {1,2,3};

        System.out.println(getPrintableArray(intArrays));

    }
}
