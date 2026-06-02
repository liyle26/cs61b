package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T> {

    private class node {
        public T item;
        public node next;
        public node prev;

        public node(T i, node n, node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private node dummy;
    private int size;

    public LinkedListDeque() {
        dummy = new node(null, null, null);
        dummy.next = dummy;
        dummy.prev = dummy;
        size = 0;
    }

    public LinkedListDeque(node d) {
        dummy = d;
    }

    public T getRecursive(int index) {
        if (index + 1 > size) {
            return null;
        }
        if (index == 0) {
            return dummy.next.item;
        }
        deque.LinkedListDeque<T> link = new LinkedListDeque<>(dummy.next);
        return link.getRecursive(index - 1);
    }

    @Override
    public void addFirst(T item) {
        node second=dummy.next;
        node first = new node(item, dummy.next, dummy);
        dummy.next=first;
        second.prev=first;
        size++;
    }

    @Override
    public void addLast(T item) {
        node lastse=dummy.prev;
        node last = new node(item, dummy, dummy.prev);
        lastse.next=last;
        dummy.prev=last;
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
        if (this.isEmpty()) {
            return;
        }
        node curr = dummy.next;
        while (curr != dummy) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size < 1) {
            return null;
        } else {
            T item = dummy.next.item;
            dummy.next = dummy.next.next;
            dummy.next.prev=dummy;
            size--;
            return item;
        }
    }

    @Override
    public T removeLast() {
        if (size < 1) {
            return null;
        } else {
            T item = dummy.prev.item;
            dummy.prev = dummy.prev.prev;
            dummy.prev.next=dummy;
            size--;
            return item;
        }
    }

    @Override
    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        node curr = dummy;
        for (int i = 0; i < index + 1; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListiterator();
    }


    private class LinkedListiterator implements Iterator<T> {
        int index;

        public LinkedListiterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size - 1;
        }

        @Override
        public T next() {
            return get(index++);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque) {
            LinkedListDeque other = (LinkedListDeque) o;
            {
                for (int i = 0; i <= size - 1; i++) {
                    if (this.get(i) != other.get(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
        }
    }