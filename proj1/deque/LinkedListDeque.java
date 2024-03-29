package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;
        Node() {
            item = null;
            prev = next = null;
        }
        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private int size;
    private Node sentinel;

    //create an empty LinkedListDeque
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //Adds an item of type T to the front of the deque.
    // You can assume that item is never null.
    @Override
    public void addFirst(T item) {
        size += 1;
        Node temp = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
    }

    @Override
    public void addLast(T item) {
        size += 1;
        Node temp = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
    }

    @Override
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    //Once all the items have been printed, print out a new line.
    @Override
    public void printDeque() {
        Node p = sentinel;
        int i = size;
        while (i > 0) {
            p = p.next;
            System.out.println(p.item + " ");
            i--;
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            size -= 1;
            Node temp = sentinel.next;
            T x = temp.item;
            sentinel.next = temp.next;
            temp.next.prev = sentinel;
            return x;
        }
    }

    //Removes and returns the item at the back of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            size -= 1;
            Node temp = sentinel.prev;
            T x = temp.item;
            sentinel.prev = temp.prev;
            temp.prev.next = sentinel;
            return x;
        }
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Node p = sentinel;
        int i = index;
        while (i >= 0) {
            p = p.next;
            i--;
        }
        return p.item;
    }

    //The Deque objects we’ll make are iterable (i.e. Iterable<T>)
    // so we must provide this method to return an iterator.
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private int pos;
        private Node temp;

        DequeIterator() {
            pos = 0;
            temp = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return temp != sentinel;
        }

        public T next() {
            T returnitem = temp.item;
            pos += 1;
            temp = temp.next;
            return returnitem;
        }
    }

    //Returns whether or not the parameter o is equal to the Deque.
    // o is considered equal if it is a Deque and
    // if it contains the same contents (as goverened by the generic T’s equals method)
    // in the same order.
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof  Deque)) {
            return false;
        }
        Deque<T> o = (Deque<T>) obj;
        if (size != o.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(o.get(i))) {
                return false;
            }
        }
        return true;
    }

    private T getRecursiveHelper(int index, Node p) {
        if (p == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
    //Same as get, but uses recursion.
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }
}
