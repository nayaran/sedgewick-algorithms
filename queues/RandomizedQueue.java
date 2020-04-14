/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;

    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (size == items.length) {
            if (size == 0) {
                resize(1);
            }
            else {
                resize(size*2);
            }
        }

        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        // Chose random item to remove
        Integer randomIndex = StdRandom.uniform(size());

        Item itemToRemove = items[randomIndex];
        items[randomIndex] = items[size-1];
        items[size-1] = null;
        size--;

        if (size == items.length / 4)
            resize(items.length/4);

        return itemToRemove;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        Integer randomIndex = StdRandom.uniform(size());
        return items[randomIndex];
    }

    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int index;
        private Item[] itemsCopy;

        public RandomizedIterator() {
            itemsCopy = createItemsCopy();
        }

        public boolean hasNext() {
            return index < size();
        }

        public Item next() {
            if (index == size()) {
                throw new NoSuchElementException();
            }
            Integer randomIndex = StdRandom.uniform(index, size());
            Item itemToReturn = itemsCopy[randomIndex];
            swapUsingIndices(index, randomIndex);

            index++;
            return itemToReturn;
        }

        private void swapUsingIndices(int i, int j) {
            Item temp = itemsCopy[i];
            itemsCopy[i] = itemsCopy[j];
            itemsCopy[j] = temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private Item[] createItemsCopy() {
            Item[] newItems = (Item[]) new Object[size()];
            for (int i = 0; i < size(); i++) {
                newItems[i] = items[i];
            }
            return newItems;
        }


    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    public static void main(String[] args) {
        // Test basic api
        System.out.println("Testing basic API.....");
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<>();

        randQueue.enqueue(3);

        System.out.println("randQueue.enqueue(3) - " + randQueue);

        randQueue.enqueue(2);
        System.out.println("randQueue.enqueue(2) - " + randQueue);

        randQueue.enqueue(1);
        System.out.println("randQueue.enqueue(1) - " + randQueue);

        randQueue.enqueue(4);
        System.out.println("randQueue.enqueue(4) - " + randQueue);
        System.out.println("randQueue.isEmpty() - " + randQueue.isEmpty());
        System.out.println("randQueue.size() - " + randQueue.size());

        System.out.println("randQueue.sample() - " + randQueue.sample());
        System.out.println("randQueue.sample() - " + randQueue.sample());
        System.out.println("randQueue.sample() - " + randQueue.sample());
        System.out.println("randQueue.sample() - " + randQueue.sample());

        System.out.println("randQueue.dequeue() - " + randQueue.dequeue() + " - " + randQueue);

        System.out.println("randQueue.dequeue() - " + randQueue.dequeue() + " - " + randQueue);
        System.out.println("randQueue.dequeue() - " + randQueue.dequeue() + " - " + randQueue);
        System.out.println("randQueue.dequeue() - " + randQueue.dequeue() + " - " + randQueue);
        System.out.println("randQueue.isEmpty() - " + randQueue.isEmpty());
        System.out.println("randQueue.size() - " + randQueue.size());
        System.out.print("randQueue.dequeue() - ");
        try {
             randQueue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("threw NoSuchElementException");
        }
        System.out.print("randQueue.sample() - ");
        try {
            randQueue.sample();
        } catch (NoSuchElementException e) {
            System.out.println("threw NoSuchElementException");
        }

        // Test iterator
        System.out.println("Testing iterator.....");
        randQueue = new RandomizedQueue<>();
        randQueue.enqueue(3);

        System.out.println("randQueue.enqueue(3) - " + randQueue);

        randQueue.enqueue(2);
        System.out.println("randQueue.enqueue(2) - " + randQueue);

        randQueue.enqueue(1);
        System.out.println("randQueue.enqueue(1) - " + randQueue);
        System.out.println("randQueue.size() - " + randQueue.size());

        Iterator<Integer> iterator1 = randQueue.iterator();
        Iterator<Integer> iterator2 = randQueue.iterator();
        System.out.println("Looping over items using iterator - ");
        while (iterator1.hasNext()) {
            System.out.println("iterator1.next - " + iterator1.next());
        }
        while (iterator2.hasNext()) {
            System.out.println("iterator2.next - " + iterator2.next());
        }
        System.out.println("Done looping");
        System.out.print("iterator1.next - ");
        try {
            System.out.println(+iterator1.next());
        } catch (NoSuchElementException e) {
            System.out.println("threw NoSuchElementException");
        }
        System.out.print("iterator1.remove - ");
        try {
            iterator1.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("threw UnsupportedOperationException");
        }
        System.out.println();

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        for (int i = 1; i <= 10; i++) {
            rq.enqueue(i);
            System.out.println("randQueue.enqueue(" + i + ") - " + rq);
        }

        iterator1 = rq.iterator();
        iterator2 = rq.iterator();
        while (iterator1.hasNext()) {
            System.out.println("iterator1.next - " + iterator1.next());
        }
        while (iterator2.hasNext()) {
            System.out.println("iterator2.next - " + iterator2.next());
        }
    }
}
