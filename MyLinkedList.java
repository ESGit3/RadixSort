public class MyLinkedList {
    private int size;
    private Node start, end;
    public MyLinkedList() {
        size = 0;
        start = null;
        end = null;
    }
    public int size() {
        return size;
    };

    public boolean add(String value) {
        Node temp = new Node(value);
        if (size == 0) {
            start = temp;
            end = temp;
            size++;
        } else {
            end.setNext(temp);
            Node initialEnd = end;
            end = temp;
            end.setNext(null);
            end.setPrev(initialEnd);
            size++;
        }
        return true;
    };

    public void add(int index, String value) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(index + " is out of bounds");
        }
        Node temp = new Node(value);
        if (size == 0) {
            start = temp;
            end = temp;
            size++;
        } else if (index == 0) {
            Node initialStart = start;
            start.setPrev(temp);
            start = temp;
            start.setNext(initialStart);
            size++;
        } else if (index == size) {
            end.setNext(temp);
            Node initialEnd = end;
            end = temp;
            end.setNext(null);
            end.setPrev(initialEnd);
            size++;
        } else {
            Node currNode = start;
            for (int i = 0; i < index; i++) {
                currNode = currNode.getNext();
            }

            Node prevNode = currNode.getPrev();

            currNode.setPrev(temp);
            temp.setNext(currNode);
            temp.setPrev(prevNode);
            prevNode.setNext(temp);
            size++;
        }
    };

    public String get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index + "is out of bounds");
        }
        Node currNode = this.start;
        for (int i = 0; i < index; i++) {
            currNode = currNode.getNext();
        }
        return currNode.getData();
    };

    public String set(int index, String value) {
        Node currNode = this.start;
        for (int i = 0; i < index; i++) {
            currNode = currNode.getNext();
        }
        currNode.setData(value);
        return value;
    };

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        String temp = "[";
        Node currNode = this.start;
        for (int i = 0; i < this.size; i++) {
            temp += currNode.getData() + ", ";
            currNode = currNode.getNext();
        }
        temp = temp.substring(0, temp.length() - 2) + "]";
        return temp;
    };
    //Any helper method that returns a Node object MUST BE PRIVATE!

    public String toStringReversed() {
        if (this.size == 0) {
            return "[]";
        }
        String temp = "[";
        Node currNode = this.end;
        for (int i = 0; i < this.size; i++) {
            temp += currNode.getData() + ", ";
            currNode = currNode.getPrev();
        }
        temp = temp.substring(0, temp.length() - 2) + "]";
        return temp;
    }

    public String remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index + "is out of bounds");
        }

        String removedValue = this.get(index);

        if (size == 1) {
            start = null;
            end = null;
            size = 0;
            return removedValue;
        }
        if (index == 0) {
            start = start.getNext();
            start.getPrev().setNext(null);
            start.setPrev(null);
            size -= 1;
            return removedValue;
        }
        if (index == size - 1) {
            end = end.getPrev();
            end.getNext().setPrev(null);
            end.setNext(null);
            size -= 1;
            return removedValue;
        }

        Node currNode = this.start;
        for (int i = 0; i < index; i++) {
            currNode = currNode.getNext();
        }

        Node prevNode = currNode.getPrev();
        Node nextNode = currNode.getNext();

        currNode.setPrev(null);
        currNode.setNext(null);
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        size -= 1;
        return removedValue;
    }

    public void extend(MyLinkedList other) {
        if (size == 0) {
            start = other.start;
            end = other.end;
        } else {
            end.setNext(other.start);
            other.start.setPrev(end);
            end = other.end;
            size += other.size;
        }
        other.start = null;
        other.end = null;
        other.size = 0;
    }
}