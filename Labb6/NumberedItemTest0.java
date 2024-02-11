import static org.junit.Assert.*;

import org.junit.Test;

public class NumberedItemTest0 {

    @Test
    public void testCompare() {
	NumberedItem<Integer> i1 = new NumberedItem<>(-1,1);
	NumberedItem<Integer> i2 = new NumberedItem<>(0,17);
	NumberedItem<Integer> i3 = new NumberedItem<>(5,100);
	NumberedItem<Integer> i4 = new NumberedItem<>(10,378);
	assertEquals(0, i1.compareTo(i2));
	assertEquals(-5, i3.compareTo(i4));
	assertEquals(5, i4.compareTo(i3));
    }
}
