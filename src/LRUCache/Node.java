package LRUCache;

public class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> next;
    public Node<K, V> pre;

    public Node() {

    }
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
