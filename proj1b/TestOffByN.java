import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    OffByN offBy5 = new OffByN(5);

    @Test
    public void testEqualChars() {
        boolean b1 = offBy5.equalChars('a', 'f');
        boolean b2 = offBy5.equalChars('f', 'a');
        boolean b3 = offBy5.equalChars('f', 'h');
        assertTrue(b1);
        assertTrue(b2);
        assertFalse(b3);
    }
}
