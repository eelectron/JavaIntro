package bst;

public class Key implements Comparable {
    private long keyValue;

    // Constructor
    public Key(long in) {
        keyValue = in;
    }

    public void setValue(long x) {
        keyValue = x;
    }

    public long getValue() {
        return keyValue;
    }

    public int compareTo(Object o) {
        Key k = (Key)o;
        long v = this.keyValue - k.getValue();
        if (v < 0)
            return -1;
        else if (v > 0)
            return 1;
        else
            return 0;
    }

    public String toString() {
        return keyValue + "";
    }
}
