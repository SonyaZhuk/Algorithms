package algo.lakman.medium.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) cash.
 * <p>
 * See Lakman p. 552
 * <p>
 * {@link <a href="https://leetcode.com/problems/lru-cache/">146. LRU Cache</a>}.
 */
public class Cache {
    private int capacity;
    private Map<Integer, LinkedListNode> cacheMap;

    private LinkedListNode listHead = null;
    private LinkedListNode listTail = null;

    public Cache(int capacity) {
        if (capacity <= 0)
            throw new UnsupportedOperationException();
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
    }

    private static class LinkedListNode {
        private LinkedListNode next, prev;
        private int key;
        private String value;

        public LinkedListNode(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public String getValue(int key) {
        LinkedListNode item = cacheMap.get(key);
        if (item == null) return null;

        /* Перемещение в начало списка. */
        if (item != listHead) {
            removeFromLinkedList(item);
            insertAtFrontOfLinkedList(item);
        }
        return item.value;
    }

    /* Удаление пары ключ/значение из кэша. */
    public boolean remove(int key) {
        LinkedListNode node = cacheMap.get(key);
        removeFromLinkedList(node);
        cacheMap.remove(key);
        return true;
    }

    public void put(int key, String value) {
        /* Удалить, если уже присутствует. */
        remove(key);

        /* Если кэш заполнен, удалить самый старый элемент. */
        if (cacheMap.size() >= capacity && listTail != null) {
            remove(listTail.key);
        }

        /* Insert nеw node. */
        LinkedListNode node = new LinkedListNode(key, value);
        insertAtFrontOfLinkedList(node);
        cacheMap.put(key, node);
    }

    /* Удаление узла из связного списка. */
    private void removeFromLinkedList(LinkedListNode node) {
        if (node == null) return;

        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
        if (node == listTail) listTail = node.prev;
        if (node == listHead) listHead = node.next;
    }


    /* Вставка узла в начало связного списка. */
    private void insertAtFrontOfLinkedList(LinkedListNode node) {
        if (listHead == null) {
            listHead = node;
            listTail = node;
        } else {
            listHead.prev = node;
            node.next = listHead;
            listHead = node;
        }
    }
}
