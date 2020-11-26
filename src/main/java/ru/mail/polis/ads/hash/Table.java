package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Table<Key, Value> implements HashTable<Key, Value> {
    private static final double MAX_LOAD_FACTOR = 0.75;

    private int size;
    private chainNode[] table;

    private class chainNode<Key, Value> {
        Key key;
        Value value;
        chainNode<Key, Value> next;

        public chainNode(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public chainNode(Key key, Value value, chainNode<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Table() {
        table = new chainNode[16];
    }

    public Table(int size) {
        table = new chainNode[size];
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        chainNode<Key, Value> temp = table[hash];
        while (temp != null && !temp.key.equals(key)) {
            temp = temp.next;
        }
        return temp == null ? null : temp.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hash = hash(key);
        chainNode temp = table[hash];
        if (temp == null) {
            table[hash] = new chainNode(key, value);
            ++size;
            return;
        }

        while (temp.next != null && !temp.equals(key)) {
            temp = temp.next;
        }

        if (!temp.key.equals(key)) {
            temp.next = new chainNode(key, value);
            ++size;
        } else {
            temp.value = value;
        }

        if (size >= MAX_LOAD_FACTOR * table.length) resize();
    }

    private void resize() {
        int newSize = table.length * 2;
        size = 0;
        chainNode[] oldTable = table.clone();
        table = new chainNode[newSize];
        for (chainNode<Key, Value> node : oldTable) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);
        chainNode<Key, Value> temp = table[hash];

        if (temp == null) return null;

        if (temp.key.equals(key)) {
            --size;
            table[hash] = temp.next;
            return temp.value;
        }

        while (temp.next != null && !temp.next.key.equals(key)) {
            temp = temp.next;
        }

        if (temp.next != null) {
            --size;
            Value result = temp.next.value;
            temp.next = temp.next.next;
            return result;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }
}
