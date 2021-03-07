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
            throw new IndexOutOfBoundsException("Список пуст");
        }

        return head.getData();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public T removeFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException("Список пуст");
        }

        T headData = head.getData();
        head = head.getNext();
        count--;
        return headData;
    }

    public ListItem<T> getListItemByIndex(int index) {
        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс =" + index + ". Индекс должен быть от 0 до " + this.getCount());
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс =" + index + ". Индекс должен быть от 0 до " + this.getCount());
        }

        int counter = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == index) {
                return p;
            }
            counter++;
        }

        return head;
    }

    public T getDataByIndex(int index) {
        ListItem<T> item = getListItemByIndex(index);
        return item.getData();
    }

    public T setDataByIndex(T newData, int index) {
        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть от 0 до " + this.getCount());
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть от 0 до " + this.getCount());
        }
        ListItem<T> item = getListItemByIndex(index);
        T previousData = item.getData();
        item.setData(newData);

        return previousData;
    }

    public T removeByIndex(int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть от 0 до " + this.getCount());
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть от 0 до " + this.getCount());
        }
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
        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть от 0 до " + this.getCount());
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс = " + index + ". Индекс должен быть от 0 до " + this.getCount());
        }
        if (index == 0) {
            addFirst(newData);
        }
        ListItem<T> previousItem = getListItemByIndex(index - 1);
        ListItem<T> newListItem = new ListItem<>(newData);
        newListItem.setNext(previousItem.getNext());
        previousItem.setNext(newListItem);
        count++;
    }

    public boolean removeByData(T data) {
        if (head == null) {
            return false;
        }

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(p.getData(), data)) {
                if (prev == null) {
                    head = p.getNext();
                    return true;
                }
                prev.setNext(p.getNext());
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

        for (ListItem<T> p = head.getNext(), c = newHead; p != null; p = p.getNext(), c = c.getNext()) {
            ListItem<T> q = new ListItem<>(p.getData());
            c.setNext(q);
        }

        newList.count = this.getCount();
        return newList;
    }
}