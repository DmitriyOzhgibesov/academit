package ru.academits.java.ozhgibesov.list;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NullPointerException("Список пуст");
        }

        return head.getData();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NullPointerException("Список пуст");
        }

        T headData = head.getData();
        head = head.getNext();
        count--;
        return headData;
    }

    public ListItem<T> getListItemByIndex(int index) {
        checkIndexOutOfBounds(index);

        int i = 0;

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            if (i == index) {
                return currentItem;
            }

            i++;
        }

        return head;
    }

    public T getDataByIndex(int index) {
        return getListItemByIndex(index).getData();
    }

    public T setDataByIndex(T newData, int index) {
        ListItem<T> item = getListItemByIndex(index);
        T previousData = item.getData();
        item.setData(newData);

        return previousData;
    }

    public T removeByIndex(int index) {
        checkIndexOutOfBounds(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getListItemByIndex(index - 1);
        T previousData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        count--;

        return previousData;
    }

    public void addListItemByIndex(T newData, int index) {
        checkIndexOutOfBounds(index);

        if (index == 0) {
            addFirst(newData);
        }

        ListItem<T> previousItem = getListItemByIndex(index - 1);
        previousItem.setNext(new ListItem<>(newData, previousItem.getNext()));
        count++;
    }

    public boolean removeByData(T data) {
        if (head == null) {
            return false;
        }

        for (ListItem<T> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (Objects.equals(current.getData(), data))
            {
                if (previous != null)
                {
                    previous.setNext(current.getNext());

                    if (current.getNext() == null)
                    {
                        current.setNext(previous);
                    }
                }
                else
                {
                    head = head.getNext();
                }

                count--;
                return true;
            }
        }
        return false;
    }

    public void reverseList() {
        ListItem<T> previousItem = null;

        for (ListItem<T> item = head, nextItem; item != null; previousItem = item, item = nextItem) {
            nextItem = item.getNext();
            item.setNext(previousItem);
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> copyList() {
        if (count == 0) {
            return new SinglyLinkedList<>();
        }

        ListItem<T> newHead = new ListItem<>(head.getData());
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        newList.head = newHead;

        for (ListItem<T> current = head.getNext(), currentItemCopy = newHead; current != null; current = current.getNext(), currentItemCopy = currentItemCopy.getNext()) {
            ListItem<T> nextItemCopy = new ListItem<>(current.getData());
            currentItemCopy.setNext(nextItemCopy);
        }

        newList.count = getCount();
        return newList;
    }

    private void checkIndexOutOfBounds(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть от 0 до " + getCount() + " включительно");
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть от 0 до " + getCount() + " включительно");
        }
    }
}