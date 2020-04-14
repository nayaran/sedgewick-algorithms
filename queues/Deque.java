/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private Node first;
    private Node last;

    private int size;

    public Deque() { }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node newItem = new Node();
        newItem.item = item;

        newItem.next = first;
        if (first != null)
            first.prev = newItem;
        else
            // first item to be added;
            last = newItem;

        first = newItem;

        size += 1;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node newItem = new Node();
        newItem.item = item;

        newItem.prev = last;
        if (last != null)
            last.next = newItem;
        else
            first = newItem;

        last = newItem;
        size += 1;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item itemToRemove = first.item;

        if (first.next != null)
            first.next.prev = null;
        else
            last = null;
        first = first.next;

        size -= 1;

        return itemToRemove;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item itemToRemove = last.item;

        if (last.prev != null)
            last.prev.next = null;
        else
            first = null;
        last = last.prev;

        size -= 1;

        return itemToRemove;
    }

    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<Item> {
        Node current = first;
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node n = first;
        while (n != null) {
            str.append(n.item);
            str.append("-> ");
            n = n.next;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        // Test basic api
        System.out.println("Testing basic API.....");
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(3);
        System.out.println("dequeue.addFirst(3) : " + deque);
        System.out.println("dequeue.removeFirst() : " + deque.removeFirst() + " : " + deque);

        deque.addFirst(3);
        System.out.println("dequeue.addFirst(3) : " + deque);
        System.out.println("dequeue.removeLast() : " + deque.removeLast() + " : " + deque);

        deque.addLast(3);
        System.out.println("dequeue.addLast(3) : " + deque);
        System.out.println("dequeue.removeFirst() : " + deque.removeFirst() + " : " + deque);

        deque.addLast(3);
        System.out.println("dequeue.addLast(3) : " + deque);
        System.out.println("dequeue.removeLast() : " + deque.removeLast() + " : " + deque);


        deque.addFirst(2);
        System.out.println("dequeue.addFirst(2) : " + deque);

        deque.addFirst(1);
        System.out.println("dequeue.addFirst(1) : " + deque);

        deque.addLast(4);
        System.out.println("dequeue.addLast(4) : " + deque);

        deque.addFirst(3);
        System.out.println("dequeue.addFirst(3) : " + deque);

        System.out.println("dequeue.isEmpty() : " + deque.isEmpty());
        System.out.println("dequeue.size() : " + deque.size());


        System.out.println("dequeue.removeFirst() : " + deque.removeFirst() + " : " + deque);
        System.out.println("dequeue.removeFirst() : " + deque.removeFirst() + " : " + deque);

        System.out.println("dequeue.removeLast() : " + deque.removeLast() + " : " + deque);
        System.out.println("dequeue.removeLast() : " + deque.removeLast() + " : " + deque);
        try {
            System.out.println("dequeue.removeFirst() : " + deque.removeFirst() + " : " + deque);
            System.out.println("dequeue.removeLast() : " + deque.removeLast() + " : " + deque);
        } catch (NoSuchElementException e) {
            System.out.println("threw NoSuchElementException");
        }

        // Test iterator
        System.out.println("Testing iterator.....");
        deque = new Deque<>();

        deque.addFirst(3);
        System.out.println("dequeue.addFirst(3) : " + deque);

        deque.addFirst(2);
        System.out.println("dequeue.addFirst(2) : " + deque);

        deque.addFirst(1);
        System.out.println("dequeue.addFirst(1) : " + deque);

        Iterator<Integer> iterator = deque.iterator();
        System.out.println("Looping over items using iterator : ");
        while (iterator.hasNext()) {
            System.out.println("iterator.next : " + iterator.next());
        }

        System.out.println("Done looping");
        System.out.print("iterator.next : ");
        try {
            System.out.println(+iterator.next());
        } catch (NoSuchElementException e) {
            System.out.println("threw NoSuchElementException");
        }
        System.out.print("iterator.remove : ");
        try {
            iterator.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("threw UnsupportedOperationException");
        }
    }
}