import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        // String word = "cat";
        String word = "rancor";
        boolean result = palindrome.isPalindrome(word);
        assertFalse(result);
    }

    @Test
    public void testIsPalindrome2() {
        String word = "a";
        boolean result = palindrome.isPalindrome(word);
        assertTrue(result);
    }

    @Test
    public void testIsPalindrome3() {
        // String word = "racecar";
        String word = "noon";
        boolean result = palindrome.isPalindrome(word);
        assertTrue(result);
    }

    @Test
    public void testIsPalindrome4() {
        String word = "aflakeb";
        CharacterComparator cc = new OffByOne();
        boolean result = palindrome.isPalindrome(word, cc);
        assertTrue(result);
    }

    @Test
    public void testIsPalindrome5() {
        String word = "bidding";
        CharacterComparator cc = new OffByN(5);
        boolean result = palindrome.isPalindrome(word, cc);
        assertTrue(result);
    }
}
