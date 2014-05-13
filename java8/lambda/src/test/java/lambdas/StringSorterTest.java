package lambdas;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by satish on 13/05/14.
 */
public class StringSorterTest {
    StringSorter sorter = new StringSorter();

    List<String> defaultStrings = null;

    @Before
    public void onSetup(){
        defaultStrings = Arrays.asList("people", "to", "from");
    }

    @Test
    public void testSortingBasedOnSize(){
        sorter.sortBasedOnSize(defaultStrings);
        verifyDefaultStringsSortingBasedOnSize();
    }

    @Test
    public void testSortingBasedOnSizeLambda(){
        sorter.sortBasedOnSizeNew(defaultStrings);
        verifyDefaultStringsSortingBasedOnSize();
    }

    private void verifyDefaultStringsSortingBasedOnSize(){
        Assert.assertEquals(3, defaultStrings.size());
        Assert.assertEquals("to", defaultStrings.get(0));
        Assert.assertEquals("from", defaultStrings.get(1));
        Assert.assertEquals("people", defaultStrings.get(2));
    }

    @Test
    public void testSortingIgnoreCase(){
        List<String> strings = Arrays.asList("delhi", "Bangalore", "chennai", "Calcutta", "banaras");
        sorter.sortIgnoreCase(strings);
        Assert.assertEquals(5, strings.size());
        Assert.assertEquals("banaras", strings.get(0));
        Assert.assertEquals("Bangalore", strings.get(1));
        Assert.assertEquals("Calcutta", strings.get(2));
        Assert.assertEquals("chennai", strings.get(3));
        Assert.assertEquals("delhi", strings.get(4));
    }

    @Test
    public void testSortingIgnoreCaseLambda(){
        List<String> strings = Arrays.asList("delhi", "Bangalore", "chennai", "Calcutta", "banaras");
        sorter.sortIgnoreCaseNew(strings);
        Assert.assertEquals(5, strings.size());
        Assert.assertEquals("banaras", strings.get(0));
        Assert.assertEquals("Bangalore", strings.get(1));
        Assert.assertEquals("Calcutta", strings.get(2));
        Assert.assertEquals("chennai", strings.get(3));
        Assert.assertEquals("delhi", strings.get(4));
    }

    @Test
    public void testSortingIgnoreCaseDesc(){
        List<String> strings = Arrays.asList("delhi", "Bangalore", "chennai", "Calcutta", "banaras");
        sorter.sortIgnoreCaseDesc(strings);
        Assert.assertEquals(5, strings.size());
        Assert.assertEquals("banaras", strings.get(4));
        Assert.assertEquals("Bangalore", strings.get(3));
        Assert.assertEquals("Calcutta", strings.get(2));
        Assert.assertEquals("chennai", strings.get(1));
        Assert.assertEquals("delhi", strings.get(0));
    }
}
