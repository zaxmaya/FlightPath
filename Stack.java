public class Stack<Element> extends LinkedList<Element> {

    // Put element on top of list
    public void push(Element element) {
        add(element);
    }

    // Remove element on top of list
    public Element pop() {
        Element element = tail.element;

        Node previous = null;
        Node current = head;

        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        if (previous != null) {
            previous.next = null;
            tail = previous;
        } else {
            head = null;
            tail = null;
        }

        count--;

        return element;
    }
    
    // Return top of list
    public Element peek() {
        if(tail != null) {
            return tail.element;
        }
        
        return null;
    }
}
