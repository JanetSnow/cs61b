//doubly linked list(circular sentinel)循环双链表
/*
双向链表常采用带附加头结点的循环链表的链接方式。除此之外，还可以使用doubly linked list(two sentinels:sentFront, sentBack).
单链表和双链表都可以制成循环链表。
一个双向链表有一个附加头结点，由链表的头指针first指示，它的data域或者不放数据，或者存放一个特殊要求的数据，它的lLink指向链表的尾结点(即最后一个结点)，
它的rLink指向链表的首元结点(即第一个结点)。双向链表的首元结点的左链指针lLink和尾结点的右链指针rLink都指向附加头结点first。
 */

public class LinkedListDeque<T> {
    /** inner class Node. */
    public class Node {
        /** the item stored on this node. */
        private T item;
        /** the Node before this Node. **/
        private Node pre;
        /** the Node after this Node. **/
        private Node next;

        /** constructor for Node. */
        public Node(T n, Node ppre, Node nnext) {
            item = n;
            pre = ppre;
            next = nnext;
        }

        /** constructor for Node.(especially for sentinel node). */
        public Node(Node ppre, Node nnext) {
            pre = ppre;
            next = nnext;
        }
    }

    /** sentinel node. */
    private Node sentinel;
    /** size of the deque. */
    private int size;

    /** constructor for deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        Node newList = new Node(item, sentinel, sentinel.next);
        sentinel.next.pre = newList;
        sentinel.next = newList;
        size++;
    }

    public void addLast(T item) {
        Node newList = new Node(item, sentinel.pre, sentinel);
        sentinel.pre.next = newList;
        sentinel.pre = newList;
        size++;
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.pre.item;
        //要满足成为最后一个节点有两个条件：1.它的next指向sentinel. 2.sentinel的prev指向它。这样才是循环
        //在双链表中 ，prev指向前一个节点，next指向下一个节点，依此类推
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size--;
        return ret;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node ptr = sentinel;
        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    private T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }

    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }
}
