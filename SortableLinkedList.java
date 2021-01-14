public class SortableLinkedList{
    private class Node{
        public Node(Integer value){
            data = value;
        }
        private Integer data;
        private Node next,prev;

        public void setNext(Node n){
            next = n;
        }

        public void setPrev(Node n){
            prev = n;
        }

        public void setData(Integer s){
            data = s;
        }

        public Node getNext(){
            return next;
        }

        public Node getPrev(){
            return prev;
        }

        public Integer getData(){
            return data;
        }

    }


    private int size;
    private Node start,end;
    public SortableLinkedList(){
    }

    public int size(){
        return size;
    }

    public boolean add(Integer value){
        if(size() == 0){
            start = new Node(value);
            end = start;
            size = 1;
        }else{//not empty
            end.setNext(new Node(value));
            end.getNext().setPrev(end);//double added this
            end = end.getNext();
            size += 1;
        }
        return true;
    }

    public Integer remove(int index){
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("index "+index+" not valid for size: "+size());
        }
        Node ans;
        if(size == 1){
            ans = start;
            start = null;
            end = null;
            size = 0;
        }else if(index == 0){
            ans = start;
            start = start.getNext();
            start.setPrev(null);
            size--;
        }else if(index == size()-1){
            ans = end;
            end = end.getPrev();
            end.setNext(null);
            size--;
        }else{
            ans = getNthNode(index);
            ans.getPrev().setNext(ans.getNext());
            ans.getNext().setPrev(ans.getPrev());
            size--;
        }
        return ans.getData();
    }

    private Node getNthNode(int index){
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException("index "+index+" not valid for size: "+size());
        }
        Node current = start;
        while(index > 0){
            current = current.getNext();
            index --;
        }
        return current;
    }

    public void add(int index, Integer value){
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException("index "+index+" not valid for size: "+size());
        }else if ( index == size){
            //empty, or end of the list.
            add(value);
        }else if (index == 0){
            //start of the list
            Node toAdd = new Node(value);
            toAdd.setNext(start);
            start.setPrev(toAdd);//double added this
            start = toAdd;
            size++;
        }else{
            //middle of the list.
            //get pre-Node
            Node tmp = getNthNode(index-1);
            Node toAdd = new Node(value);
            //add after pre-node
            toAdd.setPrev(tmp);
            toAdd.setNext(tmp.getNext());
            toAdd.getNext().setPrev(toAdd);//double (deleted a line)
            toAdd.getPrev().setNext(toAdd);//double
            size++;
        }
    }
    public Integer get(int index){
        if(index < 0 || index > size()){
            //out of bounds
            throw new IndexOutOfBoundsException("index "+index+" not valid for size: "+size());
        }
        return getNthNode(index).getData();
    }
    public Integer set(int index, Integer value){
        if(index < 0 || index > size()){
            //out of bounds
            throw new IndexOutOfBoundsException("index "+index+" not valid for size: "+size());
        }
        Node n = getNthNode(index);
        Integer v = n.getData();
        n.setData(value);
        return v;
    }

    public void extend(SortableLinkedList other){
        if(size() == 0){
            //empty list just become the other
            start = other.start;
            end = other.end;
            size = other.size;
        }else if(other.size() > 0){
            //when both have at least one element:
            //connect
            end.setNext(other.start);
            other.start.setPrev(end);
            //move end
            end = other.end;
            //update sizes
            this.size = size()+other.size();
        }
        //always erase other
        other.size = 0;
        other.end = null;
        other.start = null;
    }
    public String toString(){
        Node current = start;
        String ans = "[";
        while(current != null){
            ans += current.getData();
            current = current.getNext();
            if(current!= null){
                ans += ", ";
            }
        }
        return ans + "]";
    }
    public String toStringReversed(){
        Node current = end;
        String ans = "[";
        while(current != null){
            ans += current.getData();
            current = current.getPrev();
            if(current!= null){
                ans += ", ";
            }
        }
        return ans + "]";
    }
}
