package lmu.cmsi281.assignments;


public class LinkedListInt implements ListInt {

    int  size;
    Node head;

    class Node {
        int  data;
        Node next;

        public Node(int d) {
            data = d;
            next = null;
        }
    }

    public LinkedListInt() {
        size = 0;
        head = null;
    }

    @Override
    // Returns the current size of the Linked List 
    public int size() {
        return size;
    }

    @Override
    // Returns the value stored in the data field of the Node at the given index
    // If the index is out of bounds then throw an IndexOutOfBoundsException
    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // Iterate to specified position
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    // Sets the value stored in the data field of the Node at the given index with element
    // If the index is out of bounds then throw an IndexOutOfBoundsException
    public void set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // Iterate to specified position
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        curr.data = element;
    }

    @Override
    // Adds a new element to the Linked List by adding a new Node
    public void add(int element) {
        Node node = new Node(element);
        // If there is nothing in the Linked List
        if (head == null) {
            head = node;
        } else {
            Node curr = head;
            // Iterate until we arrive at the last node
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }
        size = size + 1;
    }

    @Override
    // Inserts a new element as a new Node into the Linked List at the given index
    // If the index is out of bounds then throw an IndexOutOfBoundsException
    public void insert(int index, int element) {
        // Make sure we are not inserting out of bounds
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } 
        // Allocate a new node
        Node node = new Node(element);
        // Check if we have nothing in our array
        if (index == 0) {
            node.next = head;
            head = node;
            size = size + 1;
            return;
        }
        // Move two pointers prev and next to insert location
        Node curr = head;
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = curr;
            curr = curr.next;
        }
        // Set pointers
        node.next = curr;
        prev.next = node;
        size = size + 1;
    }

    @Override
    // Removes the element at a given index from the Linked List
    // If the index is out of bounds then throw an IndexOutOfBoundsException
    public void remove(int index) throws IndexOutOfBoundsException {
        // Check if we have nothing in our array
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // Check if we have nothing in our array
        if (index == 0) {
            head = head.next;
            size = size - 1;
            return;
        }
        // Move two pointers prev and next to removal location
        Node curr = head;
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;
        curr.next = null;
        curr = null;
        size = size - 1;
    }

    @Override
    // Returns true if the Linked List contains the element, else false
    public boolean contains(int element) {
        Node curr = head;
        while (curr != null) { 
            if (curr.data == element) {
                return true;
            }
            curr = curr.next;
        }
        return false;

    }

    // Add all the given elements as Nodes to the Linked List at the given index
    // If the index is out of bounds then throw an IndexOutOfBoundsException
    public void addAll(int index, int[] elements) {
        // Make sure we are not inserting out of bounds
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (elements.length == 0) {
            return;
        }

        // Build the elements as a linked Nodes
        Node start = new Node(elements[0]);
        Node tail = start;
        for (int i = 1; i < elements.length; i++) {
            Node node = new Node(elements[i]);
            tail.next = node;
            tail = node;
        }
        // Check if we have nothing in our array
        if (index == 0) {
            tail.next = head;
            head = start;
            size = size + elements.length;
            return;
        }
        // Move two pointers prev and next to insert location
        Node curr = head;
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = curr;
            curr = curr.next;
        }
        // Set pointers
        tail.next = curr;
        prev.next = start;
        size = size + elements.length;
    }

    public void swap(int indexA, int indexB) throws IndexOutOfBoundsException {
        if (indexA < 0 || indexA >= size || indexB < 0 || indexB >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node currA = head;
        Node prevA = currA;
        Node currB = head;
        Node prevB = currB;

        for (int i = 0; i < indexA; i++) {
            prevA = currA;
            currA = currA.next;
        }
        for (int i = 0; i < indexB; i++) {
            prevB = currB;
            currB = currB.next;
        }
        Node nextA = currA.next;
        Node nextB = currB.next;

        currB.next = nextA;
        prevB.next = currA;
        currA.next = nextB;
        prevA.next = currB;
    }

    public void reverse() {
        if (size <= 1) {
            return;
        }

        Node prev = null;
        Node curr = head;
        Node next = curr.next;

        while (next != null) {
            curr.next = prev;
            prev = curr;
            System.out.println(prev.data);
            curr = next;
            next = next.next;	
        }
        curr.next = prev;
        head = curr;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[ ]";
        }
        String out = "[ ";
        Node curr = head;
        while (curr.next != null) {
            out = out + curr.data + ", ";
            curr = curr.next;
        }

        out = out + curr.data + " ]";
        return out;
    }
    public static void main(String[] args) {
        LinkedListInt a = new LinkedListInt();
        LinkedListInt b = new LinkedListInt();

        a.add(7);
        a.add(11);
        a.add(93);
        System.out.println("a = " + a.toString());
        b.add(7);
        b.add(11);
        b.add(93);
        System.out.println("b = " + b.toString());

        a.insert(0, 15);
        a.insert(3, 44);
        a.insert(a.size(), 20);
        System.out.println("a = " + a.toString());

        a.set(0, 20);
        a.set(3, 15);
        a.set(5, 44);
        System.out.println("a = " + a.toString());

        a.remove(0);
        System.out.println("a = " + a.toString()); 
        a.remove(2);
        System.out.println("a = " + a.toString()); 
        a.remove(3);
        System.out.println("a = " + a.toString()); 

        a.addAll(0, new int[]{ 0, 1, 2});
        System.out.println("a = " + a.toString());

        a.reverse();
        System.out.println("a = " + a.toString());	
    }
}
