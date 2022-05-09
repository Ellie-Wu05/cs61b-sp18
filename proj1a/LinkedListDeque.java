public class LinkedListDeque<T> {

    public class LinkedNode{

        public T item;
        public LinkedNode prev;
        public LinkedNode next;

        public LinkedNode(LinkedNode pre_node, LinkedNode next_node, T item){
            this.item = item;
            this.prev = pre_node;
            this.next = next_node;
        }


    }


    private int size = 0;
    private LinkedNode front;
    private LinkedNode rear;

    public LinkedListDeque(){
        size = 0;
        front = new LinkedNode(null,null,null);
        rear = new LinkedNode(null,null,null);
        front.next = rear;
        rear.prev = front;


    }
    public LinkedListDeque(LinkedListDeque other){
        size = 0;
        front = new LinkedNode(null,null,null);
        rear = new LinkedNode(null,null,null);
        front.next = rear;
        rear.prev = front;

        int i =0 ;
        while(i<other.size()){
            this.addFirst((T) other.get(i));
        }

    }



    public void addFirst(T item){
        size +=1;

        LinkedNode currNext = front.next;
        LinkedNode newNode = new LinkedNode(front,currNext,item);

        currNext.prev = newNode;
        front.next = newNode;

    }
    public void addLast(T item){
        size +=1;
        LinkedNode currPrev = rear.prev;
        LinkedNode newNode = new LinkedNode(currPrev,rear,item);
        currPrev.next = newNode;
        rear.prev = newNode;


    }
    public boolean isEmpty(){
        return size ==0;

    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int i = 0;
        LinkedNode temp = front.next;
        while (i<size){
            System.out.println( temp.item + " ");
            temp = temp.next;
            i+=1;
        }

    }
    public T removeFirst(){
        if (size==0){
            return null;
        }

        LinkedNode currHead = front.next;
        front.next = currHead.next;
        currHead.next.prev= front;
        size -=1;
        return currHead.item;

    }
    public T removeLast(){
        if (size==0){
            return null;
        }

        LinkedNode currTail = rear.prev;
        rear.prev = currTail.prev;
        currTail.prev.next = rear;

        size-=1;
        return currTail.item;

   
    }
    public T get(int index){
        if(size ==0 || index >size){
            return null;
        }
        LinkedNode temp = front;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;

    }


    public LinkedNode trace(LinkedNode current, int index){
        if (index==0){
            return current.next;
        }

        current = trace(current.next,index-1);

        return current;
    }


    public T getRecursive(int index){
        if(index+1>size){
            return null;
        }

        return trace(front,index).item;


    }




}
