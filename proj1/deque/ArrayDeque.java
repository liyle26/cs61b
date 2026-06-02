package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T>{

    private T[] elements; // 底层数组
    private int head;          // 队头指针
    private int tail;          // 队尾指针
    private int capacity;      // 容量
    private int size;
    public ArrayDeque()
    {
            head=0;
            tail=0;
            size=0;
            capacity=100;
            elements= (T[]) new Object[100];
    }

    public void resize(int newCap)
    {
        T[] newArr = (T[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            newArr[i] = elements[(head + i) % elements.length];
        }
        elements = newArr;
        head = 0;
        tail = size;
    }


    @Override
    public void addFirst(T t){
        if (size == elements.length) {
            resize(2 * elements.length);
        }
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = t;
        size++;
    }

    @Override
    public void addLast(T t) {
        if (size == elements.length) {
            resize(2 * elements.length);
        }
        elements[tail] = t;
        tail = (tail + 1) % elements.length;
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
        for (int i = 0; i < size; i++) {
            System.out.print(elements[(head + i) % elements.length] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        T res = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return res;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        tail = (tail - 1 + elements.length) % elements.length;
        T res = elements[tail];
        elements[tail] = null;
        size--;
        return res;
    }


    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return elements[(head + index) % elements.length];
    }

    @Override
    public Iterator iterator() {
        return new Arraydequeiterator();
    }

    public class Arraydequeiterator implements Iterator{

        private int index;
        Arraydequeiterator(){
            index=0;
        }

        @Override
        public boolean hasNext() {
            return (boolean) get(index+1);
        }

        @Override
        public Object next() {
            index++;
            return get(index);
        }
    }



    @Override
    public boolean equals(Object o){
        if(o instanceof ArrayDeque){
            ArrayDeque other=(ArrayDeque) o;
            if(other.size()!=size()){
                return false;
            }
            if(other.capacity!=capacity){
                return false;
            }
            for(int i=0;i<=size()-1;i++)
            {
                if(elements[i]!=other.elements[i])
                {
                        return false;
                }
            }
            return true;
        }
        return false;
    }
}
