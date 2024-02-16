public class LinkedList<Element> {

    protected Node head;
    protected Node tail;
    protected int count;

    // Creates a linked list
    public LinkedList() {
        clear();
    }

    // Clears the elements in linked list
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Check if the element is in the linked list
    public boolean contains(Element element) {
        Node current = head;

        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }


    // Gets the index 
    public int indexOf(Element element) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.element.equals(element)) {
                return index;
            }

            index++;
            current = current.next;
        }

        return -1;
    }

    // Insert a new element
    public void add(Element element) {
        Node node = new Node(element);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        count++;
    }

    // Get the number of nodes
    public int size() {
        return count;
    }

    // Get an element from an index
    public Element get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.element;
    }

    // Link nodes together
    protected class Node {

        public Element element;
        public Node next;

        // Create a new node
        public Node(Element element) {
            this.element = element;
        }
    }
}
