package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T>{

    private Object[] elements; // 底层数组
    private int head;          // 队头指针
    private int tail;          // 队尾指针
    private int capacity;      // 容量
    public ArrayDeque()
    {
            head=0;
            tail=0;
            capacity=100;
            elements=new Object[100];
    }

    public void resize(int s)
    {
        this.capacity=s;
            T[] newarray=(T[]) new Object[s];
            for(int i=0;i<=tail-1;i++)
            {
                newarray[i]= (T) elements[i];
            }
            for(int i=capacity-1;i>=head+1;i--)
            {
                if(head==capacity)
                {
                    break;
                }
               newarray[i]= (T) elements[i-capacity/2];
            }
            elements=newarray;
    }


    @Override
    public void addFirst(T t){
            if(head==tail){
                resize(2*capacity);
                head=(head-1+capacity)%capacity;
                elements[head]=t;
            }
        head=(head-1+capacity)%capacity;
        elements[head]=t;
    }

    @Override
    public void addLast(T t) {
        if(head==tail){
            resize(2*capacity);
            elements[tail]=t;
            head=(head+1-capacity)%capacity;
        }
        elements[tail]=t;
        head=(head+1-capacity)%capacity;
    }

    @Override
    public boolean isEmpty() {
        for(int i=0;i<=capacity-1;i++){
            if(elements[i]!=null){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public void printDeque() {
        int curr=head;
        while(curr!=tail)
        {
            curr=(curr+1-capacity)%capacity;
            System.out.print(elements[curr]+" ");
        }
    }

    @Override
    public T removeFirst() {
        head=(head+1-capacity)%capacity;
        return (T) elements[head];
    }

    @Override
    public T removeLast() {
        tail=(tail-1+capacity)%capacity;
        return (T) elements[tail];
    }

    @Override
    public T get(int index) {
        if(index+1>capacity-head+1+tail){
            return null;
        } else if (index+1<=capacity+1-head) {
            return (T) elements[head+index];
        }else {
            return (T) elements[index-capacity+head-1];
        }
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
        if(o instanceof ArrayDeque other){
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
