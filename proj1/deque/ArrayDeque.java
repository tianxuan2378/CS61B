package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{

    private int size;
    public int startsize = 8;  //The starting size of the array should be 8.
    public T[] items;
    public int nextfirst;   // This array is a circled array
    public int nextlast;

    public ArrayDeque(){
        items = (T[]) new Object[startsize];
        size = 0;
        nextfirst = 2;
        nextlast = 3;
    }

    // For the movement of nextfirst
    private int addone(int x){
        return (x + 1) % items.length;
    }

    // For the movement of nextfirst
    private int minusone(int x){
        return (x - 1 + items.length) % items.length;
    }

    private void resize(int capacity){
        T[] temp = (T[]) new Object[capacity];
        int index = addone(nextfirst);
        for(int i = 0; i < size; i++){
            temp[i] = items[index];
            index = addone(index);
        }
        nextfirst = capacity - 1;
        nextlast = size;
        items = temp;
    }

    /**
     * to check whether the array is full so that it needs to be resized
     */
    private void checkFull() {
        if (size == items.length) {
            resize(size * 2);
        }
    }

    /**
     * to check whether the array is under the required usage rate so that it needs to be resized
     */
    private void checkWasted() {
        int len = items.length;
        if (len >= 16 && size < len / 4) {
            resize(len / 4);
        }
    }


    @Override
    public void addFirst(T item) {
        checkFull();
        items[nextfirst] = item;
        nextfirst = minusone(nextfirst);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque. You can assume that item is never null.
     */
    @Override
    public void addLast(T item) {
        checkFull();
        items[nextlast] = item;
        nextlast = addone(nextlast);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if(!this.isEmpty()){
            int temp = size;
            while(temp > 0){
                nextfirst = addone(nextfirst);
                System.out.println(items[nextfirst] + " ");
                temp -= 1;
            }
            System.out.println();
        }
    }

    //Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        checkWasted();
        nextfirst = addone(nextfirst);
        T returnitem = items[nextfirst];
        items[nextfirst] = null;
        size--;
        return returnitem;
    }

    //Removes and returns the item at the back of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        checkWasted();
        nextlast = minusone(nextlast);
        T returnitem = items[nextlast];
        items[nextlast] = null;
        size--;
        return returnitem;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        if(index < 0) {
            return null;
        }
        int gap = addone(nextfirst);
        while (gap != 0) {
            index = addone(index);
            gap--;
        }
        return items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayNewIterator();
    }

    private class ArrayNewIterator implements Iterator<T>{
        private int pos;
        ArrayNewIterator(){
            pos = addone(nextfirst);
        }

        @Override
        public boolean hasNext() {
            return pos != nextlast;
        }

        @Override
        public T next() {
            T returnitem = items[pos];
            pos = addone(pos);
            return returnitem;
        }
    }

    //Returns whether or not the parameter o is equal to the Deque.
    // o is considered equal if it is a Deque and
    // if it contains the same contents (as goverened by the generic T’s equals method) in the same order.
    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(o == null){
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        ArrayDeque<T> p = (ArrayDeque<T>) o;
        if(p.size != this.size){
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(p.get(i))) {
                return false;
            }
        }
        return true;
    }
}
