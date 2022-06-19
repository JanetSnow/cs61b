import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 * [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    public int first;
    /**
     * Remaining elements of list.
     */
    public IntList rest;

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
        /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /**
     * Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */

    public static IntList dcatenate(IntList A, IntList B) {
        // TODO:  fill in method
        IntList res = A;
        if (A == null) {
            return null;
        }
        while (A.rest != null) {
            A = A.rest;
        }
        A.rest = B;
        return res;
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     * 此时，C和res都指向同一个地址值，随着循环和赋值的进行，它们实际上是同时变化互相影响
     * 最后的值都相等，因为它们指向同一个地址值
     * while循环后的.rest只是在形式上等于它的下一个位置。
     * 而在这个方法中最后return的值也是形参的值。但实际上不是完整的A.
     */
    public static IntList catenate(IntList A, IntList B) {
        // TODO:  fill in method
        if (A == null) {
            return null;
        }
        IntList res = new IntList(A.first, null);
        IntList C = res;
        A = A.rest;

        while (A != null) {
            res.rest = new IntList(A.first, null);
            A = A.rest;
            res = res.rest;
        }
        res.rest = B;
        return C;

    }

    /**
     * Returns the reverse of the given IntList.
     * This method is destructive. If given null
     * as an input, returns null.
     */

    public static IntList reverse(IntList A) {
        IntList prev = null;
        IntList next = null;
        if (A == null) {
            return A;
        }
        while (A != null) {
            next = A.rest;
            A.rest = prev;
            prev = A;
            A = next;
        }
        return prev;
    }

    /*
    public static IntList reverse(IntList A){
        IntList prev = null;
        IntList next = null;
        IntList tail = null;

        if (A == null){
            return null;
        }
        while (A != null){
            next = A.rest;
            if (next == null){
                tail = A;
            }
            //prev第一次是指向第一个地址值，所以每次赋值都包括第一个地址值上的第一个值
            //A.rest = prev：这一步后reverse中的A变成1,null;
            //由于原来位置上的值没了，所以next生成了由{2，3，4，5}构成的另一个新的地址值
            A.rest = prev;
            prev = A;
            //第二个地址值最开始是因为next而创建的，后面在这句话后A也指向了第二个
            //A和next最后都是null,此时它们不再指向第二个地址值，所以不会影响之前的改变。
            //此时，仍然指向第二个地址值的变量就是我们要return的变量
            A = next;
        }
        return tail;
    }
     */

    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}




