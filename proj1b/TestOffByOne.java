import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    Palindrome palindrome = new Palindrome();

    // Your tests go here.
   @Test
    public void isEqualChars() {
       // Deque<Character> d = palindrome.wordToDeque("butter");
       // char x = d.get(1);
       // char y = d.get(2);
       int x = 'a';
       int y = 'b';
       System.out.println(x);
       System.out.println(y);
       boolean result = offByOne.equalChars('a', 'b');
       assertTrue(result);
   }
}
