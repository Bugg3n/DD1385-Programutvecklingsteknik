import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SparseVectorTestB {

    @Test
    public void testAdd1() {
	    SparseVectorB<String> v = new SparseVectorB<>();
        v.add("a");
        assertEquals("a", v.get(0));
        v.add(2, "c");
        v.add("b");
        assertEquals("b", v.get(1));
        assertEquals(3, v.size());
        v.add("d");
        assertEquals("d", v.get(3));
    }

    @Test
    public void testAdd2() {
	    SparseVectorB<String> v = new SparseVectorB<>();
        v.add(1000, "a");
        v.add(0, "b");
        v.add(-1, "c");
        assertEquals("a", v.get(1000));
        assertEquals("c", v.get(0));
        assertEquals(2, v.size());
    }
    
    @Test
    public void testIndexOf(){
        SparseVectorB<String> v = new SparseVectorB<>();
        v.add("a");
        v.add(2, "b");
        v.add(10, "c");
        v.add(5, "d");
        assertEquals(2, v.indexOf("b"));
        assertEquals(10, v.indexOf("c"));
        assertEquals(5, v.indexOf("d"));
        assertEquals(-1, v.indexOf("e"));
    }

    @Test
    public void testRemoveAt() {
        SparseVectorB<String> v = new SparseVectorB<>();
        v.add("a");
        v.add(2, "b");
        v.add(10, "c");
        v.add(5, "d");
        v.removeAt(2);
        assertEquals(3, v.size());
        assertEquals("d", v.get(5));
        assertEquals("c", v.get(10));
        assertEquals(null, v.get(2));
    }

    @Test
    public void testRemoveElem() {
        SparseVectorB<String> v = new SparseVectorB<>();
        v.add("a");
        v.add(2, "b");
        v.add(10, "c");
        v.add(5, "d");
        v.removeElem("c");
        assertEquals(3, v.size());
        assertEquals("d", v.get(5));
        assertEquals("b", v.get(2));
        assertEquals(null, v.get(10));
    }

    @Test
    public void testSize() {
        SparseVectorB<String> v = new SparseVectorB<>();
        assertEquals(0, v.size());
        v.add("a");
        assertEquals(1, v.size());
        v.add(0, "e");
        assertEquals(1, v.size());
        v.add("f");
        assertEquals(2, v.size());

    }

    @Test
    public void testMinIndex() {
        SparseVectorB<String> v = new SparseVectorB<>();
        assertEquals(-1, v.minIndex());
        v.add("a");
        v.add(2, "b");
        v.add(10, "c");
        v.add(5, "d");
        assertEquals(0, v.minIndex());
    }

    @Test
    public void testMaxIndex() {
        SparseVectorB<String> v = new SparseVectorB<>();
        assertEquals(-1, v.maxIndex());
        v.add("a");
        v.add(2, "b");
        v.add(10, "c");
        v.add(5, "d");
        assertEquals(10, v.maxIndex());
    }

    @Test
    public void testGet() {
        SparseVectorB<String> v = new SparseVectorB<>();
        assertEquals(null, v.get(3));
        v.add("a");
        v.add(2, "b");
        v.add(10, "c");
        v.add(5, "d");
        v.add("e");
        v.add("f");
        assertEquals("a", v.get(0));
        assertEquals("b", v.get(2));
        assertEquals("c", v.get(10));
        assertEquals("d", v.get(5));
        assertEquals("e", v.get(1));
        assertEquals("f", v.get(3));
        assertEquals(null, v.get(4));
    }

    @Test
    public void toArray(){
        SparseVectorB<String> v = new SparseVectorB<>();
        assertEquals(0, v.toArray().length);    
        v.add(1, "a");
        v.add(2, "b");
        v.add(4, "c");
        v.add(5, "d");
        v.add(8, "e");
        Object[] array = {null, "a", "b", null, "c", "d", null, null, "e"};
        assertEquals(array, v.toArray());
    }
    
    @Test
    public void testSortedValues() {
        SparseVectorB<String> v = new SparseVectorB<>();
        assertEquals(0, v.sortedValues().size());
        v.add(4, "a");
        v.add(1, "c");
        v.add(5, "d");
        v.add(2, "b");
        List<String> array = Arrays.asList("a", "b", "c", "d");
        assertEquals(array, v.sortedValues());
    }
}
