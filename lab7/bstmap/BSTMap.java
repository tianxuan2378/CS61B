package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{
    private BSTNode root;  // 根节点
    private class BSTNode{
        public BSTNode left;
        public BSTNode right;
        public int size;
        private K key;
        private V value;

        private BSTNode(K key, V value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap(){

    }

    @Override
    public void clear(){
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    public V get(BSTNode x, K key) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with a null key");
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) {
            return x.value;
        } else if(cmp > 0) {
            return get(x.right, key);
        } else {
            return get(x.left, key);
        }
    }

    @Override
    public int size() {
        return root.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, value);
    }

    public BSTNode put(BSTNode x, K key, V value) {
        if(x == null)
            return new BSTNode(key, value, 1);
        int cmp = x.key.compareTo(key);
        if(cmp > 0) {
            x.left = put(x, key, value);
        }
        if(cmp > 0){
            x.right = put(x, key, value);
        }
        x.size = 1 + x.left.size + x.right.size;
        return x;
    }

    //prints out the BSTMap in order of increasing Key.
    public void printInOrder(){

    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
