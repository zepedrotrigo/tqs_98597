/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testConstructorFromBadArrays() {
        int numValidNumbers = 5;
        int numValidStars = 2;

        // Not asked in the exercise 2a, but decided to do it
        Dip d1 = instance.generateRandomDip();
        assertEquals(numValidNumbers, d1.getNumbersColl().size());
        assertEquals(numValidStars, d1.getStarsColl().size());

        // This is what is asked for in 2a
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] {}, new int[] { 1, 2 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1}, new int[] { 1, 2 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2 }, new int[] { 1, 2 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] {1, 2, 3}, new int[] {1, 2}));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2, 3, 4 }, new int[] { 1, 2 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2}));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2, 3, 4, 5 }, new int[] {}));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 2, 3 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] {-1, 2, 3, 4, 5}, new int[] {1,2}));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 51, 2, 3, 4, 5 }, new int[] { 1, 2 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] {1, 2, 3, 4, 5}, new int[] {-1,2}));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] {1, 2, 3, 4, 5}, new int[] {1,20}));
    }

    @Test
    public void testFormat() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

    @Test
    public void testValidStarRange() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }
}
