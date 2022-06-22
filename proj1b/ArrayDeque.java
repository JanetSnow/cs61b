public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private final int INIT_CAPACITY  = 8;
    //nextFirst和nextLast实际上分别代表这个数组的开头与结尾，该数组元素顺序符合这个规律
    //该数组并不是按照普通的index的顺序来查找元素，而是按照从nextFirst开始来查找
    private int nextFirst;
    private int nextLast;
    private int size;
    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /* get the last index */
    private int minusOne(int index) {
        return Math.floorMod(index - 1, items.length);
    }

    /* get the next index */
    private int plusOne(int index) {
        return Math.floorMod(index + 1, items.length);
    }

    private int plusOne(int index, int length) {
        return Math.floorMod(index + 1, length);
    }

    @Override
    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        size++;
        nextFirst = minusOne(nextFirst);
    }

    public T getFirst() {
        return items[plusOne(nextFirst)];
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        resize();
        T removedItem = getFirst();
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size--;
        return removedItem;
    }

    @Override
    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        size++;
        nextLast = plusOne(nextLast);
    }

    public T getLast() {
        return items[minusOne(nextLast)];
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        resize();
        T removedItem = getLast();
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size--;
        return removedItem;

    }

    @Override
    public void printDeque() {
        for (int index = plusOne(nextFirst); index != nextLast; index = plusOne(index)) {
            System.out.print(items[index] + " ");
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size || isEmpty()) {
            return null;
        }
        index = Math.floorMod(plusOne(nextFirst) + index, items.length);
        return items[index];
    }

    /* Designing resize() method */
    private void resize() {
        if (size == items.length) {
            expand();
        }
        if (size < (items.length) * 0.25 && items.length > 8) {
            reduce();
        }
    }

    private void expand() {
        resizeHelper(items.length * 2);
    }

    private void reduce() {
        resizeHelper(items.length / 2);
    }

    private void resizeHelper(int capacity) {
        T[] tempArr = items;
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i = begin; i != end; i = plusOne(i, tempArr.length)) {
            items[nextLast] = tempArr[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = tempArr[end];
        nextLast = plusOne(nextLast);
    }

}



