package com.mycompany.app;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTest {
	private Stack<Integer> s1;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

	@BeforeEach
	public void testBeforeEach() throws Exception {
        s1 = new Stack<Integer>();
	}

    @DisplayName("a) A stack is empty on construction.")
    @Test
    public void isEmpty() {
        assertTrue(s1.isEmpty());
    }

    @DisplayName("b) A stack has size 0 on construction.")
    @Test
    public void isSizeZeroOnConstruction() {
        assertEquals(0, s1.size());
    }

    @DisplayName("c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    public void isStackSizeCorrect() {
        int n = getRandomNumber(5,10);

        for (int i = 0; i < n; i++)
            s1.push(i);
        
        assertTrue(!s1.isEmpty());
        assertEquals(n, s1.size());
    }

    @DisplayName("d) If one pushes x then pops, the value popped is x")
    @Test
    public void isPoppedValueCorrect() {
        int n = getRandomNumber(5,10);

        s1.push(n);

        assertEquals(n, s1.pop());
    }


    @DisplayName("e) If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    public void testSizeOnPeek() {
        s1.push(1); // First element

        // push n elements (total elements = n+1)
        int n = getRandomNumber(5, 10);
        for (int i = 0; i < n; i++) {
            s1.push(getRandomNumber(5, 1000));
        }

        assertEquals(1, s1.peek());
        assertEquals(n+1, s1.size()); // first element + n iterations
    }

    @DisplayName("f) If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    public void testPushNPopN() {
        int n = getRandomNumber(5, 10);

        // Add elements to stack to reach size n
        for (int i = 0; i < n; i++) {
            s1.push(getRandomNumber(5, 1000));
        }

        // Pop n times
        for (int i = 0; i < n; i++)
            s1.pop();

        assertTrue(s1.isEmpty());
        assertEquals(0, s1.size());
    }

    @DisplayName("g) Popping from an empty stack does throw a NoSuchElementException")
    @Test
    public void testPopFromEmptyStackException() {
        assertThrows(NoSuchElementException.class, () -> s1.pop());
    }

    @DisplayName("h) Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    public void testPeekFromEmptyStackException() {
        assertThrows(NoSuchElementException.class, () -> s1.peek());
    }

    @DisplayName("i) For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    public void testName5() {
        // Create bounded stack (bound=n)
        int n = getRandomNumber(5, 20);
        Stack<Integer> s2 = new Stack<Integer>(n);

        // Fill stack
        for (int i = 0; i < n; i++)
            s2.push(i);

        assertThrows(IllegalStateException.class, () -> s2.push(1));
    }
}
