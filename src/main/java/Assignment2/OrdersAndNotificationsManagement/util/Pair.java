package Assignment2.OrdersAndNotificationsManagement.util;

public class Pair<KeyType, ValueType> {
    private KeyType key;
    private ValueType value;

    public Pair(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
        this.key = null;
        this.value = null;
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }

    public Pair<KeyType, ValueType> setKey(KeyType key) {
        this.key = key;

        return this;
    }

    public Pair<KeyType, ValueType> setValue(ValueType value) {
        this.value = value;

        return this;
    }
}
