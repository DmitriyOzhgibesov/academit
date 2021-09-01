package ru.academits.java.ozhgibesov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        T headData = head.getData();
        head = head.getNext();
        count--;
        return headData;
    }

    private ListItem<T> getListItem(int index) {
        checkIndex(index);

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
        return getListItem(index).getData();
    }

    public T setDataByIndex(T newData, int index) {
        ListItem<T> item = getListItem(index);
        T oldData = item.getData();
        item.setData(newData);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getListItem(index - 1);
        T deletedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        count--;

        return deletedData;
    }

    public void addByIndex(T data, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Индекс = " + index);
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Невозможно вставить данные в индекс (" + index + ") т.к. индекс больше допустимого предела (" + count + ")");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> previousItem = getListItem(index - 1);

        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
        count++;
    }

    public boolean removeByData(T data) {
        if (head == null) {
            return false;
        }

        for (ListItem<T> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (Objects.equals(current.getData(), data)) {
                if (previous == null) {
                    head = head.getNext();
                } else {
                    previous.setNext(current.getNext());

                    if (current.getNext() == null) {
                        current.setNext(previous);
                    }
                }

                count--;
                return true;
            }
        }

        return false;
    }

    public void reverse() {
        ListItem<T> previousItem = null;

        for (ListItem<T> item = head, nextItem; item != null; previousItem = item, item = nextItem) {
            nextItem = item.getNext();
            item.setNext(previousItem);
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> copy() {
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

        newList.count = count;
        return newList;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс не может быть меньше нуля");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть меньше, чем количество элементов списка (" + count + ")");
        }
    }

    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder listElementsSequence = new StringBuilder();

        ListItem<T> currentItem = head;

        listElementsSequence.append("[");

        for (int i = 0; i < count - 1; i++) {
            listElementsSequence.append(currentItem.getData());
            listElementsSequence.append(", ");

            currentItem = currentItem.getNext();
        }

        listElementsSequence.append(currentItem.getData());

        listElementsSequence.append("]");

        return listElementsSequence.toString();
    }
}