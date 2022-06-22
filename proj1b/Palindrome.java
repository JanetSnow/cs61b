

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word.length() == 0) {
            return null;
        }
        Deque<Character> d = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char cr = word.charAt(i);
            d.addLast(cr);
        }
        return d;
    }

    // 检测前后顺序拼写都相同
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        boolean result = recursivePalindrome(d, d.size());
        return result;
        /*
        if (d.isEmpty()|| d.size() == 1) {
            return true;
        }
        String fword = "";
        for (int begin = 0; begin < d.size(); begin++) {
            fword += d.get(begin);
        }
        String bword = "";
        for ( int end = d.size() - 1; end >= 0; end--) {
            bword += d.get(end);
        }
        if (fword.equals(bword)) {
            return true;
        }
        return false;
         */
    }

    private boolean recursivePalindrome(Deque<Character> d, int size) {
        if (d.isEmpty() || size == 1) {
            return true;
        }
        Character first = d.removeFirst();
        Character last = d.removeLast();
        size -= 2;
        if (first == last) {
            if (size == 0 || size == 1) {
                return true;
            }
            return recursivePalindrome(d, size);
        }
        return false;
    }

    // 检测是否前后/后前的值相减等于1
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        boolean result = recursiveIsPalindrome(d, d.size(), cc);
        return result;
    }

    private boolean recursiveIsPalindrome(Deque<Character> d, int size, CharacterComparator cc) {
        if (d.isEmpty() || size == 1) {
            return true;
        }
        Character first = d.removeFirst();
        Character last = d.removeLast();
        size -= 2;
        if (cc.equalChars(first, last)) {
            if (size == 0 || size == 1) {
                return true;
            }
            return recursiveIsPalindrome(d, size, cc);
        }
        return false;
    }
}