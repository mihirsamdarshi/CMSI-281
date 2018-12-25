package lmu.cmsi281.assignments;

/**
 * CMSI Assignment 2
 * 
 * @author <Samdarshi, Mihir>
 *
 */
public class LinkedListInt {

    int size;
    Node head;

    //Contructor for Linked List Node
    class Node {
        int data;
        Node next;

        public Node(int d) {
            data = d;
            next = null;
        }
    }

    //Contructor for Linked List
    public LinkedListInt() {
        size = 0;
        head = null;
    }

    // Returns the current size of the Linked List
    public int size() {
        return this.size;
    }

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

    // Sets the value stored in the data field of the Node at the given index with
    // element
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

    // Adds a new element to the Linked List by adding a new Node
    public void add(int element) {
        Node additionNode = new Node(element);
        if (head == null) {
            head = additionNode;
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = additionNode;
        }
        size = size + 1;
    }

    // Inserts a new element as a new Node into the Linked List at the given index
    // If the index is out of bounds then throw an IndexOutOfBoundsException
    public void insert(int index, int element) {
        Node lastNode = head;
        Node insertionNode = new Node(element);

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            insertionNode.next = head;
            head = insertionNode;
        } else {
            for (int i = 0; i < index - 1; ++i) {
                lastNode = lastNode.next;
            }
            if (index == size) {
                insertionNode.next = null;
                lastNode.next = insertionNode;
            } else {
                insertionNode.next = lastNode.next;
                lastNode.next = insertionNode;
            }
        }

        size++;
    }

    // Removes the element at a given index from the Linked List
    // If the index is out of bounds then throw an IndexOutOfBoundsException
    public void remove(int index) throws IndexOutOfBoundsException {
        Node currentNode = head;
        Node previousNode = null;
        int i = 0;

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            head = currentNode.next;
        } else {
            while (currentNode != null && i < index - 1) {
                previousNode = currentNode;
                currentNode = currentNode.next;
                i++;
            }
            previousNode.next = currentNode.next;
        }

        size--;
    }

    // Returns true if the Linked List contains the element, else false
    public boolean contains(int element) {
        Node searchingNode = head;
        if (head == null) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (searchingNode.data == element) {
                    return true;
                }
            }
        }
        return false;
    }

    // Add all the given elements as Nodes to the Linked List at the given index
    // If the index is out of bounds then throw an IndexOutOfBoundsException
    public void addAll(int index, int[] elements) {
/*
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < elements.length; i++) {
            insert(index + i, elements[i]);
        }
*/
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedListInt additionList = new LinkedListInt();
        for (int i = 0; i < elements.length; i++) {
            additionList.add(elements[i]);
        }
        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = additionList.head;
    }

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
        System.out.println(a.get(2));

        b.add(0);
        b.add(1);
        b.add(2);
        System.out.println("b = " + b.toString());

        a.insert(0, 15);
        a.insert(3, 44);
        a.insert(a.size(), 20);
        System.out.println("a = " + a.toString());

        b.insert(0, 69);
        System.out.println("b = " + b.toString());

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

        a.addAll(0, new int[] { 0, 1, 2 });
        System.out.println("a = " + a.toString());
    }
}
