package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class CuponEuromillionsTest {
  
  private CuponEuromillions c1 = new CuponEuromillions();

  @Test
  public void testFormat() {
    assertEquals(c1.format(), "", "format as string: formatted string not as expected. ");

    c1.addDipToCuppon(new Dip());
    assertEquals(c1.format(), "Dip #1:N[] S[]\n", "format as string: formatted string not as expected. ");

    c1.addDipToCuppon(new Dip(new int[]{1,2,3,4,5}, new int[]{1,2}));
    assertEquals(c1.format(), "Dip #1:N[] S[]\nDip #2:N[  1  2  3  4  5] S[  1  2]\n", "format as string: formatted string not as expected. ");
  }

}
