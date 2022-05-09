import java.util.ArrayList;

public class ArrayDeque<T> {
    // empty first pointer of first and last
    private T[] items;
    private int size=0;
    private int nextFirst;
    private int nextLast;

    private final double usage_ratio =0.25;


    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length/2;
        nextLast = items.length/2+1;

    }
    // deep-copy
    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[8];
        size=0;
        nextFirst = items.length/2;
        nextLast = items.length/2+1;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }

    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    private int minusOne(int index){
        if (index==0){
            return items.length-1;
        } else {
            return index-1;
        }
    }

    private int plusOne(int index){

        if (index == items.length-1){
            return 0;
        }else {
            return index+1;
        }
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int j = 0;
        for (int i = plusOne(nextFirst);i!=minusOne(nextLast);i=plusOne(i)){
            a[j] = items[i];
            j = plusOne(j);
        }
        a[j] = items[minusOne(nextLast)];
        items=a;
        nextFirst = items.length-1;
        nextLast = j+1;

    }

    public void addFirst(T item){
        items[nextFirst] = item;
        size++;
        nextFirst = minusOne(nextFirst);
        if (size==items.length){
            resize(size*2);
        }

    }

    public void addLast(T item){
        items[nextLast] = item;
        size++;
        nextLast = plusOne(nextLast);
        if (size==items.length){
            resize(size*2);
        }

    }

    public T removeFirst(){
        T res = items[plusOne(nextFirst)];
        nextFirst = plusOne(nextFirst);
        items[nextFirst] =null;
        size--;
        if ((double)size/items.length < usage_ratio && (items.length>=16)) {
            resize((int) (items.length*0.5));
        }

        return res;

    }

    public T removeLast(){
        T res = items[minusOne(nextLast)];
        nextLast = minusOne(nextLast);
        items[nextLast] =null;
        size--;
        if ((double)size/items.length < usage_ratio && (items.length>=16)) {
            resize((int) (items.length*0.5));
        }

        return res;


    }

    public T get(int index){
        if (size==0){
            return null;
        }
        return items[(index + plusOne(nextFirst))% items.length];

    }

    public void printDeque(){
        int i =0;
        while (i<size){
            System.out.println(get(i));
            System.out.print("");
            i++;

        }
        System.out.println('\n');

    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> de = new ArrayDeque<>();
//        de.addFirst(0);
//        de.get(0);
//        de.addLast(2);
//        de.removeFirst();
//        de.addFirst(4);
//        de.addFirst(5);
//        de.get(1);
//        de.addFirst(7);
//        de.addFirst(8);
//        de.removeFirst();
//        de.get(2);
//        de.get(3);
//        de.addFirst(12);
//        de.addFirst(13);
//        de.addLast(14);
//        int a = de.get(0);
//        de.addLast(16);
//        de.addLast(17);
//        de.addLast(18);
//        de.removeFirst();
//        de.printDeque();
//    }

}
