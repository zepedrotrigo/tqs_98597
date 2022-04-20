package euromillions;

import java.util.Objects;

import sets.SetOfNaturals;

import java.util.Random;

/**
 * A set of 5 numbers and 2 starts according to the Euromillions ranges.
 *
 * @author ico0
 */
public class Dip {
    // Constants added by me
    private static int VALID_NUMBERS = 5;
    private static int VALID_STARTS = 2;

    private static int NUMBERS_MIN_VAL = 1;
    private static int NUMBERS_MAX_VAL = 50;
    private static int STARTS_MIN_VAL = 1;
    private static int STARTS_MAX_VAL = 12;

    private SetOfNaturals numbers;
    private SetOfNaturals starts;

    public Dip() {
        numbers = new SetOfNaturals();
        starts = new SetOfNaturals();
    }

    public Dip(int[] arrayOfNumbers, int[] arrayOfStarts) {
        this();

        if (arrayOfNumbers.length == VALID_NUMBERS && arrayOfStarts.length == VALID_STARTS) {
            for (int n : arrayOfNumbers) {
                if (n < NUMBERS_MIN_VAL || n > NUMBERS_MAX_VAL)
                    throw new IllegalArgumentException(String.format("Number must be greater than %d and lower than %d",
                            NUMBERS_MIN_VAL, NUMBERS_MAX_VAL));
            }
    
            for (int n : arrayOfStarts) {
                if (n < STARTS_MIN_VAL || n > STARTS_MAX_VAL)
                    throw new IllegalArgumentException(String.format("Star must be greater than %d and lower than %d",
                            STARTS_MIN_VAL, STARTS_MAX_VAL));
            }

            numbers.add(arrayOfNumbers);
            starts.add(arrayOfStarts);
        } else {
            throw new IllegalArgumentException("wrong number of elements in numbers/stars");
        }
    }

    public SetOfNaturals getNumbersColl() {
        return numbers;
    }

    public SetOfNaturals getStarsColl() {
        return starts;
    }

    public static Dip generateRandomDip() {
        Random generator = new Random();

        Dip randomDip = new Dip();
        for (int i = 0; i < 5;) {
            int candidate = generator.nextInt(49) + 1;
            if (!randomDip.getNumbersColl().contains(candidate)) {
                randomDip.getNumbersColl().add(candidate);
                i++;
            }
        }
        for (int i = 0; i < 2;) {
            int candidate = generator.nextInt(9) + 1;
            if (!randomDip.getStarsColl().contains(candidate)) {
                randomDip.getStarsColl().add(candidate);
                i++;
            }
        }
        return randomDip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.numbers);
        hash = 29 * hash + Objects.hashCode(this.starts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dip other = (Dip) obj;
        if (!Objects.equals(this.numbers, other.numbers)) {
            return false;
        }
        return Objects.equals(this.starts, other.starts);
    }

    /**
     * prepares a string representation of the data structure, formated for
     * printing
     *
     * @return formatted string with data
     */
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("N[");
        for (int number : getNumbersColl()) {
            sb.append(String.format("%3d", number));
        }
        sb.append("] S[");
        for (int star : getStarsColl()) {
            sb.append(String.format("  %d", star));
        }
        sb.append("]");
        return sb.toString();
    }
}
